package org.longbox.integration.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.EmailDoesNotExistException;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.exception.UserNameDoesNotExistException;
import org.longbox.businesslogic.exception.UsernameOrEmailExistsException;
import org.longbox.businesslogic.service.UserService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.domainobjects.entity.User;
import org.longbox.domainobjects.mapper.UserMapper;
import org.longbox.persistence.dao.UserDaoImpl;

import java.util.*;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {

    UserService userService;
    UserDto u1;

    @BeforeEach
    void setup() {
        userService = new UserService(new UserDaoImpl(HibernateUtils.getSessionFactory()));
        u1 = new UserDto();
        u1.setId(1L);
        u1.setUserName("Always_Scheming");
        u1.setFirstName("John");
        u1.setLastName("Smith");
        u1.setDob(new Date(1990,12,01));
        u1.setEmail("email@domain.com");
        u1.setPassword("Always_Scheming");
        u1.setCountry("Canada");
        u1.setJoinDate(new Date());
        u1.setAboutMe("Imaginations ally and inks confidante, I craft worlds within the panels, inviting you to escape reality through the lens of my storytelling pen.");
    }

    @Test
    void getUserByIdTest() throws UserIDDoesNotExistException {
        assertEquals(u1,userService.getUserById(1L));
        assertNotEquals(u1,userService.getUserById(2L));
        assertThrows(UserIDDoesNotExistException.class, () -> {
            userService.getUserById(1000L);
        });
    }

    @Test
    void getUserByUserNameTest() throws UserNameDoesNotExistException {
        assertEquals(u1,userService.getUserByUserName("Always_Scheming"));
        assertNotEquals(u1,userService.getUserByUserName("Always_Throwing"));
        assertThrows(UserNameDoesNotExistException.class, () -> {
            userService.getUserByUserName("@@@@");
        });
    }

    @Test
    void getUserByEmail() throws EmailDoesNotExistException {
        assertEquals(u1,userService.getUserByEmail("email@domain.com"));
        assertNotEquals(u1,userService.getUserByEmail("address@provider.ca"));
        assertThrows(EmailDoesNotExistException.class, () -> {
            userService.getUserByEmail("@@@@");
        });
    }

    @Test
    void startSession() {
        UserSession userSession = UserSession.getInstance(u1);
        assertEquals(userSession, userService.startSession(u1));
    }

    @Test
    void saveUserTest() {
        UserDto u2 = new UserDto();
        u2.setUserName("Always_Throwing");
        u2.setFirstName("Neo");
        u2.setLastName("Anderson");
        u2.setDob(new Date(1929,1,1));
        u2.setEmail("address@provider.ca");
        u2.setPassword("Always_Throwing");
        u2.setCountry("Indonesia");
        u2.setDefaults();
        assertThrows(UsernameOrEmailExistsException.class, () -> userService.saveUser(u2));
    }

    @Test
    void getAllUsersTest() {
        userService.getAllUsers();
        assertFalse(userService.getAllUsers().isEmpty());
    }

    @Test
    void testGetterSetter() {
        UserService service2 = new UserService();
        service2.getCurrentUserLoggedIn();
        service2.getUserDao();
        service2.setCurrentUserLoggedIn(UserSession.getInstance(u1));
        service2.setUserDao(new UserDaoImpl(HibernateUtils.getSessionFactory()));
    }

    @Test
    void test_getAllUsers(){
        List<UserDto> actual = userService.getAllUsers();
        assertTrue(actual.size() >= 50);
    }

    @Test
    void test_getUsersMoreThan_1(){
        List<UserDto> actual = userService.getUsersMoreThan("comicsReading", 1000);
        assertTrue(actual.isEmpty());
    }

    @Test
    void test_getUsersMoreThan_2(){
        List<UserDto> actual = userService.getUsersMoreThan("comicsReading", 15);
        assertEquals(1, actual.size());
    }

    @Test
    void test_getUsersLessThan_1(){
        List<UserDto> actual = userService.getUsersLessThan("comicsFinished", 0);
        assertTrue(actual.isEmpty());
    }

    @Test
    void test_getUsersLessThan_2(){
        List<UserDto> actual = userService.getUsersLessThan("comicsFinished", 2);
        assertEquals(27, actual.size());
    }

    @Test
    void test_add_1_Fail() {
        UserDto u2 = new UserDto();
        u2.setUserName("Always_Throwing");
        u2.setFirstName("Neo");
        u2.setLastName("Anderson");
        u2.setDob(new Date(1929,1,1));
        u2.setEmail("address@provider.ca");
        u2.setPassword("Always_Throwing");
        u2.setCountry("Indonesia");
        u2.setDefaults();

        assertThrows(UsernameOrEmailExistsException.class, () -> userService.saveUser(u2));
    }

    @Test
    void test_add_2_Fail(){
        UserDto u3 = new UserDto();
        u3.setUserName("Phoenix");
        u3.setFirstName("Stan");
        u3.setLastName("Lee");
        u3.setDob(new Date(2000,4,31));
        u3.setEmail("123fake@nowhere.org");
        u3.setPassword("Phoenix");
        u3.setCountry("United Kingdom");
        u3.setDefaults();

        assertThrows(UsernameOrEmailExistsException.class, () -> userService.saveUser(u3));
    }

}
