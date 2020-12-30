package servlets.Actions;

import entity.Faculty;
import exception.GetFacultiesException;
import org.apache.log4j.Logger;
import service.PaginationService;
import service.WebUserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ViewFaculties implements Action {
    private WebUserService service;

    final static Logger logger = Logger.getLogger(ViewFaculties.class);

    public ViewFaculties(WebUserService userService) {
        service = userService;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws IOException, ServletException {
        try {
            ArrayList<Faculty> faculties = service.getFaculties(Integer.parseInt(getSortingType(request)));
            PaginationService.pagination(request,faculties,"faculties",5);
        } catch (GetFacultiesException | NumberFormatException e) {
            logger.error("Exception while getting faculties");
            request.setAttribute("error", e.getMessage());
        }
        request.getRequestDispatcher("/jsp/viewFaculties.jsp").forward(request, response);
    }

    private String getSortingType(HttpServletRequest request) {
        String sortType = "none";
        Iterator<String> it = request.getParameterNames().asIterator();
        while (it.hasNext()) {
            String name = it.next();
            String[] values = request.getParameterValues(name);
            for (String value : values) {
                if (!value.equals("none"))
                    sortType = value;
            }
        }
        return sortType;
    }
}