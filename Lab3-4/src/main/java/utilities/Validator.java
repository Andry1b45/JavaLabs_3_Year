package utilities;

import entity.User;
import exception.*;

public class Validator {
    public static String usernameRegex = "[A-Za-z0-9_]+";
    public static String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    public static String passwordRegex = "[^.*(?=.{6,})(?=.*d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$]";
    public static String cityRegex = "[^0-9]*[^\\n\\t\\f\\r]*$";
    public static String regionRegex = "[^0-9]*[A-Za-z_]*$";
    public static String schoolRegex = "[A-Za-z_#№_0-9_]*$";


    public static void validateUsername(String username) throws BadUsernameException {
        if (!username.matches(usernameRegex) || username.isEmpty()) {
            throw new BadUsernameException();
        }
    }

    public static void validateName(String name) throws BadNameException {
        if (name.isEmpty()) {
            throw new BadNameException();
        }
    }

    public static void validateEmail(String email) throws BadEmailException {
        if (!email.matches(emailRegex) || email.isEmpty()) {
            throw new BadEmailException();
        }
    }
    public static void validatePassword(String password) throws BadPasswordException {
        if (!password.matches(passwordRegex) || password.isEmpty()) {
            throw new BadPasswordException();
        }
    }

    public static void validateRole(String role) throws BadRoleException{
        if (!role.equals("student") && !role.equals("admin")  || role.isEmpty()){
            throw new BadRoleException();
        }
    }
    public static void validateCity(String city) throws BadCityException{
        if (!city.matches(cityRegex) || city.isEmpty()) {
            throw new BadCityException();
        }
    }
    public static void validateRegion(String region) throws BadRegionException{
        if (!region.matches(regionRegex) || region.isEmpty()) {
            throw new BadRegionException();
        }
    }
    public static void validateSchool(String school) throws BadSchoolException{
        if (!school.matches(schoolRegex) || school.isEmpty()) {
            throw new BadSchoolException();
        }
    }
    public static void validateAttestatID(int attestatID) throws BadAttestatIdException{
        if (attestatID <= 0) {
            throw new BadAttestatIdException();
        }
    }
    public static void validateExamsId(int examsId) throws BadExamsIdException{
        if (examsId <= 0) {
            throw new BadExamsIdException();
        }
    }

    public static void validateBlocked(User user) throws BlockedUserException{
        if(user.getBlocked() == true){
            throw new BlockedUserException();
        }
    }
}
