package controller;

import view.View;
import view.ViewContext;
import controller.action.*;
import controller.useractions.*;
import exception.UnsupportedActionException;
import java.util.Map;

public class Controller {
    View view;

    public Controller() {
        this.view = new ViewContext(System.in, System.out);
    }

    public void run() {
        Action action;
        UserAuthData user;
        while (true) {
            user = UserAuthData.getAuthData();
            Map<ActionType, String> actions = UserActions.getActions(user.getRole());
            actions.forEach((key, value) -> {
                view.print(key.toString().toLowerCase() + " - " + value);
            });
            String actionId = view.getAnswer(user.getUsername() + ": ");
            if (actionId.equals("exit")) {
                return;
            }
            try {
                action = ActionFactory.getActionByNumber(actionId.toUpperCase());
                action.execute(view);
            } catch (UnsupportedActionException e) {
                view.error(e.getMessage());
                view.print("Please, try again");
            }
        }
    }

}
