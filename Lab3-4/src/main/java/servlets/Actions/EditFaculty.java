package servlets.Actions;

import dto.FacultyDto;
import exception.EditFacultiesException;
import org.apache.log4j.Logger;
import service.WebAdminService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class EditFaculty implements Action{
    private WebAdminService webAdminService;
    final static Logger logger = Logger.getLogger(EditFaculty.class);

    public EditFaculty(WebAdminService webAdminService) {
        this.webAdminService = webAdminService;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws IOException, ServletException {
        String name = request.getParameter("Name");
        int budgetPlaces = Integer.parseInt(request.getParameter("BudgetPlacesAmmount"));
        int places = Integer.parseInt(request.getParameter("PlacesAmmount"));

        try{
            webAdminService.editFaculty(new FacultyDto(name, budgetPlaces, places));
            request.getRequestDispatcher("/jsp/adminMenu.jsp").forward(request, response);
        }
        catch (EditFacultiesException e) {
            logger.error("Error while editing faculties");
            request.getRequestDispatcher("/jsp/adminMenu.jsp").forward(request, response);
        }
    }
}
