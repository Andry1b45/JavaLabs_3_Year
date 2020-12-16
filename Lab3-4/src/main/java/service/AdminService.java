package service;

import dao.ApplicationDao;
import dao.AppliedStudentsDao;
import dao.FacultyDao;
import dao.StudentsDao;
import dto.AppliedStudentDto;
import dto.FacultyDto;
import entity.Application;
import entity.Faculty;
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

    public ArrayList<String> getStudents() throws SQLException{
        studentsDao.setConnection(ConnectionsPool.getPool().getConnection());
        ArrayList<String> students = studentsDao.getStudents();
        ConnectionsPool.getPool().releaseConnection(studentsDao.releaseConnection());
        return students;
    }

    public String changeStudentStatement(UUID id, Boolean statement) throws SQLException{
        studentsDao.setConnection(ConnectionsPool.getPool().getConnection());
        studentsDao.changeStudentStatement(id, statement);
        String result = "Successfully changed student statement";
        ConnectionsPool.getPool().releaseConnection(studentsDao.releaseConnection());
        return result;
    }

    public  String editFaculties(FacultyDto facultyDto) throws SQLException {
        facultyDao.setConnection(ConnectionsPool.getPool().getConnection());
        facultyDao.addFaculty(facultyDto);
        String result = "Successfully added new faculty";
        ConnectionsPool.getPool().releaseConnection(facultyDao.releaseConnection());
        return result;
    }

    public ArrayList<Faculty> getFaculties() throws SQLException{
        facultyDao.setConnection(ConnectionsPool.getPool().getConnection());
        ArrayList <Faculty> result = facultyDao.getFacultiesList();
        return result;
    }

    public ArrayList<String> getApplications() throws SQLException{
        applicationDao.setConnection(ConnectionsPool.getPool().getConnection());
        ArrayList<String> applicationsArray = applicationDao.getApplications();
        ConnectionsPool.getPool().releaseConnection(applicationDao.releaseConnection());
        return applicationsArray;
    }

    public ArrayList<Application> getFacultyApplications(FacultyDto facultyDto) throws SQLException{
        applicationDao.setConnection(ConnectionsPool.getPool().getConnection());
        ArrayList<Application> applicationsArray = applicationDao.getApplicationList(facultyDto);
        ConnectionsPool.getPool().releaseConnection(applicationDao.releaseConnection());
        return applicationsArray;
    }

    public  String deleteFaculty(FacultyDto facultyDto) throws SQLException {
        facultyDao.setConnection(ConnectionsPool.getPool().getConnection());
        facultyDao.deleteFaculty(facultyDto);
        String result = "Successfully deleted faculty";
        ConnectionsPool.getPool().releaseConnection(facultyDao.releaseConnection());
        return result;
    }

    public String AddAppliedStudent(AppliedStudentDto appliedStudentDto) throws SQLException {
        appliedStudentsDao.setConnection(ConnectionsPool.getPool().getConnection());
        String result = appliedStudentsDao.addNewStudent(appliedStudentDto);
        ConnectionsPool.getPool().releaseConnection(appliedStudentsDao.releaseConnection());
        return result;
    }

    public void RemoveAllStudentApplications(AppliedStudentDto appliedStudentDto) throws SQLException {
        applicationDao.setConnection(ConnectionsPool.getPool().getConnection());
        applicationDao.removeAllStudentApplications(appliedStudentDto);
        ConnectionsPool.getPool().releaseConnection(applicationDao.releaseConnection());

        facultyDao.setConnection(ConnectionsPool.getPool().getConnection());
        facultyDao.decrementPlace(appliedStudentDto);
        ConnectionsPool.getPool().releaseConnection(facultyDao.releaseConnection());
    }

    public static AdminService getInstance() {
        if (instance == null) {
            instance = new AdminService();
        }
        return instance;
    }
}
