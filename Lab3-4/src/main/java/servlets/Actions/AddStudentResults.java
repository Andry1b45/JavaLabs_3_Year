package servlets.Actions;

import dto.ApplicationDto;
import exception.BadEmailException;
import org.apache.log4j.Logger;
import service.WebAdminService;
import service.WebUserService;
import utilities.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddStudentResults implements Action {
    private WebAdminService webAdminService;
    private WebUserService webUserService;
    private String email = "";
    private int faculty = 0;
    private String math;
    private String ukrainian;
    private String english;
    private String history;
    private HttpSession session;

    final static Logger logger = Logger.getLogger(AddStudentResults.class);

    public AddStudentResults(WebAdminService webAdminService, WebUserService webUserService) {
        this.webAdminService = webAdminService;
        this.webUserService = webUserService;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        try{
            email = request.getParameter("email");
            Validator.validateEmail(email);
            faculty = webUserService.convertFacultyName(request.getParameter("faculty"));
            math = request.getParameter("math");
            ukrainian = request.getParameter("ukrainian");
            english = request.getParameter("english");
            history =request.getParameter("history");

        }
        catch (BadEmailException exc){
            session = request.getSession(true);
            request.setAttribute("error", exc.getMessage());
            logger.error("Error while adding student results");
            request.getRequestDispatcher("/jsp/addResults.jsp").forward(request, response);
        }

        webAdminService.addStudentResults(new ApplicationDto(faculty, email, math, ukrainian, english, history));
        logger.info("Student results added successfully!");
        request.getRequestDispatcher("/jsp/adminMenu.jsp").forward(request, response);
    }
}





