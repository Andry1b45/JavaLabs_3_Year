package servlets.Actions;

import entity.Application;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import service.PaginationService;
import service.WebAdminService;
import service.WebUserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MenuAction implements Action {

    public MenuAction() {

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws IOException, ServletException {

        if(request.getSession().getAttribute("role").equals("ADMIN")){
            request.getRequestDispatcher("/jsp/adminMenu.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("/jsp/studentMenu.jsp").forward(request, response);
        }

    }
}

