package org.longbox.unit.domainobjects.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.longbox.domainobjects.dto.UserDto;
import org.longbox.domainobjects.entity.User;
import org.longbox.domainobjects.mapper.UserMapper;

import java.util.Date;

public class UserDtoTest {

    UserDto u1DTO,u2DTO,u3DTO, u4DTO, u1DTOClone;
    User u1, u2;
    @BeforeEach
    public void loadUsers() {

        u1DTO = new UserDto();
        u1DTO.setUserName("Always_Scheming");
        u1DTO.setFirstName("John");
        u1DTO.setLastName("Smith");
        u1DTO.setDob(new Date());
        u1DTO.setEmail("email@domain.com");
        u1DTO.setPassword("Always_Scheming");
        u1DTO.setCountry("USA");
        u1DTO.setDefaults();

        u2DTO = new UserDto();
        u2DTO.setUserName("Always_Throwing");
        u2DTO.setFirstName("Neo");
        u2DTO.setLastName("Anderson");
        u2DTO.setDob(new Date(1929,1,1));
        u2DTO.setEmail("address@provider.ca");
        u2DTO.setPassword("Always_Throwing");
        u2DTO.setCountry("Indonesia");
        u2DTO.setDefaults();

        u3DTO = new UserDto();
        u3DTO.setUserName("Phoenix");
        u3DTO.setFirstName("Stan");
        u3DTO.setLastName("Stan");
        u3DTO.setDob(new Date(2000,4,31));
        u3DTO.setEmail("123fake@nowhere.org");
        u3DTO.setPassword("Phoenix");
        u3DTO.setCountry("United Kingdom");
        u3DTO.setDefaults();

        u1DTOClone = new UserDto();
        u1DTOClone.setUserName("Always_Scheming");
        u1DTOClone.setFirstName("John");
        u1DTOClone.setLastName("Smith");
        u1DTOClone.setDob(new Date());
        u1DTOClone.setEmail("email@domain.com");
        u1DTOClone.setPassword("Always_Scheming");
        u1DTOClone.setCountry("USA");
        u1DTOClone.setDefaults();

        u1 = new User();
        u1.setUserName("Phoenix");
        u1.setFirstName("Stan");
        u1.setLastName("Stan");
        u1.setDob(new Date(2000,4,31));
        u1.setEmail("123fake@nowhere.org");
        u1.setPassword("Phoenix");
        u1.setCountry("United Kingdom");
        u1.setDefaults();

        u4DTO = UserMapper.toDto(u1);

        u2 = UserMapper.toEntity(u3DTO);
    }

    @Test
    public void testUserDTONoIDConstructor() {
        assertNull(u1DTO.getId());
        assertEquals("Always_Scheming", u1DTO.getUserName());
        assertEquals("John", u1DTO.getFirstName());
        assertEquals("Smith", u1DTO.getLastName());
        assertEquals(new Date().toString(), u1DTO.getDob().toString());
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

        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setUserName(userName);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setDob(dob);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setCountry(country);
        userDto.setJoinDate(new Date());
        userDto.setAboutMe(aboutMe);

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

        UserDto userDto1 = new UserDto();
        userDto1.setId(id);
        userDto1.setUserName(userName);
        userDto1.setFirstName(firstName);
        userDto1.setLastName(lastName);
        userDto1.setDob(dob);
        userDto1.setEmail(email);
        userDto1.setPassword(password);
        userDto1.setCountry(country);
        userDto1.setJoinDate(new Date());
        userDto1.setAboutMe(aboutMe);

        UserDto userDto2 = new UserDto();
        userDto2.setId(id);
        userDto2.setUserName(userName);
        userDto2.setFirstName(firstName);
        userDto2.setLastName(lastName);
        userDto2.setDob(dob);
        userDto2.setEmail(email);
        userDto2.setPassword(password);
        userDto2.setCountry(country);
        userDto2.setJoinDate(new Date());
        userDto2.setAboutMe(aboutMe);

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
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setUserName(userName);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setDob(dob);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setCountry(country);
        userDto.setComicsReading(comicsReading);
        userDto.setComicsFinished(comicsFinished);
        userDto.setAboutMe(aboutMe);
        userDto.setJoinDate(new Date());

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

        UserDto userDto = new UserDto();
        userDto.setUserName(userName);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setDob(dob);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setCountry(country);
        userDto.setJoinDate(new Date());
        userDto.setAboutMe(aboutMe);


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
                "aboutMe=About me" + ", preferredGenre=null)";

        // Verify toString representation
        assertEquals(expectedToString, userDto.toString());
    }
}
