package controller.action;

import dto.LoginDto;
import exception.BadCredentialsException;
import service.UserService;
import view.View;

import java.sql.SQLException;

public class LoginAction implements Action {
    private static Action action;
    private UserService userService;

    private LoginAction(UserService service) {
        this.userService = service;
    }

    @Override
    public void execute(View view) {
        String username = view.getAnswer("Input username:");
        String password = view.getAnswer("Input password:");
        try {
            view.print(userService.login(new LoginDto(username, password)));
        } catch (BadCredentialsException e) {
            view.error(e.getMessage());
        } catch (SQLException ignored) {
            view.error("Connection error(SQLException)");
        }
    }

    public static Action getAction() {
        if (action == null) {
            action = new LoginAction(UserService.getInstance());
        }
        return action;
    }
}
