package controller.action;

import dto.AppliedStudentDto;
import dto.FacultyDto;
import entity.Application;
import entity.Faculty;
import exception.AddingStudentsException;
import exception.GetApplicationsException;
import exception.GetFacultiesException;
import exception.RemoveApplicationsException;
import service.AdminService;
import view.View;

import java.sql.SQLException;
import java.util.ArrayList;

public class FinalizeAction implements Action{
    private static Action action;
    private AdminService adminService;

    //Після фіналізації відомості система підраховує суму балів і визначає абітурієнтів, зарахованих
    //до навчального закладу на бюджетні місця, на контракт.

    private FinalizeAction(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void execute(View view) {
        ArrayList<String> faculties = null;
        view.print("facultyID \tStudentID\t\t\t\t\t\t\tmath ukrainian english history");
        try {
            faculties= adminService.getApplications();
            view.printList(faculties);
        }
        catch (GetApplicationsException e){
            view.error("Finalization error(SQL exception)");
        }
        ArrayList<Faculty> facultiesData = null;
        try {
            facultiesData = adminService.getFaculties();
        }
        catch (GetFacultiesException e){
            view.error(e.getMessage());
        }
        view.print("\nName Budget All");
        for (int i = 0; i < facultiesData.size(); i++) {
            view.print(facultiesData.get(i).getName() + " - "
                    + facultiesData.get(i).getBudgetPlacesAmount() + " - "
                    + facultiesData.get(i).getPlacesAmount());
        }

        String facultyName = view.getAnswer("Input faculty name to finalize");
        ArrayList<Application> facultyApplications = null;
        try {
            facultyApplications = adminService.getFacultyApplications(new FacultyDto(facultyName));
        }
        catch (GetApplicationsException e){
            view.error(e.getMessage());
        }
        for (Application apl : facultyApplications) {
            System.out.println(apl);
        }

        for (int i = 0; i < faculties.size(); i++) {
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
                                view.error(e.getMessage());
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
                                view.error(e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }

    public static Action getAction() {
        if (action == null) {
            action = new FinalizeAction(AdminService.getInstance());
        }
        return action;
    }
}
