package controller.useractions;

import java.util.HashMap;
import java.util.Map;


public class UserActions {
    public static Map<ActionType, String> getActions(String userType) {
        Map<ActionType, String> actions = new HashMap<>();
        switch (userType) {
            case "STUDENT":
                actions.put(ActionType.APPLICATION, "register on faculty");
                break;
            case "ADMIN":
                actions.put(ActionType.FACULTIES, "edit/remove faculties");
                actions.put(ActionType.STUDENTS, "block/unblock students");
                actions.put(ActionType.FINALIZE, "finalize statement");
                break;
            default:
                actions.put(ActionType.LOGIN, "login into system");
                actions.put(ActionType.REGISTER, "create new account");
                break;
        }
        actions.put(ActionType.EXIT, "close the program");
        return actions;
    }
}
