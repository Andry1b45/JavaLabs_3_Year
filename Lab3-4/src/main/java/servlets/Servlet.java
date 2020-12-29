package servlets;

import entity.User;
import service.AdminService;
import service.UserService;
import service.WebAdminService;
import service.WebUserService;
import servlets.Actions.*;
import servlets.Actions.Action;

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
        "/menu", "/viewFaculties", "/sendApplication", "/editFaculty", "/removeFaculty", "/viewStudents",
        "/block", "/addStudentResults", "/viewApplications", "/finalize", "/language"
})
public class Servlet extends HttpServlet {
    private Map<String, Action> actions;

    @Override
    public void init(ServletConfig config) throws ServletException {
        actions = new HashMap<>();
        actions.put("/language", new LocaleChoosing());

        actions.put("/login", new LoginAction(new WebUserService()));
        actions.put("/register", new RegistrationAction(new WebUserService()));
        actions.put("/menu", new MenuAction());
        actions.put("/sendApplication", new SendApplication(new WebUserService()));
        actions.put("/viewFaculties", new ViewFaculties(new WebUserService()));

        actions.put("/editFaculty", new EditFaculty(new WebAdminService()));
        actions.put("/removeFaculty", new RemoveFaculty(new WebAdminService()));
        actions.put("/viewStudents", new ViewStudents(new WebAdminService()));
        actions.put("/block", new BlockStudent(new WebAdminService()));
        actions.put("/addStudentResults", new AddStudentResults(new WebAdminService(), new WebUserService()));
        actions.put("/viewApplications", new ViewApplications(new WebAdminService()));
        actions.put("/finalize", new FinalizeAction(new WebAdminService(), new AdminService()));
        actions.put("/logout", new LogOutAction());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        Action action = actions.get(path);
        if (action != null) {
            action.execute(req, resp, req.getServletContext());
        } else {

        }
    }
}