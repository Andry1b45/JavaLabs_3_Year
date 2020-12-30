package servlets.Actions;

import dto.RegisterDto;
import exception.*;
import org.apache.log4j.Logger;
import service.UserService;
import service.WebUserService;
import utilities.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class RegistrationAction implements Action {
    private static controller.action.Action action;
    WebUserService webUserService;
    final static Logger logger = Logger.getLogger(RegistrationAction.class);
    public RegistrationAction(WebUserService webUserService) {
        this.webUserService = webUserService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws IOException, ServletException {
        String username ="";
        String fullName = "";
        String email = "";
        String password ="";
        String repeatPassword ="";
        String role = "";
        String city = "";
        String region = "";
        String school = "";
        int attestat_id = 0 ;
        int exams_id = 0;
        String result = "";

        try{
        username =  request.getParameter("username");;
        Validator.validateUsername(username);

        fullName =  request.getParameter("fullName");
        Validator.validateName(fullName);


        email = request.getParameter("email");
        Validator.validateEmail(email);


        password =  request.getParameter("password");
        repeatPassword =  request.getParameter("repeatPassword");
        if(!password.equals(repeatPassword)) throw new BadPasswordException();

        role =  request.getParameter("role");
        }
        catch (BadUsernameException | BadNameException | BadEmailException | BadPasswordException exc){
            request.setAttribute("error", exc.getMessage());
            request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
        }

        if(role.equals("1")) {
            try {
                city = request.getParameter("city");
                Validator.validateCity(city);

                region = request.getParameter("region");
                Validator.validateRegion(region);

                school = request.getParameter("school");
                Validator.validateSchool(school);

                attestat_id = Integer.parseInt(request.getParameter("attestatId"));
                Validator.validateAttestatID(attestat_id);

                exams_id = Integer.parseInt(request.getParameter("examsId"));
                Validator.validateExamsId(exams_id);
            }
            catch (BadCityException | BadRegionException | BadSchoolException | BadAttestatIdException|
                    BadExamsIdException exc){
                request.setAttribute("error", exc.getMessage());
                request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
            }


            try {
                webUserService.register(new RegisterDto(username, fullName, email, password, role, city, region,
                        school, attestat_id, exams_id));
                logger.info("Created new user " + username);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } catch (SQLException e) {
                logger.error("Error creating new user");
            }
        }

        try {
            webUserService.register(new RegisterDto(username, fullName, email, password, role));
            logger.info("Created new user " + username);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (SQLException e) {
            logger.error("Error creating new user");
        }
    }
}
