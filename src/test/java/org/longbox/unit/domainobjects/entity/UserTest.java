package org.longbox.unit.domainobjects.entity;

import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.entity.User;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testEqualsAndHashCode() {
        // Create two users with the same email, user name, and name
        User user1 = new User("user1", "John", "Doe", new Date(), "john.doe@example.com", "password", "USA");
        User user2 = new User("user1", "John", "Doe", new Date(), "john.doe@example.com", "password", "USA");

        // Ensure that the equals() method correctly identifies them as equal
        assertTrue(user1.equals(user2));
        assertTrue(user2.equals(user1));

        // Ensure that their hash codes are the same
        assertEquals(user1.hashCode(), user2.hashCode());

        // Create two users with different email
        User user3 = new User("user2", "John", "Doe", new Date(), "jane.doe@example.com", "password", "USA");
        User user4 = new User("user2", "John", "Doe", new Date(), "john.doe@example.com", "password", "USA");

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
        User user = new User("user1", "John", "Doe", dob, "john.doe@example.com", "password", "USA");
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