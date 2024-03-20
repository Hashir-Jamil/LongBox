package org.longbox.unit.domainobjects.entity;

import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.entity.User;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testEqualsAndHashCode() {
        // Create two users with the same email, user name, and name
        User user1 = user1 = new User();
        user1.setUserName("user1");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setDob(new Date());
        user1.setEmail("john.doe@example.com");
        user1.setPassword("password");
        user1.setCountry("USA");
        user1.setDefaults();

        User user2 = new User();
        user2.setUserName("user1");
        user2.setFirstName("John");
        user2.setLastName("Doe");
        user2.setDob(new Date());
        user2.setEmail("john.doe@example.com");
        user2.setPassword("password");
        user2.setCountry("USA");
        user2.setJoinDate(new Date());
        user2.setDefaults();

        // Ensure that the equals() method correctly identifies them as equal
        assertTrue(user1.equals(user2));
        assertTrue(user2.equals(user1));

        // Ensure that their hash codes are the same
        assertEquals(user1.hashCode(), user2.hashCode());

        // Create two users with different email
        User user3 = new User();
        user3.setUserName("user2");
        user3.setFirstName("John");
        user3.setLastName("Doe");
        user3.setDob(new Date());
        user3.setEmail("jane.doe@example.com");
        user3.setPassword("password");
        user3.setCountry("USA");
        user3.setDefaults();

        User user4 = new User();
        user4.setUserName("user2");
        user4.setFirstName("John");
        user4.setLastName("Doe");
        user4.setDob(new Date());
        user4.setEmail("john.doe@example.com");
        user4.setPassword("password");
        user4.setCountry("USA");
        user4.setJoinDate(new Date());
        user4.setDefaults();

        // Ensure that the equals() method correctly identifies them as not equal
        assertFalse(user3.equals(user4));
        assertFalse(user4.equals(user3));

        // Ensure that their hash codes are different
        assertNotEquals(user3.hashCode(), user4.hashCode());
    }

    @Test
    public void testToString() {
        // Prepare test data
        Date dob = new Date();
        Date joinDate = new Date();
        String aboutMe = "About me";

        // Create a user
        User user = new User();
        user.setUserName("user1");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setDob(new Date());
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setCountry("USA");
        user.setJoinDate(new Date());
        user.setContinent("North America");
        user.setJoinDate(joinDate);
        user.setComicsReading(5);
        user.setComicsFinished(10);
        user.setAboutMe(aboutMe);

        // Generate expected toString output
        String expectedToString = "User{id=0, userName='user1', firstName='John', lastName='Doe', " +
                "dob=" + dob + ", email='john.doe@example.com', password='password', country='USA', " +
                "continent='North America', joinDate=" + joinDate + ", comicsReading=5, comicsFinished=10, " +
                "aboutMe=About me}";

        // Verify toString output
        assertEquals(expectedToString, user.toString());
    }
}