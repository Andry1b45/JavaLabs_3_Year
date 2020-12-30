package service;
import dao.ApplicationDao;
import dao.FacultyDao;
import dto.ApplicationDto;
import entity.Application;
import entity.Faculty;
import exception.*;
import utilities.ConnectionsPool;
import dao.UserDao;
import dto.LoginDto;
import dto.RegisterDto;
import entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class WebUserService {

    private final UserDao userDao;
    private final FacultyDao facultyDao;
    private final ApplicationDao applicationDao;

    public WebUserService() {
        userDao = new UserDao();
        applicationDao = new ApplicationDao();
        facultyDao = new FacultyDao();
    }


    public User login(LoginDto credentials) throws BadCredentialsException, SQLException {
        Optional<User> user;
        userDao.setConnection(ConnectionsPool.getPool().getConnection());
        user = userDao.findByUsername(credentials.getUsername());
        ConnectionsPool.getPool().releaseConnection(userDao.closeConnection());
        if (user.isPresent()) {
            User userData = user.get();
            if (userData.getPassword().equals(credentials.getPassword())) {
                return userData;
            }
        }
        throw new BadCredentialsException();
    }

    public void register(RegisterDto registerData) throws SQLException, UnavailableException {
        userDao.setConnection(ConnectionsPool.getPool().getConnection());
        if (userDao.findByUsername(registerData.getUsername()).isPresent()) {
            throw new UserAlreadyExistException();
        }
        userDao.save(User.fromRegisterData(registerData));
        ConnectionsPool.getPool().releaseConnection(userDao.closeConnection());
    }


    public ArrayList<Faculty> getFaculties(int sort) throws GetFacultiesException {
        ArrayList<Faculty> faculties;
        try {
            facultyDao.setConnection(ConnectionsPool.getPool().getConnection());
            faculties = facultyDao.getFacultiesList(sort);
            ConnectionsPool.getPool().releaseConnection(facultyDao.closeConnection());
        }
        catch (SQLException e){
            throw new GetFacultiesException();
        }
        return faculties;
    }

    public void sendApplication(ApplicationDto applicationData) throws ApplicationException {
        try{
            applicationDao.setConnection(ConnectionsPool.getPool().getConnection());
            applicationDao.saveApplication(Application.fromApplicationData(applicationData));
            ConnectionsPool.getPool().releaseConnection(userDao.closeConnection());
        }
        catch (SQLException e){
            throw new ApplicationException();
        }
    }

    public int convertFacultyName(String facultyName){
        facultyName = facultyName.toUpperCase();
        int facultyId = 0;
        try{
        facultyDao.setConnection(ConnectionsPool.getPool().getConnection());
        facultyId = facultyDao.getFacultyByName(facultyName);
        ConnectionsPool.getPool().releaseConnection(facultyDao.closeConnection());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return facultyId;
    }

}
