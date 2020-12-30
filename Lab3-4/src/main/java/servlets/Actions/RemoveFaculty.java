package servlets.Actions;

import dto.FacultyDto;
import exception.DeleteFacultyException;
import org.apache.log4j.Logger;
import service.WebAdminService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveFaculty implements Action {
    private WebAdminService webAdminService;
    final static Logger logger = Logger.getLogger(RemoveFaculty.class);
    public RemoveFaculty(WebAdminService webAdminService) {
        this.webAdminService = webAdminService;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws IOException, ServletException {
        String name = request.getParameter("Name");

        try{
            webAdminService.removeFaculty(new FacultyDto(name));
            logger.info(request.getSession().getAttribute("username") + " removed faculty " + name);
            request.getRequestDispatcher("/jsp/adminMenu.jsp").forward(request, response);
        }
        catch (DeleteFacultyException e) {
            logger.error("Error while removing faculty");
            request.getRequestDispatcher("/jsp/adminMenu.jsp").forward(request, response);
        }
    }
}
