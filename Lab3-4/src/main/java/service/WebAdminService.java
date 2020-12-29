package service;

import dao.ApplicationDao;
import dao.FacultyDao;
import dao.StudentsDao;
import dao.UserDao;
import dto.ApplicationDto;
import dto.FacultyDto;
import entity.Application;
import entity.User;
import exception.*;
import utilities.ConnectionsPool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class WebAdminService {
    private final UserDao userDao;
    private final FacultyDao facultyDao;
    private final ApplicationDao applicationDao;
    private final StudentsDao studentsDao;

    public WebAdminService() {
        userDao = new UserDao();
        applicationDao = new ApplicationDao();
        facultyDao = new FacultyDao();
        studentsDao = new StudentsDao();
    }


    public  void editFaculty(FacultyDto facultyDto) throws EditFacultiesException {
        try{
            facultyDao.setConnection(ConnectionsPool.getPool().getConnection());
            facultyDao.addFaculty(facultyDto);
            ConnectionsPool.getPool().releaseConnection(facultyDao.releaseConnection());
            System.out.println("edited faculty");
        }
        catch (SQLException e){
            throw new EditFacultiesException();
        }
    }

    public  void removeFaculty(FacultyDto facultyDto) throws DeleteFacultyException {
        try {
            facultyDao.setConnection(ConnectionsPool.getPool().getConnection());
            facultyDao.deleteFaculty(facultyDto);
            ConnectionsPool.getPool().releaseConnection(facultyDao.releaseConnection());
        }
        catch (SQLException e){
            throw new DeleteFacultyException();
        }
    }

    public ArrayList<User> getStudentList() throws GetStudentsException {
        ArrayList<User> students;
        try{
            studentsDao.setConnection(ConnectionsPool.getPool().getConnection());
            students = studentsDao.getStudentsList();
            ConnectionsPool.getPool().releaseConnection(studentsDao.releaseConnection());}
        catch (NullPointerException | SQLException e){
            throw new GetStudentsException();
        }
        return students;
    }

    public void blockStudent(String email, Boolean statement) throws ChangeStatementException {
        if(!email.equals("")){
        try{
            studentsDao.setConnection(ConnectionsPool.getPool().getConnection());
            studentsDao.block(email, statement);
            ConnectionsPool.getPool().releaseConnection(studentsDao.releaseConnection());
        }
        catch (SQLException |ChangeStatementException e){
            throw new ChangeStatementException();
        }}
        else {
            throw new ChangeStatementException();
        }
    }

    public void addStudentResults(ApplicationDto applicationDto){
        try{
            studentsDao.setConnection(ConnectionsPool.getPool().getConnection());
            applicationDao.setConnection(ConnectionsPool.getPool().getConnection());
            applicationDao.saveApplication(new Application(applicationDto.getFacultyID(),
                    studentsDao.getStudentID(applicationDto.getStudentEmail()) , applicationDto.getMathGrade(),
                    applicationDto.getUkrainianGrade(), applicationDto.getEnglishGrade(),
                    applicationDto.getHistoryGrade()));
            applicationDao.releaseConnection();
            studentsDao.releaseConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Application> getApplications(){
        ArrayList<Application> result = new ArrayList<>();
        try{
        applicationDao.setConnection(ConnectionsPool.getPool().getConnection());
        result = applicationDao.getApplicationsList();
        applicationDao.releaseConnection();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
