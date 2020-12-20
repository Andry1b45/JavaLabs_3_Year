package controller.action;

import dto.ApplicationDto;
import exception.ApplicationException;
import exception.ChangeStatementException;
import exception.GetFacultiesException;
import exception.GetStudentsException;
import service.AdminService;
import service.UserService;
import view.View;

import java.util.ArrayList;
import java.util.UUID;

public class EditStudentsAction implements Action {
    private static Action action;
    private AdminService adminService;
    private UserService userService;

    private EditStudentsAction(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @Override
    public void execute(View view) {
        view.print("1.Block/Unblock student\n2.Add student results\n3.Show all students");
        int option = Integer.parseInt(view.getAnswer(("Input option(1/2):")));
        String result = null;
        switch (option){
            case 1:{
                ArrayList<String> students = null;
                view.print("\t\t\t\tid\t\t\t\t\t\tname\tblocked");
                try {
                    students = adminService.getStudents();
                    view.printList(students);
                }
                catch (GetStudentsException e){
                    view.error(e.getMessage());
                }
                UUID studentID = UUID.fromString(view.getAnswer("Input student ID:"));
                Boolean parameter = Boolean.parseBoolean(view.getAnswer("Block or unblock student?(true/false)"));
                try {
                    result = adminService.changeStudentStatement(studentID, parameter);
                } catch (ChangeStatementException e) {
                   view.error(e.getMessage());
                }
                view.print(result);
                break;
            }

            case 2:{
                ArrayList<String> students = null;
                view.print("\t\t\t\tid\t\t\t\t\t\tname\tblocked");
                try {
                    students = adminService.getStudents();
                    view.printList(students);
                }
                catch (NullPointerException | GetStudentsException e){
                    view.error(e.getMessage());
                }
                UUID studentID = UUID.fromString(view.getAnswer("Input student ID:"));
                ArrayList<String> faculties = new ArrayList<>();
                view.print("id name\tbudget places\tall places");
                try {
                    faculties= userService.getFaculties(1);
                    view.printList(faculties);
                }
                catch (GetFacultiesException e){
                    view.error(e.getMessage());
                }
                int faculty = 0;
                String inputFaculty = view.getAnswer("Input faculty:");
                for (int i = 0; i < faculties.size(); i++) {
                    if(faculties.get(i).contains(inputFaculty.toUpperCase())){
                        faculty = Integer.parseInt(String.valueOf(faculties.get(i).charAt(0)));
                    }
                }
                int math = Integer.parseInt(view.getAnswer("Input math grade:"));
                int ukrainian = Integer.parseInt(view.getAnswer("Input ukrainian language grade:"));
                int english = Integer.parseInt(view.getAnswer("Input english language grade:"));
                int history = Integer.parseInt(view.getAnswer("Input history grade:"));
                try {
                    result = userService.sendApplication(new ApplicationDto(faculty, studentID, math, ukrainian,
                            english,history));
                }
                catch (ApplicationException e){
                    view.error(e.getMessage());
                }
                view.print(result);
                break;
            }

            case 3:{
                ArrayList<String> students = null;
                view.print("\t\t\t\tid\t\t\t\t\t\tname\tblocked");
                try {
                    students = adminService.getStudents();
                    view.printList(students);
                }
                catch (GetStudentsException e){
                    view.error(e.getMessage());
                }
                break;
            }
        }

    }

    public static Action getAction() {
        if (action == null) {
            action = new EditStudentsAction(AdminService.getInstance(), UserService.getInstance());
        }
        return action;
    }
}
