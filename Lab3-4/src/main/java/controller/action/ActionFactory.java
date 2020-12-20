package controller.action;

import controller.useractions.ActionType;
import exception.UnsupportedActionException;

public class ActionFactory {
    public static Action getActionByName(String action) throws UnsupportedActionException {
        ActionType actionType;
        try {
            actionType = ActionType.valueOf(action);
        } catch (IllegalArgumentException e) {
            throw new UnsupportedActionException();
        }
        switch (actionType) {
            case LOGIN:
                return LoginAction.getAction();
            case REGISTER:
                return RegisterAction.getAction();
            case APPLICATION:
                return ApplicationOnFacultyAction.getAction();
            case FACULTIES:
                return EditFacultiesAction.getAction();
            case STUDENTS:
                return EditStudentsAction.getAction();
            case FINALIZE:
                return FinalizeAction.getAction();
            default:
                throw new UnsupportedActionException();
        }
    }
}
