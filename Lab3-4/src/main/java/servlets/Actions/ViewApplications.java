package servlets.Actions;

import entity.Application;
import service.PaginationService;
import service.WebAdminService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ViewApplications implements Action {
    WebAdminService webAdminService;
    public ViewApplications(WebAdminService webAdminService) {
        this.webAdminService = webAdminService;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws IOException, ServletException {
            ArrayList<Application> applications = webAdminService.getApplications();
            PaginationService.pagination(request,applications,"applications",10);
            request.getRequestDispatcher("/jsp/viewApplications.jsp").forward(request, response);

    }
}
