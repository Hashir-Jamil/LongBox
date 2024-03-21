package org.longbox.unit.businesslogic.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.utils.UserSearchUtils;
import org.longbox.domainobjects.dto.UserDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserSearchUtilsTest {
    UserDto u1DTO,u2DTO,u3DTO;
    List<UserDto> userDtoList;
    @BeforeEach
    void setup(){
        userDtoList = new ArrayList<>();

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

        userDtoList.add(u1DTO);
        userDtoList.add(u2DTO);
        userDtoList.add(u3DTO);
    }

    @Test
    void test_equals_1(){
        UserDto expected = new UserDto();
        expected.setUserName("Always_Scheming");
        expected.setFirstName("John");
        expected.setLastName("Smith");
        expected.setDob(new Date());
        expected.setEmail("email@domain.com");
        expected.setPassword("Always_Scheming");
        expected.setCountry("USA");
        expected.setDefaults();

        assertEquals(expected, UserSearchUtils.getSearchedUser(userDtoList, "Always_Scheming"));
    }

    @Test
    void test_equals_2(){
        UserDto expected = new UserDto();
        expected.setUserName("Phoenix");
        expected.setFirstName("Stan");
        expected.setLastName("Stan");
        expected.setDob(new Date(2000,4,31));
        expected.setEmail("123fake@nowhere.org");
        expected.setPassword("Phoenix");
        expected.setCountry("United Kingdom");
        expected.setDefaults();

        assertEquals(expected, UserSearchUtils.getSearchedUser(userDtoList, "Phoenix"));
    }

    @Test
    void test_notEquals_1(){
        UserDto expected = new UserDto();
        expected.setUserName("Phoenix");
        expected.setFirstName("Stan");
        expected.setLastName("Stan");
        expected.setDob(new Date(2000,4,31));
        expected.setEmail("123fake@nowhere.org");
        expected.setPassword("Phoenix");
        expected.setCountry("United Kingdom");
        expected.setDefaults();

        assertNotEquals(expected, UserSearchUtils.getSearchedUser(userDtoList, "user"));
    }

    @Test
    void test_notEquals_2(){
        UserDto expected = new UserDto();
        expected.setUserName("Phoenix");
        expected.setFirstName("Stan");
        expected.setLastName("Stan");
        expected.setDob(new Date(2000,4,31));
        expected.setEmail("123fake@nowhere.org");
        expected.setPassword("Phoenix");
        expected.setCountry("United Kingdom");
        expected.setDefaults();

        assertNotEquals(expected, UserSearchUtils.getSearchedUser(userDtoList, ""));
    }
}
