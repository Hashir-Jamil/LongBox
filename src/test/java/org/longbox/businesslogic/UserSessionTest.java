package org.longbox.businesslogic;

import org.junit.jupiter.api.BeforeAll;
import org.longbox.businesslogic.UserSession;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.UserDTO;
import java.util.Date;

public class UserSessionTest {

	private UserDTO userDTO1;
    private UserDTO userDTO2;

    @BeforeEach
    public void setUp() {
        // Initialize UserDTOs for testing
        userDTO1 = new UserDTO("user1", "John", "Doe", new Date(), "john@example.com", "password123", "USA");
        userDTO2 = new UserDTO("user2", "Jane", "Doe", new Date(), "jane@example.com", "pass456", "Canada");
    }
    
    @Test
    public void testGetInstance() {
        UserSession userSession1 = UserSession.getInstance(userDTO1);
        UserSession userSession2 = UserSession.getInstance(userDTO1);

        assertNotNull(userSession1);
        assertNotNull(userSession2);
        assertEquals(userSession1, userSession2);
    }

    @Test
    public void testSetAndGetActiveUser() {
        UserSession.setActiveUser(null);
        assertNull(UserSession.getActiveUser());

        UserSession userSession = UserSession.getInstance(userDTO1);
        UserSession.setActiveUser(userSession);

        assertEquals(userSession, UserSession.getActiveUser());
    }

    @Test
    public void testClearUserSession() {
        UserSession userSession = UserSession.getInstance(userDTO1);
        userSession.clearUserSession();

        assertNull(userSession.getUser());
    }

    @Test
    public void testMultipleInstances() {
        UserSession userSession1 = UserSession.getInstance(userDTO1);
        UserSession userSession2 = UserSession.getInstance(userDTO1);

        assertEquals(userSession1, userSession2);
    }


    @Test
    public void testGetInstanceTwiceSameUser() {
        UserSession userSession1 = UserSession.getInstance(userDTO1);
        UserSession userSession2 = UserSession.getInstance(userDTO1);

        assertNotNull(userSession1);
        assertNotNull(userSession2);
        assertEquals(userSession1, userSession2);
    }

    @Test
    public void testSetAndGetActiveUserMultipleTimes() {
        UserSession.setActiveUser(null);
        assertNull(UserSession.getActiveUser());

        UserSession userSession1 = UserSession.getInstance(userDTO1);
        UserSession.setActiveUser(userSession1);

        assertEquals(userSession1, UserSession.getActiveUser());

        UserSession userSession2 = UserSession.getInstance(userDTO2);
        UserSession.setActiveUser(userSession2);

        assertEquals(userSession2, UserSession.getActiveUser());

        UserSession.setActiveUser(null);
        assertNull(UserSession.getActiveUser());
    }

    @Test
    public void testClearUserSessionMultipleTimes() {
        UserSession userSession = UserSession.getInstance(userDTO1);
        userSession.clearUserSession();
        userSession.setActiveUser(null);

        assertNull(userSession.getUser());
        assertNull(UserSession.getActiveUser());

        // Clear again, should not cause issues
        userSession.clearUserSession();
        assertNull(userSession.getUser());
        assertNull(UserSession.getActiveUser());
    }

  
}
