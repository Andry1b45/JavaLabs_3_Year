package service;

import dao.ApplicationDao;
import dao.AppliedStudentsDao;
import dao.FacultyDao;
import dao.StudentsDao;
import dto.AppliedStudentDto;
import dto.FacultyDto;
import entity.Application;
import entity.Faculty;
import exception.*;
import utilities.ConnectionsPool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class AdminService {
    private static AdminService instance;
    private final ApplicationDao applicationDao;
    private final FacultyDao facultyDao;
    private final StudentsDao studentsDao;
    private final AppliedStudentsDao appliedStudentsDao;

    private AdminService(){
        applicationDao = new ApplicationDao();
        facultyDao = new FacultyDao();
        studentsDao = new StudentsDao();
        appliedStudentsDao = new AppliedStudentsDao();
    }

    public ArrayList<String> getStudents() throws GetStudentsException {
        ArrayList<String> students;
        try{
        studentsDao.setConnection(ConnectionsPool.getPool().getConnection());
        students = studentsDao.getStudents();
        ConnectionsPool.getPool().releaseConnection(studentsDao.releaseConnection());}
        catch (NullPointerException | SQLException e){
            throw new GetStudentsException();
        }
        return students;
    }

    public String changeStudentStatement(UUID id, Boolean statement) throws ChangeStatementException{
        String result = "Successfully changed student statement";
        try{
        studentsDao.setConnection(ConnectionsPool.getPool().getConnection());
        studentsDao.changeStudentStatement(id, statement);
        ConnectionsPool.getPool().releaseConnection(studentsDao.releaseConnection());
        }
        catch (SQLException e){
            throw new ChangeStatementException();
        }
        return result;
    }

    public  String editFaculties(FacultyDto facultyDto) throws EditFacultiesException {
        String result = "Successfully added new faculty";
        try{
        facultyDao.setConnection(ConnectionsPool.getPool().getConnection());
        facultyDao.addFaculty(facultyDto);
        ConnectionsPool.getPool().releaseConnection(facultyDao.releaseConnection());
        }
        catch (SQLException e){
            throw new EditFacultiesException();
        }
        return result;
    }

    public ArrayList<Faculty> getFaculties() throws GetFacultiesException{
        ArrayList <Faculty> result;
        try{
        facultyDao.setConnection(ConnectionsPool.getPool().getConnection());
        result = facultyDao.getFacultiesList();
        }
        catch (SQLException e){
            throw new GetFacultiesException();
        }
        return result;
    }

    public ArrayList<String> getApplications() throws GetApplicationsException{
        ArrayList<String> applicationsArray;
        try {
            applicationDao.setConnection(ConnectionsPool.getPool().getConnection());
             applicationsArray = applicationDao.getApplications();
            ConnectionsPool.getPool().releaseConnection(applicationDao.releaseConnection());
        }
        catch (SQLException e){
            throw new GetApplicationsException();
        }
        return applicationsArray;
    }

    public ArrayList<Application> getFacultyApplications(FacultyDto facultyDto) throws GetApplicationsException{
        ArrayList<Application> applicationsArray;
        try{
            applicationDao.setConnection(ConnectionsPool.getPool().getConnection());
            applicationsArray = applicationDao.getApplicationList(facultyDto);
            ConnectionsPool.getPool().releaseConnection(applicationDao.releaseConnection());
        }
        catch (SQLException e){
            throw new GetApplicationsException();
        }
        return applicationsArray;
    }

    public  String deleteFaculty(FacultyDto facultyDto) throws DeleteFacultyException {
        try {
            facultyDao.setConnection(ConnectionsPool.getPool().getConnection());
            facultyDao.deleteFaculty(facultyDto);
            ConnectionsPool.getPool().releaseConnection(facultyDao.releaseConnection());
        }
        catch (SQLException e){
            throw new DeleteFacultyException();
        }

        String result = "Successfully deleted faculty";
        return result;
    }

    public String AddAppliedStudent(AppliedStudentDto appliedStudentDto) throws AddingStudentsException {
        String result;
        try{
        appliedStudentsDao.setConnection(ConnectionsPool.getPool().getConnection());
        result = appliedStudentsDao.addNewStudent(appliedStudentDto);
        ConnectionsPool.getPool().releaseConnection(appliedStudentsDao.releaseConnection());
        }
        catch (SQLException e){
            throw new AddingStudentsException();
        }
        return result;
    }

    public void RemoveAllStudentApplications(AppliedStudentDto appliedStudentDto) throws RemoveApplicationsException {
        try{
        applicationDao.setConnection(ConnectionsPool.getPool().getConnection());
        applicationDao.removeAllStudentApplications(appliedStudentDto);
        ConnectionsPool.getPool().releaseConnection(applicationDao.releaseConnection());

        facultyDao.setConnection(ConnectionsPool.getPool().getConnection());
        facultyDao.decrementPlace(appliedStudentDto);
        ConnectionsPool.getPool().releaseConnection(facultyDao.releaseConnection());}
        catch (SQLException e){
            throw new RemoveApplicationsException();
        }
    }

    public static AdminService getInstance() {
        if (instance == null) {
            instance = new AdminService();
        }
        return instance;
    }
}
