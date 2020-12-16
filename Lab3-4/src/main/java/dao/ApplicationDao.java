package dao;

import dto.AppliedStudentDto;
import dto.FacultyDto;
import entity.Application;

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

    public ArrayList<Application> getApplicationList(FacultyDto facultyDto) throws SQLException {
        Connection connection = getConnection();
        final String findApplicationsByFaculty = "select * from facultyrequests where facultyid = (select id from faculties " +
                "where name = ?) order by (math + ukrainian + english + history) desc";
        ArrayList<Application> applicationsArray = new ArrayList<>();
        PreparedStatement pstmt = connection.prepareStatement(findApplicationsByFaculty);
        pstmt.setString(1, facultyDto.getName());
        ResultSet res = pstmt.executeQuery();
        while (res.next()) {
            applicationsArray.add(new Application(res.getInt("facultyid"),
                    UUID.fromString(res.getString("studentid")),res.getInt("math"),res.getInt("ukrainian"),
                    res.getInt("english"),res.getInt("history")));
        }
        return applicationsArray;
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
