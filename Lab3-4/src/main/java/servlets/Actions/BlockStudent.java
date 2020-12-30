package servlets.Actions;

        import exception.ChangeStatementException;
        import org.apache.log4j.Logger;
        import service.WebAdminService;

        import javax.servlet.ServletContext;
        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.Iterator;

public class BlockStudent implements Action{
    private WebAdminService webAdminService;
    final static Logger logger = Logger.getLogger(BlockStudent.class);
    public BlockStudent(WebAdminService webAdminService) {
        this.webAdminService = webAdminService;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws IOException, ServletException {
        String email = request.getParameter("email");
        Boolean statement = Boolean.parseBoolean(getStatement(request));
        try{
            webAdminService.blockStudent(email, statement);
            logger.info(email + " blocked!");
            request.getRequestDispatcher("/viewStudents").forward(request, response);
        }
        catch (ChangeStatementException e) {
            logger.error("Error while blocking student");
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/jsp/blockStudent.jsp").forward(request, response);
        }
    }

    private String getStatement(HttpServletRequest request) {
        String statementType = "none";
        Iterator<String> it = request.getParameterNames().asIterator();
        while (it.hasNext()) {
            String name = it.next();
            String[] values = request.getParameterValues(name);
            for (String value : values) {
                if (!value.equals("none"))
                    statementType = value;
            }
        }
        return statementType;
    }
}
