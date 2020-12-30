package servlets.Actions;

import dto.ApplicationDto;
import exception.ApplicationException;

import org.apache.log4j.Logger;
import service.WebUserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;


public class SendApplication implements Action {
    private WebUserService service;
    final static Logger logger = Logger.getLogger(SendApplication.class);

    public SendApplication(WebUserService userService) {
        service = userService;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws IOException, ServletException {
        int faculty = service.convertFacultyName(request.getParameter("faculty"));
        String math = request.getParameter("math");
        String ukrainian = request.getParameter("ukrainian");
        String english = request.getParameter("english");
        String history =request.getParameter("history");
        HttpSession session = request.getSession(true);

        try{
            service.sendApplication(new ApplicationDto(String.valueOf(faculty),
                    UUID.fromString(session.getAttribute("id").toString()), math, ukrainian, english, history));
            request.getRequestDispatcher("/jsp/studentMenu.jsp").forward(request, response);
            logger.info(request.getSession().getAttribute("username") +
                    " sent application to " + request.getParameter("faculty"));
        }
        catch (ApplicationException exception) {
            logger.error("There were error occured while application sending");
            request.setAttribute("error", exception.getMessage());
            request.getRequestDispatcher("/jsp/sendApplication.jsp").forward(request, response);
        }
    }

}
