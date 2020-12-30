package servlets.Actions;

import entity.User;
import exception.GetStudentsException;
import org.apache.log4j.Logger;
import service.PaginationService;
import service.WebAdminService;
import servlets.Servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ViewStudents implements Action {
    WebAdminService webAdminService;
    final static Logger logger = Logger.getLogger(ViewStudents.class);

    public ViewStudents(WebAdminService webAdminService) {
        this.webAdminService = webAdminService;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws IOException, ServletException {
        try {
            ArrayList<User> students = webAdminService.getStudentList();
            PaginationService.pagination(request,students,"students",5);
            request.getRequestDispatcher("/jsp/viewStudents.jsp").forward(request, response);
        } catch (GetStudentsException e) {
            logger.error("Exception while getting students");
            request.getRequestDispatcher("/menu").forward(request, response);
            e.printStackTrace();
        }
    }
}
