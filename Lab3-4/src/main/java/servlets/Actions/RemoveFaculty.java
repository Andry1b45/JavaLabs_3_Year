package servlets.Actions;

import dto.FacultyDto;
import exception.DeleteFacultyException;
import service.WebAdminService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveFaculty implements Action {
    private WebAdminService webAdminService;

    public RemoveFaculty(WebAdminService webAdminService) {
        this.webAdminService = webAdminService;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws IOException, ServletException {
        String name = request.getParameter("Name");

        try{
            webAdminService.removeFaculty(new FacultyDto(name));
            request.getRequestDispatcher("/jsp/adminMenu.jsp").forward(request, response);
        }
        catch (DeleteFacultyException e) {
            e.printStackTrace();
            request.getRequestDispatcher("/jsp/adminMenu.jsp").forward(request, response);
        }
    }
}
