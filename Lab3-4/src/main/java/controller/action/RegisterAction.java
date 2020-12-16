package controller.action;

import dto.RegisterDto;
import exception.*;
import service.UserService;
import utilities.Validator;
import view.View;
import java.sql.SQLException;

public class RegisterAction implements Action {
    private static Action action;
    private UserService userService;

    private RegisterAction(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(View view) {
        String username;
        String fullName;
        String email;
        String password;
        String repeatPassword;
        String role;
        String city;
        String region;
        String school;
        int attestat_id;
        int exams_id;
        String result;
        while (true) {
            while (true) {
                try {
                    username = view.getAnswer("Input username:");
                    Validator.validateUsername(username);
                    break;
                } catch (BadUsernameException e) {
                    view.error(e.getMessage());
                }
            }
            while (true){
                try {
                    fullName = view.getAnswer("Input full name:");
                    Validator.validateName(fullName);
                    break;
                }
                catch (BadNameException e){
                    view.error(e.getMessage());
                }

            }
            while (true) {
                try {
                    email = view.getAnswer("Input email:");
                    Validator.validateEmail(email);
                    break;
                } catch (BadEmailException e) {
                    view.error(e.getMessage());
                }
            }
            while (true) {
                try{
                    password = view.getAnswer("Input password:");
                    repeatPassword = view.getAnswer("Repeat password:");
                    if (password.equals(repeatPassword)) {
                        break;
                    }
                    view.error("Passwords are not equal!");
                    Validator.validatePassword(password);
                }
                catch (BadPasswordException e){
                    view.error(e.getMessage());
                }
            }
            while (true) {
                try {
                    role = view.getAnswer("Input your role(student/admin):");
                    Validator.validateRole(role);
                    break;
                } catch (BadRoleException e) {
                    view.error(e.getMessage());
                }
            }
            if(!role.equals("admin")){
                while (true) {
                    try {
                        city = view.getAnswer("Input your city:");
                        Validator.validateCity(city);
                        break;
                    } catch (BadCityException e) {
                        view.error(e.getMessage());
                    }
                }
                while (true) {
                    try {
                        region = view.getAnswer("Input your region:");
                        Validator.validateRegion(region);
                        break;
                    } catch (BadRegionException e) {
                        view.error(e.getMessage());
                    }
                }
                while (true) {
                    try {
                        school = view.getAnswer("Input your school:");
                        Validator.validateSchool(school);
                        break;
                    } catch (BadSchoolException e) {
                        view.error(e.getMessage());
                    }
                }
                while (true) {
                    try {
                        attestat_id = Integer.parseInt(view.getAnswer("Input your attestat ID number:"));
                        Validator.validateAttestatID(attestat_id);
                        break;
                    } catch (BadAttestatIdException e) {
                        view.error(e.getMessage());
                    }
                }
                while (true) {
                    try {
                        exams_id = Integer.parseInt(view.getAnswer("Input your exams_ID:"));
                        Validator.validateExamsId(exams_id);
                        break;
                    } catch (BadExamsIdException e) {
                        view.error(e.getMessage());
                    }
                }
            }
            try {
                result = userService.register(new RegisterDto(username, fullName, email, password, role));
                view.print(result);
                break;
            }
            catch (UserAlreadyExistException e) {
                view.error(e.getMessage());
            }
            catch (SQLException ignored) {
                view.error("Registration error(SQLException)");
            }
        }
    }

    public static Action getAction() {
        if (action == null) {
            action = new RegisterAction(UserService.getInstance());
        }
        return action;
    }
}
