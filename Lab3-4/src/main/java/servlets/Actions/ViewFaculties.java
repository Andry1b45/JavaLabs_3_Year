package servlets.Actions;

import entity.Faculty;
import exception.GetFacultiesException;
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

    public ViewFaculties(WebUserService userService) {
        service = userService;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws IOException, ServletException {
        try {
            ArrayList<Faculty> faculties = service.getFaculties(Integer.parseInt(getSorting(request)));
            PaginationService.pagination(request,faculties,"faculties",5);
            request.getRequestDispatcher("/jsp/viewFaculties.jsp").forward(request, response);
        } catch (GetFacultiesException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/jsp/viewFaculties.jsp").forward(request, response);  //todo вынести
        }
    }

    private String getSorting(HttpServletRequest request) {
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