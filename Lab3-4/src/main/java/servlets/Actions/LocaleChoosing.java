package servlets.Actions;

import dto.LoginDto;
import entity.User;
import exception.BadCredentialsException;
import exception.UnavailableException;
import service.WebUserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LocaleChoosing implements Action {


    public LocaleChoosing() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws IOException, ServletException {
        String language = request.getParameter("language");
        HttpSession session = request.getSession();
        session.setAttribute("language", language);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}