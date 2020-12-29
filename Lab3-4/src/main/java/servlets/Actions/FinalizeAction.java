package servlets.Actions;

import dto.AppliedStudentDto;
import dto.FacultyDto;
import entity.Application;
import entity.Faculty;
import exception.*;
import service.AdminService;
import service.WebAdminService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class FinalizeAction implements Action {
    private WebAdminService webAdminService;
    private AdminService adminService;

    public FinalizeAction(WebAdminService webAdminService, AdminService adminService) {
        this.webAdminService = webAdminService;
        this.adminService = adminService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws IOException, ServletException {

        String facultyName = request.getParameter("facultyName");
        ArrayList<String> faculties = null;
        try {
            faculties= adminService.getApplications();
        }
        catch (GetApplicationsException e){
            e.printStackTrace();
        }
        ArrayList<Faculty> facultiesData = null;
        try {
            facultiesData = adminService.getFaculties();
        }
        catch (GetFacultiesException e){
            e.printStackTrace();
        }

        ArrayList<Application> facultyApplications = null;
        try {
            facultyApplications = adminService.getFacultyApplications(new FacultyDto(facultyName));
        }
        catch (GetApplicationsException e){
            e.printStackTrace();
        }


        for (int i = 0; i < faculties.size()*2-1; i++) {
            System.out.println("iteration " + i + ":" + facultiesData.get(i).getName() + " " + facultyName);
            if ((facultiesData.get(i).getName()).equals(facultyName)){
                if(facultiesData.get(i).getPlacesAmount() > 0){
                    if(facultiesData.get(i).getBudgetPlacesAmount() > 0){
                        for (int j = 0; j < facultyApplications.size(); j++) {
                            AppliedStudentDto appliedStudentDto = new AppliedStudentDto(
                                    facultyApplications.get(j).getStudentID(),
                                    facultyApplications.get(j).getFacultyID(),
                                    1);
                            try {
                                adminService.AddAppliedStudent(appliedStudentDto);
                                adminService.RemoveAllStudentApplications(appliedStudentDto);
                            }
                            catch (RemoveApplicationsException | AddingStudentsException e){
                                request.setAttribute("error", e.getMessage());
                                request.getRequestDispatcher("/jsp/finalize.jsp").forward(request, response);
                            }
                        }
                    }
                    else {
                        for (int j = 0; j < facultyApplications.size(); j++) {
                            AppliedStudentDto appliedStudentDto = new AppliedStudentDto(
                                    facultyApplications.get(j).getStudentID(),
                                    facultyApplications.get(j).getFacultyID(),
                                    2);
                            try{
                                adminService.AddAppliedStudent(appliedStudentDto);
                                adminService.RemoveAllStudentApplications(appliedStudentDto);
                            }
                            catch (AddingStudentsException | RemoveApplicationsException e){
                                request.setAttribute("error", e.getMessage());
                                request.getRequestDispatcher("/jsp/finalize.jsp").forward(request, response);
                            }
                        }
                    }
                }
            }
        }

        request.getRequestDispatcher("/jsp/adminMenu.jsp").forward(request, response);
    }
}

