package org.longbox.unit.domainobjects.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.longbox.domainobjects.dto.UserDto;
import org.longbox.domainobjects.entity.User;

import java.util.Date;

public class UserDtoTest {

    UserDto u1DTO,u2DTO,u3DTO, u4DTO, u1DTOClone;
    User u1, u2;
    @BeforeEach
    public void loadUsers() {

        u1DTO = new UserDto(
            "Always_Scheming",
            "John",
            "Smith",
            new Date(1990, 12, 1),
            "email@domain.com",
            "Always_Scheming",
            "Canada"
        );

        u2DTO = new UserDto(
            2,
            "Always_Throwing",
            "Neo",
            "Anderson",
            new Date(1929,1,1),
            "address@provider.ca",
            "Always_Throwing",
            "Indonesia",
            0,
            0
        );

        u3DTO = new UserDto(
            3,
            "Phoenix",
            "Stan",
            "Lee",
            new Date(2000,4,31),
            "123fake@nowhere.org",
            "Phoenix",
            "United Kingdom",
            0,
            0
        );

        u1DTOClone = new UserDto(
                "Always_Scheming",
                "John",
                "Smith",
                new Date(1990, 12, 1),
                "email@domain.com",
                "Always_Scheming",
                "Canada"
        );

        u1 = new User(
                "Phoenix",
                "Stan",
                "Lee",
                new Date(2000,4,31),
                "123fake@nowhere.org",
                "Phoenix",
                "United Kingdom"
        );

        u4DTO = new UserDto(u1);

        u2 = new User(u3DTO);
    }

    @Test
    public void testUserDTONoIDConstructor() {
        assertNull(u1DTO.getId());
        assertEquals("Always_Scheming", u1DTO.getUserName());
        assertEquals("John", u1DTO.getFirstName());
        assertEquals("Smith", u1DTO.getLastName());
        assertEquals("Thu Jan 01 00:00:00 EST 3891", u1DTO.getDob().toString());
        assertEquals("email@domain.com", u1DTO.getEmail());
        assertEquals("Always_Scheming", u1DTO.getPassword());
        assertEquals(0, u1DTO.getComicsReading());
        assertEquals(0, u1DTO.getComicsFinished());
    }

    @Test
    public void testUserDTOToUserTransfer() {
        assertEquals(u3DTO.getUserName(), u2.getUserName());
        assertEquals(u3DTO.getFirstName(), u2.getFirstName());
        assertEquals(u3DTO.getLastName(), u2.getLastName());
        assertEquals(u3DTO.getDob(), u2.getDob());
        assertEquals(u3DTO.getEmail(), u2.getEmail());
        assertEquals(u3DTO.getPassword(), u2.getPassword());
        assertEquals(u3DTO.getComicsReading(), u2.getComicsReading());
        assertEquals(u3DTO.getComicsFinished(), u2.getComicsFinished());
    }

    @Test
    public void userDTOEqualToItselfTest() {
        assertEquals(u1DTO,u1DTO);
        assertNotEquals(u1DTO,u2DTO);
    }
    @Test
    public void userDTOEqualsMethodTest() {
        assertTrue(u1DTO.equals(u1DTO));
        assertTrue(u1DTO.equals(u1DTOClone));
        assertFalse(u2DTO.equals(u3DTO));
    }

    @Test
    public void testUserToUserDTO() {
        assertEquals(u1.getUserName(), u4DTO.getUserName());
        assertEquals(u1.getFirstName(), u4DTO.getFirstName());
        assertEquals(u1.getLastName(), u4DTO.getLastName());
        assertEquals(u1.getDob(), u4DTO.getDob());
        assertEquals(u1.getEmail(), u4DTO.getEmail());
        assertEquals(u1.getPassword(), u4DTO.getPassword());
        assertEquals(u1.getComicsReading(), u4DTO.getComicsReading());
        assertEquals(u1.getComicsFinished(), u4DTO.getComicsFinished());
    }

    @Test
    public void testEmptyConstructor() {
        UserDto userDto = new UserDto();
        assertNotNull(userDto);
    }

    @Test
    public void testConstructorWithBasicFields() {
        Long id = 1L;
        String userName = "user1";
        String firstName = "John";
        String lastName = "Doe";
        Date dob = new Date();
        String email = "john.doe@example.com";
        String password = "password";
        String country = "USA";
        String aboutMe = "About me";

        UserDto userDto = new UserDto(id, userName, firstName, lastName, dob, email, password, country, aboutMe);

        assertEquals(id, userDto.getId());
        assertEquals(userName, userDto.getUserName());
        assertEquals(firstName, userDto.getFirstName());
        assertEquals(lastName, userDto.getLastName());
        assertEquals(dob, userDto.getDob());
        assertEquals(email, userDto.getEmail());
        assertEquals(password, userDto.getPassword());
        assertEquals(country, userDto.getCountry());
        assertEquals(aboutMe, userDto.getAboutMe());
    }

    @Test
    public void testEqualsAndHashCode() {
        Long id = 1L;
        String userName = "user1";
        String firstName = "John";
        String lastName = "Doe";
        Date dob = new Date();
        String email = "john.doe@example.com";
        String password = "password";
        String country = "USA";
        String aboutMe = "About me";

        UserDto userDto1 = new UserDto(id, userName, firstName, lastName, dob, email, password, country, aboutMe);
        UserDto userDto2 = new UserDto(id, userName, firstName, lastName, dob, email, password, country, aboutMe);

        // Test equals method
        assertTrue(userDto1.equals(userDto2));
        assertTrue(userDto2.equals(userDto1));

        // Test hashCode method
        assertEquals(userDto1.hashCode(), userDto2.hashCode());
    }

    @Test
    public void testConstructorWithAllParameters() {
        // Prepare test data
        long id = 1L;
        String userName = "user1";
        String firstName = "John";
        String lastName = "Doe";
        Date dob = new Date();
        String email = "john.doe@example.com";
        String password = "password";
        String country = "USA";
        int comicsReading = 5;
        int comicsFinished = 10;
        String aboutMe = "About me";

        // Create UserDto object using the constructor
        UserDto userDto = new UserDto(id, userName, firstName, lastName, dob, email, password, country, comicsReading, comicsFinished, aboutMe);

        // Assert that the fields of the UserDto object are initialized correctly
        assertEquals(id, userDto.getId());
        assertEquals(userName, userDto.getUserName());
        assertEquals(firstName, userDto.getFirstName());
        assertEquals(lastName, userDto.getLastName());
        assertEquals(dob, userDto.getDob());
        assertEquals(email, userDto.getEmail());
        assertEquals(password, userDto.getPassword());
        assertEquals(country, userDto.getCountry());
        assertEquals(comicsReading, userDto.getComicsReading());
        assertEquals(comicsFinished, userDto.getComicsFinished());
        assertEquals(aboutMe, userDto.getAboutMe());

        // Assert that joinDate field is initialized with the current date
        assertNotNull(userDto.getJoinDate());
    }

    @Test
    public void continentTest() {
        u1DTO.setContinent("Africa");
        assertEquals("Africa", u1DTO.getContinent());
    }

    @Test
    public void testConstructorWithBasicParameters() {
        // Prepare test data
        String userName = "user1";
        String firstName = "John";
        String lastName = "Doe";
        Date dob = new Date();
        String email = "john.doe@example.com";
        String password = "password";
        String country = "USA";
        String aboutMe = "About me";

        // Create UserDto object using the constructor
        UserDto userDto = new UserDto(userName, firstName, lastName, dob, email, password, country, aboutMe);

        // Assert that the fields of the UserDto object are initialized correctly
        assertEquals(userName, userDto.getUserName());
        assertEquals(firstName, userDto.getFirstName());
        assertEquals(lastName, userDto.getLastName());
        assertEquals(dob, userDto.getDob());
        assertEquals(email, userDto.getEmail());
        assertEquals(password, userDto.getPassword());
        assertEquals(country, userDto.getCountry());
        assertEquals(0, userDto.getComicsReading());
        assertEquals(0, userDto.getComicsFinished());
        assertEquals(aboutMe, userDto.getAboutMe());

        // Assert that joinDate field is initialized with the current date
        assertNotNull(userDto.getJoinDate());
    }
    @Test
    public void testToString() {
        // Prepare test data
        Long id = 1L;
        String userName = "user1";
        String firstName = "John";
        String lastName = "Doe";
        Date dob = new Date();
        String email = "john.doe@example.com";
        String password = "password";
        String country = "USA";
        String continent = "North America";
        Date joinDate = new Date();
        int comicsReading = 5;
        int comicsFinished = 10;
        String aboutMe = "About me";

        // Create UserDto object
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setUserName(userName);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setDob(dob);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setCountry(country);
        userDto.setContinent(continent);
        userDto.setJoinDate(joinDate);
        userDto.setComicsReading(comicsReading);
        userDto.setComicsFinished(comicsFinished);
        userDto.setAboutMe(aboutMe);

        // Generate toString representation
        String expectedToString = "UserDto(id=1, userName=user1, firstName=John, lastName=Doe, " +
                "dob=" + dob + ", email=john.doe@example.com, password=password, country=USA, " +
                "continent=North America, joinDate=" + joinDate + ", comicsReading=5, comicsFinished=10, " +
                "aboutMe=About me)";

        // Verify toString representation
        assertEquals(expectedToString, userDto.toString());
    }
}
