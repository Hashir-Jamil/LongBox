package org.longbox.unit.persistence.stubdatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.exception.EmailDoesNotExistException;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.exception.UserNameDoesNotExistException;
import org.longbox.businesslogic.exception.UsernameOrEmailExistsException;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.domainobjects.entity.User;
import org.longbox.persistence.stubdatabase.UserStubDb;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserStubDbTest {

    UserStubDb userStubDao;
    List<UserDto> users;
    UserDto u1;

    @BeforeEach
    void setup() {
        userStubDao = new UserStubDb();
        users = userStubDao.deserializeStubData(userStubDao.getABSOLUTE_FILE_PATH());
        u1 = new UserDto(
            1L,
            "Always_Scheming",
            "John",
            "Smith",
            new Date(1990,12,01),
            "email@domain.com",
            "Always_Scheming",
            "Canada",
            "Imaginations ally and inks confidante, I craft worlds within the panels, inviting you to escape reality through the lens of my storytelling pen."
        );
    }

    @Test
    void serializeTest() {
        userStubDao.setUserStubData(userStubDao.deserializeStubData(userStubDao.getABSOLUTE_FILE_PATH()));
        userStubDao.serializeStubData();
    }

    @Test
    void deserializeTest() {
        assertNotNull(users);
        assertEquals(7, users.size());
        assertEquals(u1, users.get(0));
        assertNotEquals(u1, users.get(1));
        Set<Long> ids = new HashSet<>();;
        Set<String> userNames = new HashSet<>();
        Set<String> emails = new HashSet<>();
        for (UserDto user : users) {
            ids.add(user.getId());
            userNames.add(user.getUserName());
            emails.add(user.getEmail());
        }
        assertEquals(users.size(), ids.size());
        assertEquals(users.size(), userNames.size());
        assertEquals(users.size(), emails.size());
    }

    @Test
    void getUserByIdTest() throws UserIDDoesNotExistException {
        assertEquals(u1.getId(),userStubDao.getUserById(1L).getId());
        assertEquals(u1.getUserName(),userStubDao.getUserById(1L).getUserName());
        assertEquals(u1.getFirstName(),userStubDao.getUserById(1L).getFirstName());
        assertEquals(u1.getLastName(),userStubDao.getUserById(1L).getLastName());
        assertEquals(u1.getEmail(),userStubDao.getUserById(1L).getEmail());
        assertThrows(UserIDDoesNotExistException.class, () -> {
           userStubDao.getUserById(10L);
        });
    }

    @Test
    void getUserByUsernameTest() throws UserNameDoesNotExistException {
        assertEquals(u1.getId(),userStubDao.getUserByUserName("Always_Scheming").getId());
        assertEquals(u1.getUserName(),userStubDao.getUserByUserName("Always_Scheming").getUserName());
        assertEquals(u1.getFirstName(),userStubDao.getUserByUserName("Always_Scheming").getFirstName());
        assertEquals(u1.getLastName(),userStubDao.getUserByUserName("Always_Scheming").getLastName());
        assertEquals(u1.getEmail(),userStubDao.getUserByUserName("Always_Scheming").getEmail());
        assertThrows(UserNameDoesNotExistException.class, () -> {
            userStubDao.getUserByUserName("Always_Scheming2");
        });
    }

    @Test
    void getUserByEmailTest() throws EmailDoesNotExistException {
        assertEquals(u1.getId(),userStubDao.getUserByEmail("email@domain.com").getId());
        assertEquals(u1.getUserName(),userStubDao.getUserByEmail("email@domain.com").getUserName());
        assertEquals(u1.getFirstName(),userStubDao.getUserByEmail("email@domain.com").getFirstName());
        assertEquals(u1.getLastName(),userStubDao.getUserByEmail("email@domain.com").getLastName());
        assertEquals(u1.getEmail(),userStubDao.getUserByEmail("email@domain.com").getEmail());
        assertThrows(EmailDoesNotExistException.class, () -> {
            userStubDao.getUserByEmail("wrongemail@domain.com");
        });
    }

    @Test
    void saveUserTest() throws UsernameOrEmailExistsException {
        User user = new User();
        user.setUserName("Never_Throwing");
        user.setFirstName("Harry");
        user.setLastName("Johnson");
        user.setEmail("123@temp.org");
        user.setDob(new Date(1986,12,1));
        user.setPassword("Never_Throwing");
        user.setCountry("Thailand");
        user.setJoinDate(new Date());
        user.setComicsReading(0);
        user.setComicsFinished(0);
        user.setAboutMe("I am not real actually.");
        assertThrows(UsernameOrEmailExistsException.class, () -> {
            userStubDao.saveUser(user);
        });
    }
}
