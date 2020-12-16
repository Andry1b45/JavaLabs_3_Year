package dao;

import dto.AppliedStudentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppliedStudentsDao extends GeneralDao{
    public String addNewStudent(AppliedStudentDto appliedStudentDto) {
        Connection connection = getConnection();
        String findAllApplications = "insert into appliedStudents (student_id, faculty_id, budget) values (?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(findAllApplications);
            pstmt.setObject(1, appliedStudentDto.getStudentID(), java.sql.Types.OTHER);
            pstmt.setObject(2, appliedStudentDto.getFacultyID(), java.sql.Types.OTHER);
            pstmt.setObject(3, appliedStudentDto.getBudget());
            ResultSet res = pstmt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "Student added!";
    }
}