package org.longbox.unit.domainobjects.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.longbox.domainobjects.dto.UserDto;
import org.longbox.persistence.entity.User;

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
        assertEquals(0, u1DTO.getId());
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
}
