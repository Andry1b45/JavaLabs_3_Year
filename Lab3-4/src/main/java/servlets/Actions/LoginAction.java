package servlets.Actions;

import entity.User;
import service.UserService;
import utilities.Builders.LoginDtoBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

public class LoginAction implements Action {
    private UserService service;

    public LoginAction(UserService userService) {
        this.service = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equals("GET")) {
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        } else if (method.equals("POST")) {
            LoginDtoBuilder builder = new LoginDtoBuilder();
            Iterator<String> it = request.getParameterNames().asIterator();
            while (it.hasNext()) {
                String name = it.next();
                String[] values = request.getParameterValues(name);
                builder.set(name, values[0]);
            }
            try {
                User user = service.loginWithDto(builder.build()).get();
                if (user != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("username", user.getUsername());
                    session.setAttribute("role", user.getConvertedRole());
                    session.setMaxInactiveInterval(9000);
                    response.sendRedirect(request.getContextPath() + "/request");
                } else {
                    request.setAttribute("error", "Incorrect username or password.");
                    request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                }
            } catch (SQLException ignored) {
                request.setAttribute("message", "Sorry, now we are temporary unavailable.");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}