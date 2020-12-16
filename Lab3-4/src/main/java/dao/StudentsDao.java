package dao;

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
            e.printStackTrace();
        }
    }
}
