package service;

import controller.UserAuthData;
import entity.Application;
import utilities.ConnectionsPool;
import dao.*;
import dto.*;
import entity.User;
import exception.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UserService {
    private static UserService instance;
    private final UserDao userDao;
    private final ApplicationDao applicationDao;
    private final FacultyDao facultyDao;

    private UserService() {
        userDao = new UserDao();
        applicationDao = new ApplicationDao();
        facultyDao = new FacultyDao();
    }

    public String login(LoginDto credentials) throws BadCredentialsException, SQLException {
        userDao.setConnection(ConnectionsPool.getPool().getConnection());
        Optional<User> user = userDao.findByUsername(credentials.getUsername());
        ConnectionsPool.getPool().releaseConnection(userDao.releaseConnection());
        if (user.isPresent()) {
            User userData = user.get();
            if (userData.getPassword().equals(credentials.getPassword())) {
                UserAuthData.setAuthData(userData.getId(), userData.getUsername(), userData.getRole());
                return "Login success!";
            }
        }
        throw new BadCredentialsException();
    }

    public String register(RegisterDto registerData) throws SQLException, UnavailableException {
        userDao.setConnection(ConnectionsPool.getPool().getConnection());
        if (userDao.findByUsername(registerData.getUsername()).isPresent()) {
            throw new UserAlreadyExistException();
        }
        userDao.save(User.fromRegisterData(registerData));
        ConnectionsPool.getPool().releaseConnection(userDao.releaseConnection());
        return "Register success!";
    }

    public ArrayList<String> getFaculties(int sort) throws GetFacultiesException {
        ArrayList<String> faculties;
        try {
            facultyDao.setConnection(ConnectionsPool.getPool().getConnection());
            faculties = facultyDao.getFaculties(sort);
            ConnectionsPool.getPool().releaseConnection(facultyDao.releaseConnection());
        }
        catch (SQLException e){
            throw new GetFacultiesException();
        }
        return faculties;
    }

    public String sendApplication(ApplicationDto applicationData) throws ApplicationException {
        try{
        applicationDao.setConnection(ConnectionsPool.getPool().getConnection());
        applicationDao.saveApplication(Application.fromApplicationData(applicationData)); //todo сделать нормальные названия
        ConnectionsPool.getPool().releaseConnection(userDao.releaseConnection());
        }
        catch (SQLException e){
            throw new ApplicationException();
        }
        String result = "Succesfully sended application";
        return  result;
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

}
