package org.longbox.unit.businesslogic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.UserSession;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.domainobjects.entity.User;

import java.util.Date;

public class UserSessionTest {

	private UserDto userDto1;
    private UserDto userDto2;

    @BeforeEach
    public void setUp() {
        // Initialize UserDTOs for testing

        userDto1 = new UserDto();
        userDto1.setUserName("user1");
        userDto1.setFirstName("John");
        userDto1.setLastName("Doe");
        userDto1.setDob(new Date());
        userDto1.setEmail("john@example.com");
        userDto1.setPassword("password123");
        userDto1.setCountry("USA");
        userDto1.setDefaults();

        userDto2 = new UserDto();
        userDto2.setUserName("user2");
        userDto2.setFirstName("Jane");
        userDto2.setLastName("Doe");
        userDto2.setDob(new Date());
        userDto2.setEmail("jane@example.com");
        userDto2.setPassword("pass456");
        userDto2.setCountry("Canada");
        userDto2.setDefaults();

    }
    
    @Test
    public void testGetInstance() {
        UserSession userSession1 = UserSession.getInstance(userDto1);
        UserSession userSession2 = UserSession.getInstance(userDto1);

        assertNotNull(userSession1);
        assertNotNull(userSession2);
        assertEquals(userSession1, userSession2);
    }

    @Test
    public void testSetAndGetActiveUser() {
        UserSession.setActiveUser(null);
        assertNull(UserSession.getActiveUser());

        UserSession userSession = UserSession.getInstance(userDto1);
        UserSession.setActiveUser(userSession);

        assertEquals(userSession, UserSession.getActiveUser());
    }

    @Test
    public void testClearUserSession() {
        UserSession userSession = UserSession.getInstance(userDto1);
        userSession.clearUserSession();

        assertNull(userSession.getUser());
    }

    @Test
    public void testMultipleInstances() {
        UserSession userSession1 = UserSession.getInstance(userDto1);
        UserSession userSession2 = UserSession.getInstance(userDto1);

        assertEquals(userSession1, userSession2);
    }


    @Test
    public void testGetInstanceTwiceSameUser() {
        UserSession userSession1 = UserSession.getInstance(userDto1);
        UserSession userSession2 = UserSession.getInstance(userDto1);

        assertNotNull(userSession1);
        assertNotNull(userSession2);
        assertEquals(userSession1, userSession2);
    }

    @Test
    public void testSetAndGetActiveUserMultipleTimes() {
        UserSession.setActiveUser(null);
        assertNull(UserSession.getActiveUser());

        UserSession userSession1 = UserSession.getInstance(userDto1);
        UserSession.setActiveUser(userSession1);

        assertEquals(userSession1, UserSession.getActiveUser());

        UserSession userSession2 = UserSession.getInstance(userDto2);
        UserSession.setActiveUser(userSession2);

        assertEquals(userSession2, UserSession.getActiveUser());

        UserSession.setActiveUser(null);
        assertNull(UserSession.getActiveUser());
    }

    @Test
    public void testClearUserSessionMultipleTimes() {
        UserSession userSession = UserSession.getInstance(userDto1);
        userSession.clearUserSession();
        UserSession.setActiveUser(null);

        assertNull(userSession.getUser());
        assertNull(UserSession.getActiveUser());

        // Clear again, should not cause issues
        userSession.clearUserSession();
        assertNull(userSession.getUser());
        assertNull(UserSession.getActiveUser());
    }
}
