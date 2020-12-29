package dao;

import entity.User;
import exception.ChangeStatementException;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;


public class StudentsDao extends GeneralDao {
    public ArrayList<String> getStudents() {
        Connection connection = getConnection();
        String findAllUsers = "select * from users";
        ArrayList<String> studentsArray = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(findAllUsers);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                String row = res.getString("id") + "  " + res.getString("username") + "\t\t\t"
                        + res.getString("blocked");
                studentsArray.add(row);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return studentsArray;
    }

    public ArrayList<User> getStudentsList() {
        Connection connection = getConnection();
        String findAllUsers = "select * from users";
        ArrayList<User> studentsArray = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(findAllUsers);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                studentsArray.add(new User(UUID.fromString(res.getString("id")),
                        res.getString("username"),
                        res.getString("full_name"),
                        res.getString("email"),
                        res.getBoolean("blocked"),
                        res.getInt("role")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return studentsArray;
    }


    public void changeStudentStatement(UUID id, Boolean statement){
        Connection connection = getConnection();
        String changeStudentStatement = "update users set blocked=? where id=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(changeStudentStatement);
            preparedStatement.setBoolean(1, statement);
            preparedStatement.setObject(2, id, java.sql.Types.OTHER);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ChangeStatementException();
        }
    }



    public void block(String  email, Boolean statement){
        Connection connection = getConnection();
        String changeStudentStatement = "update users set blocked=? where email=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(changeStudentStatement);
            preparedStatement.setBoolean(1, statement);
            preparedStatement.setString(2, email);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ChangeStatementException();
        }
    }

    public UUID getStudentID(String email){
        UUID id = null;
        Connection connection = getConnection();
        Boolean status = null;
        final String getBool = "select id from users where email=?";
        try{
            PreparedStatement pstmt = connection.prepareStatement(getBool);
            pstmt.setString(1, email);
            ResultSet res = pstmt.executeQuery();
            if(res.next()){
                id = UUID.fromString(res.getString("id"));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(id);
        return id;
    }

}



