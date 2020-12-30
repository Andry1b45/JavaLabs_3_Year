package service;

import dao.ApplicationDao;
import dao.FacultyDao;
import dao.StudentsDao;
import dao.UserDao;
import dto.ApplicationDto;
import dto.FacultyDto;
import exception.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class WebAdminServiceTest {


    @Before
    public void before(){
    }


    @Test(expected = DeleteFacultyException.class)
    public void whenRemoveEmptyFaculty(){
        FacultyDto facultyDto = new FacultyDto("");
        new WebAdminService().removeFaculty(facultyDto);
    }

    @Test(expected = ChangeStatementException.class)
    public void whenBlockWrongStudent(){
        new WebAdminService().blockStudent("", true);
    }


    @Test
    public void addStudentResults(){
        new WebAdminService().addStudentResults(new ApplicationDto(1, "andry1b45@gmail.com",
                "11" , "10", "9", "9"));
    }


}
