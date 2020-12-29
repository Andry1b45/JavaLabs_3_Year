package dao;

import dto.AppliedStudentDto;
import dto.FacultyDto;
import entity.Application;
import entity.Faculty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class ApplicationDao extends GeneralDao{
    public ArrayList<String> getApplications(){
        Connection connection = getConnection();
        String findAllApplications = "select * from facultyrequests";
        ArrayList<String> applicationsArray = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(findAllApplications);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                String row = res.getInt("facultyid") + "\t\t" + res.getString("studentid") +
                        "\t" + res.getInt("math") + "\t\t" + res.getInt("ukrainian") + "\t\t" +
                        res.getInt("english") + "\t\t" + res.getInt("history");
                applicationsArray.add(row);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return applicationsArray;
    }

    public ArrayList<Application> getApplicationList(FacultyDto facultyDto){
        Connection connection = getConnection();
        final String findApplicationsByFaculty = "select * from facultyrequests where facultyid = (select id from faculties " +
                "where name = ?) order by (math + ukrainian + english + history) desc";
        ArrayList<Application> applicationsArray = new ArrayList<>();
        try{
            PreparedStatement pstmt = connection.prepareStatement(findApplicationsByFaculty);
            pstmt.setString(1, facultyDto.getName());
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                applicationsArray.add(new Application(res.getInt("facultyid"),
                        UUID.fromString(res.getString("studentid")),res.getInt("math"),res.getInt("ukrainian"),
                        res.getInt("english"),res.getInt("history")));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return applicationsArray;
    }

    public ArrayList<Application> getApplicationsList(){
        Connection connection = getConnection();
        final String findApplicationsByFaculty = "select * from facultyrequests order by (math + ukrainian + english + history) desc";
        ArrayList<Application> applicationsArray = new ArrayList<>();
        try{
            PreparedStatement pstmt = connection.prepareStatement(findApplicationsByFaculty);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                applicationsArray.add(new Application(res.getInt("facultyid"),
                        UUID.fromString(res.getString("studentid")),res.getInt("math"),res.getInt("ukrainian"),
                        res.getInt("english"),res.getInt("history")));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        ArrayList<Application> result = new ArrayList<>();

       for(Application application: applicationsArray){
           int facultyId = application.getFacultyID();
           UUID studentId= application.getStudentID();

           final String findFacultyName = "select name from faculties where id=?";
           final String findStudentUsernameByID = "select username from users where id=?";

           String facultyName = null;
           String studentUsername = null;

           try{
               PreparedStatement pstmt = connection.prepareStatement(findFacultyName);
               pstmt.setInt(1, facultyId);
               ResultSet resultSet = pstmt.executeQuery();
               while(resultSet.next()){
                   facultyName = resultSet.getString("name");
               }
           }
           catch (SQLException throwables) {
               throwables.printStackTrace();
           }

           try {
               PreparedStatement pstmt = connection.prepareStatement(findStudentUsernameByID);
               pstmt.setObject(1, studentId, java.sql.Types.OTHER);
               ResultSet resultSet = pstmt.executeQuery();
               while(resultSet.next()){
                   studentUsername = resultSet.getString("username");
               }
           } catch (SQLException throwables) {
               throwables.printStackTrace();
           }

           result.add(new Application(facultyName, studentUsername, String.valueOf(application.getMathGrade()),
                   String.valueOf(application.getUkrainianGrade()),
                   String.valueOf(application.getEnglishGrade()), String.valueOf(application.getHistoryGrade())));
       }
       return result;
    }

    public void saveApplication(Application application){
        final String addNewApplication = "insert into facultyRequests(facultyId, studentId, math, ukrainian, " +
                "english, history) values  (?, ?, ?, ?, ?, ?)";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(addNewApplication);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement.setInt(1, application.getFacultyID());
            preparedStatement.setObject(2, application.getStudentID());
            preparedStatement.setInt(3, application.getMathGrade());
            preparedStatement.setInt(4, application.getUkrainianGrade());
            preparedStatement.setInt(5, application.getEnglishGrade());
            preparedStatement.setInt(6, application.getHistoryGrade());
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public String removeAllStudentApplications(AppliedStudentDto appliedStudentDto){
        final String removeAllApplicationsByID = "delete from facultyrequests where studentid = ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(removeAllApplicationsByID);
            preparedStatement.setObject(1, appliedStudentDto.getStudentID(), java.sql.Types.OTHER);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return "All applications had been removed";
    }
}
