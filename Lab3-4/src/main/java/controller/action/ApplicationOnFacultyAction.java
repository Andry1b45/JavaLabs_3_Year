package controller.action;

import controller.UserAuthData;
import dto.ApplicationDto;
import service.UserService;
import view.View;

import java.sql.SQLException;
import java.util.ArrayList;


public class ApplicationOnFacultyAction implements Action{
    private static Action action;
    private UserService userService;

    private ApplicationOnFacultyAction(UserService userService) {
        this.userService = userService;
    }

    public void execute(View view) {
        ArrayList<String> faculties = null;
        view.print("1.Default sort\n2.Alphabetical sort ascending\n3.Alphabetical sort descending\n" +
                "4.Budget places sort ascending\n5.Budget places sort descending\n6.Places sort ascending" +
                "\n7.Places sort descending");
        int sort = Integer.parseInt(view.getAnswer("Input sort type:"));
        view.print("id name\tbudget places\tall places");
        try {
            faculties= userService.getFaculties(sort);
            view.printList(faculties);
        }
        catch (SQLException e){
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
        UserAuthData user = UserAuthData.getAuthData();
        String result = null;
        try {
            result = userService.sendApplication(new ApplicationDto(faculty, user.getId(), math, ukrainian, english,history));
        }
        catch (SQLException e){
            view.error(e.getMessage());
        }
        view.print(result);
    }


    public static Action getAction() {
        if (action == null) {
            action = new ApplicationOnFacultyAction(UserService.getInstance());
        }
        return action;
    }
}
