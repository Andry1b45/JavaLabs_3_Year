package controller.action;

import dto.FacultyDto;
import service.AdminService;
import service.UserService;
import view.View;

import java.sql.SQLException;
import java.util.ArrayList;


public class EditFacultiesAction implements Action {
    private static Action action;
    private UserService userService;
    private AdminService adminService;

    private EditFacultiesAction(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @Override
    public void execute(View view) {
        ArrayList<String> faculties = null;
        view.print("id name\tbudget places\tall places");
        try {
            faculties= userService.getFaculties(1);
            view.printList(faculties);
        }
        catch (SQLException e){
            view.error(e.getMessage());
        }

        view.print("1.Add new faculty\n2.Edit faculty\n3.Remove faculty\n4.Show all faculties");
        int option = Integer.parseInt(view.getAnswer(("Input option(1/2/3/4):")));

        String facultyName = null;
        int budgetPlacesAmmount = 0;
        int placesAmmount = 0;

        switch (option){
            case 1:{
                while (true){
                facultyName = view.getAnswer("Input faculty name:");
                if(!facultyName.equals("")) break;
                view.print("Wrong faculty name");
                }
                while (true){
                    budgetPlacesAmmount = Integer.parseInt(view.getAnswer("Input budget places amount:"));
                    placesAmmount = Integer.parseInt(view.getAnswer("Input places amount:"));
                    if(placesAmmount !=0 && budgetPlacesAmmount <= placesAmmount ) break;
                    view.print("Wrong places ammount");
                }
                String result = null;
                try {
                    result = adminService.editFaculties(new FacultyDto(facultyName, budgetPlacesAmmount, placesAmmount));
                }
                catch (SQLException e){
                    view.error(e.getMessage());
                }
                view.print(result);
                return;
            }

            case 2:{
                while (true){
                    facultyName = view.getAnswer("Input faculty name:");
                    if(!facultyName.equals("")) break;
                    view.print("Wrong faculty name");
                }
                String answer = view.getAnswer("Are you sure you want to edit " + facultyName + "?(y/n)");
                if(answer.equals("y")){
                    while (true){
                        budgetPlacesAmmount = Integer.parseInt(view.getAnswer("Input budget places amount:"));
                        placesAmmount = Integer.parseInt(view.getAnswer("Input places amount:"));
                        if(placesAmmount !=0 && budgetPlacesAmmount <= placesAmmount ) break;
                        view.print("Wrong places amount");
                    }
                    String result = null;
                    try {
                        result = adminService.editFaculties(new FacultyDto(facultyName, budgetPlacesAmmount, placesAmmount));
                    }
                    catch (SQLException e){
                        view.error(e.getMessage());
                    }
                    view.print(result);
                }
                return;
            }

            case 3:{
                while (true){
                    facultyName = view.getAnswer("Input faculty name:");
                    if(!facultyName.equals("")) break;
                    view.print("Wrong faculty name");
                }
                String answer = view.getAnswer("Are you sure you want to delete " + facultyName + "?(y/n)");
                if(answer.equals("y")){
                    String result = null;
                    try {
                        result = adminService.deleteFaculty(new FacultyDto(facultyName));
                    }
                    catch (SQLException e){
                        view.error(e.getMessage());
                    }
                    view.print(result);
                }
                return;
            }

            case 4:{
                view.print("id name\tbudget places\tall places");
                view.printList(faculties);
                return;
            }
        }

    }

    public static Action getAction() {
        if (action == null) {
            action = new EditFacultiesAction(UserService.getInstance() ,AdminService.getInstance());
        }
        return action;
    }
}
