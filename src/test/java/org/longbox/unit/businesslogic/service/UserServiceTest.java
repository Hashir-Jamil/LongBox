package org.longbox.unit.businesslogic.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.EmailDoesNotExistException;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.exception.UserNameDoesNotExistException;
import org.longbox.businesslogic.service.UserService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.persistence.dao.UserDaoImpl;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

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
    void startSession(){
        UserSession userSession = UserSession.getInstance(u1);
        assertEquals(userSession, userService.startSession(u1));
    }

}
