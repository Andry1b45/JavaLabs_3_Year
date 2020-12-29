package service;

import dao.ApplicationDao;
import dao.FacultyDao;
import dao.UserDao;
import dto.FacultyDto;
import exception.ChangeStatementException;
import exception.EditFacultiesException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WebAdminUserServiceTest {
    private UserDao userDao;
    private FacultyDao facultyDao;
    private ApplicationDao applicationDao;
    private WebAdminService webAdminService;
    private WebAdminService webAdminServiceMock;

    @Before
    public void before(){
        webAdminServiceMock = mock(WebAdminService.class);
    }


    @Test
    public void testEditFaculty(){
        FacultyDto facultyDto = new FacultyDto("FICT");
        webAdminServiceMock.editFaculty(facultyDto);
        verify(webAdminServiceMock).editFaculty(facultyDto);
    }

    @Test
    public void whenRemoveFaculty(){
        FacultyDto facultyDto = new FacultyDto("FICT");
        webAdminServiceMock.removeFaculty(facultyDto);
        verify(webAdminServiceMock).removeFaculty(facultyDto);
    }





}
