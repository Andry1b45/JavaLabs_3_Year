package servlets.Actions;

import dto.LoginDto;
import entity.User;
import exception.BadCredentialsException;
import exception.BlockedUserException;
import exception.UnavailableException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import service.WebUserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginAction implements Action {
    private WebUserService service;
    final static Logger logger = Logger.getLogger(LoginAction.class);

    public LoginAction(WebUserService userService) {
        this.service = userService;
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws IOException, ServletException {

        String username = request.getParameter("login");
        String password = request.getParameter("password");
        User user = null;
        try{
            LoginDto loginDto = new LoginDto(username, password);
            user = service.login(loginDto);
            if(user.getBlocked().equals(true)){
                logger.error("Blocked user: ", new BlockedUserException());
                throw new BlockedUserException();
            }
        }
        catch (BadCredentialsException | UnavailableException | BlockedUserException| SQLException exc){
            logger.error("Login error: ", exc);
            logger.debug("Login error: ", exc);
            request.setAttribute("error", exc.getMessage());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("username",user.getUsername());
        session.setAttribute("id",user.getId());
        session.setAttribute("role", user.getRole());
        session.setMaxInactiveInterval(7200);

        if(user.getRole().equals("STUDENT")){
            request.getRequestDispatcher("/jsp/studentMenu.jsp").forward(request, response);
        }
        else{
            System.out.println("ADMIN");
            request.getRequestDispatcher("/jsp/adminMenu.jsp").forward(request, response);
        }

    }
}