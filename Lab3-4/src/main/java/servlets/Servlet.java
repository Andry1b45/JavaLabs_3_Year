package servlets;


import service.UserService;
import servlets.Actions.Action;
import servlets.Actions.LoginAction;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MainServlet", urlPatterns = {
        "/register", "/login", "/logout",
        "/home", "/newapplication", "/editfaculty", "/editstudents", "/finalize"
})
public class Servlet extends HttpServlet {
    private Map<String, Action> actions;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        actions = new HashMap<>();
        actions.put("/login", new LoginAction(userService));

        //actions.put("/home", new HomeAction());
        //actions.put("/register", new RegisterAction(userService));
        //actions.put("/newapplication", userService.sendApplication());
        //actions.put("/order", new OrderAction(orderService));
        //actions.put("/logout", new LogOutAction());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        Action action = actions.get(path);
        if (action != null) {
            action.execute(req, resp, req.getServletContext());
        } else {
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }
}