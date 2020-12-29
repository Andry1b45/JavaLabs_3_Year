package servlets.Actions;

import entity.User;
import exception.GetStudentsException;
import service.PaginationService;
import service.WebAdminService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ViewStudents implements Action {
    WebAdminService webAdminService;
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
            e.printStackTrace();
        }
    }
}
