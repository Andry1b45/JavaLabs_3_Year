package dao;

import dto.AppliedStudentDto;
import dto.FacultyDto;
import entity.Faculty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class FacultyDao extends GeneralDao{
    public ArrayList<String> getFaculties(int sort) {
        Connection connection = getConnection();
        String findByUsername = null;
        switch (sort){
            case 1: findByUsername = "select * from faculties"; break;
            case 2: findByUsername = "select * from faculties order by name"; break;
            case 3: findByUsername = "select * from faculties order by name desc"; break;
            case 4: findByUsername = "select * from faculties order by budgetplacesammount asc"; break;
            case 5: findByUsername = "select * from faculties order by budgetplacesammount desc"; break;
            case 6: findByUsername = "select * from faculties order by placesammount asc"; break;
            case 7: findByUsername = "select * from faculties order by placesammount desc"; break;
        }

        ArrayList<String> facultiesArray = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(findByUsername);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                String row = res.getString("id") + "  " + res.getString("name") + "\t\t"
                        + res.getString("budgetplacesammount")
                        + "\t\t\t   " + res.getString("placesammount");
                facultiesArray.add(row);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (String str:facultiesArray) {
            System.out.println(str);
        }
        return facultiesArray;
    }

    public ArrayList<Faculty> getFacultiesList(){
        Connection connection = getConnection();
        String getFaculties = "select * from faculties";
        ArrayList<Faculty> facultiesArray = new ArrayList<>();
        try{
            PreparedStatement pstmt = connection.prepareStatement(getFaculties);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                facultiesArray.add(
                        new Faculty(res.getInt("id"),
                        res.getString("name"),
                        res.getInt("budgetplacesammount"),
                        res.getInt("placesammount"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultiesArray;
    }

    public void addFaculty(FacultyDto facultyDto){
        Connection connection = getConnection();
        String addOrUpdateFaculty = "insert into faculties(name, budgetplacesammount, placesammount) values (?, ?, ?)"+
                "on conflict (name) do update set name = ?, budgetplacesammount = ?, placesammount =?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(addOrUpdateFaculty);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement.setString(1, facultyDto.getName());
            preparedStatement.setInt(2, facultyDto.getBudgetPlacesAmount());
            preparedStatement.setInt(3, facultyDto.getPlacesAmount());
            preparedStatement.setString(4, facultyDto.getName());
            preparedStatement.setInt(5, facultyDto.getBudgetPlacesAmount());
            preparedStatement.setInt(6, facultyDto.getPlacesAmount());
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void deleteFaculty(FacultyDto facultyDto){
        Connection connection = getConnection();
        String deleteFaculty = "delete from faculties where name = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(deleteFaculty);
            preparedStatement.setObject(1,facultyDto.getName());
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void decrementPlace(AppliedStudentDto appliedStudentDto){
        Connection connection = getConnection();
        final String decrementPlace;
        if(appliedStudentDto.getBudget() == 1){
            decrementPlace = "update faculties set budgetplacesammount = budgetplacesammount-1, " +
                    "placesammount = placesammount-1";
        }
        else{
            decrementPlace = "update faculties set placesammount = placesammount-1";
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(decrementPlace);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
