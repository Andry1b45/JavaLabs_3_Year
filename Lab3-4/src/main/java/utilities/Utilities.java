package utilities;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName =  "Utilities", urlPatterns = {"/viewFaculties", "/sendApplication", "/editFaculty",
        "/removeFaculty", "/viewStudents", "/block", "/addStudentResults", "/viewApplications", "/finalize"})
public class Utilities implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("role") != null) {
            //req.getSession().setAttribute("text_rus", "sessionLocale");
            filterChain.doFilter(req, res);
            } else {
                res.sendRedirect("/login");
            }
        }

        @Override
        public void destroy() {

        }
}
