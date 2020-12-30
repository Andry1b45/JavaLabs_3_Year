package service;

import dao.ApplicationDao;
import dao.FacultyDao;
import dao.UserDao;
import dto.ApplicationDto;
import dto.LoginDto;
import dto.RegisterDto;
import entity.Application;
import entity.Faculty;
import entity.User;
import org.junit.*;
import exception.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WebUserServiceTest {
    private UserDao userDao;
    private FacultyDao facultyDao;
    private ApplicationDao applicationDao;
    private WebUserService webUserService;
    private WebUserService webUserServiceMock;

    @Before
    public void before() {
        userDao = mock(UserDao.class);
        facultyDao = mock(FacultyDao.class);
        applicationDao = mock(ApplicationDao.class);
        webUserServiceMock = mock(WebUserService.class);
    }


    @Test(expected = BadCredentialsException.class)
    public void whenLoginBadCredentials() throws SQLException {
        LoginDto loginDto = new LoginDto("bersh", "false");
        when(userDao.findByUsername(loginDto.getUsername())).thenReturn(Optional.empty());
        assertNull(new WebUserService().login(loginDto));
        verify(userDao).findByUsername(loginDto.getUsername());
    }


    @Test
    public void testLogin() throws SQLException {
        LoginDto loginDto = new LoginDto("test", "pass");
        User user = new User(UUID.fromString("433d669c-c5d1-4cf5-bde7-d4d80688472a"), "test", "andrew", "andrew@gmail.com", false, "ADMIN");
        when(userDao.findByUsername(loginDto.getUsername())).thenReturn(Optional.of(user));
        User secondUser = new WebUserService().login(loginDto);
        assertNotNull(user);
        assertEquals(user, secondUser);
    }


    @Test
    public void whenRegisterNewUser() throws SQLException{
        RegisterDto registerDto = new RegisterDto("sampleTestUser", "full name",
                "sampletestUser@gmail.com", "password", "2");
        webUserServiceMock.register(registerDto);
        verify(webUserServiceMock).register(registerDto);
    }

    @Test(expected = UserAlreadyExistException.class)
    public void whenRegisterNewUserBadCredentials() throws SQLException{
        RegisterDto registerDto = new RegisterDto("testSample", "full name",
                "testUsr@gmail.com", "password", "2");
        new WebUserService().register(registerDto);
        verify(webUserService).register(registerDto);
    }

    @Test
    public void testSaveApplication() throws SQLException{
        ApplicationDto applicationDto = new ApplicationDto(1, UUID.fromString(
                "b6cc6a50-a70c-4048-8a9b-6d37048ea68f"), 10, 10,
                10, 10);
        Application application = Application.fromApplicationData(applicationDto);
        assertNotNull(application);
        applicationDao.saveApplication(application);
        verify(applicationDao).saveApplication(application);
    }

    @Test
    public void testSendApplication() throws SQLException{
        ApplicationDto applicationDto = new ApplicationDto(10, UUID.fromString(
                "b6cc6a50-a70c-4048-8a9b-6d37048ea68f"), 10, 10,
                10, 10);
        webUserServiceMock.sendApplication(applicationDto);
        verify(webUserServiceMock).sendApplication(applicationDto);
    }

    @Test
    public void whenconvertFacultyName() throws SQLException{
        assertEquals(new WebUserService().convertFacultyName("FICT"), 1);
    }


    @Test
    public void whenconvertWrongFacultyName() throws SQLException{
        assertNotEquals(new WebUserService().convertFacultyName("ФИОТ"), 1);
    }

    @Test
    public void testGettingFaculties() throws SQLException{
        ArrayList<Faculty> faculties = new WebUserService().getFaculties(1);
        assertNotNull(faculties);
    }
}

