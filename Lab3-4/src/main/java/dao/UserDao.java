package dao;

import entity.User;
import exception.BlockedUserException;
import utilities.Validator;
import java.sql.*;
import java.util.*;

public class UserDao extends GeneralDao {
    public Optional<User> findByUsername(String username) {
        final String findByUsername = "select * from users where username=?";
        Connection connection = getConnection();
        User user = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(findByUsername);
            pstmt.setString(1, username);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                user = new User(
                        UUID.fromString(res.getString("id")),
                        res.getString("username"),
                        res.getString("full_name"),
                        res.getString("email"),
                        res.getString("password"),
                        res.getBoolean("blocked"),
                        res.getInt("role"),
                        res.getString("city"),
                        res.getString("region"),
                        res.getString("school"),
                        res.getInt("attestat_id"),
                        res.getInt("exams_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user == null ? Optional.empty() : Optional.of(user);
    }

    public void save(User user) {
        final String addNewUser = "insert into users(id, username, full_name, email, password, blocked, role, city," +
                " region, school, attestat_id, exams_id) values  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final String updateUser = "update users set id = ?, username = ?, full_name = ?, email = ?, password = ?, " +
                "blocked = ?, role = ?, city = ?, region = ?, school = ?, attestat_id = ?, exams_id = ? where id = ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
            try {
                preparedStatement = connection.prepareStatement(addNewUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                preparedStatement = connection.prepareStatement(updateUser);
                preparedStatement.setString(6, user.getId().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            preparedStatement.setObject(1, user.getId(), java.sql.Types.OTHER);
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getFull_name());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setBoolean(6, false);
            preparedStatement.setInt(7, Integer.parseInt(user.getRole()));
            preparedStatement.setString(8, user.getCity());
            preparedStatement.setString(9, user.getRegion());
            preparedStatement.setString(10, user.getSchool());
            preparedStatement.setInt(11, user.getAttestat_id());
            preparedStatement.setInt(12, user.getExams_id());
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
