package org.longbox.unit.presentation.profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.presentation.profile.HomeFrame;
import org.longbox.businesslogic.UserSession;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class HomeFrameTest {

    private HomeFrame homeFrame;
    UserDto userDto;

    @BeforeEach
    public void setUp() {
        UserSession userSession = UserSession.getInstance(userDto);
        homeFrame = new HomeFrame(userSession);

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUserName("john_doe");
        userDto.setFirstName("john_doe");
        userDto.setLastName("Doe");
        userDto.setDob(new Date());
        userDto.setEmail("john@example.com");
        userDto.setPassword("password");
        userDto.setCountry("Canada");
        userDto.setAboutMe("About John");
        userDto.setJoinDate(new Date());
        userDto.setDefaults();
    }

    @Test
    void testFavoriteButton() {
        assertNotNull(homeFrame.getFavoritesButton());
    }

}
