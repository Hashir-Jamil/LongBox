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
    UserDto userDto = new UserDto(
            1L,
            "john_doe",
            "John",
            "Doe",
            new Date(),
            "john@example.com",
            "password",
            "USA",
            0,
            0,
            "About John"
    );

    @BeforeEach
    public void setUp() {
        UserSession userSession = UserSession.getInstance(userDto);
        homeFrame = new HomeFrame(userSession);
    }

    @Test
    void testFavoriteButton() {
        assertNotNull(homeFrame.getFavoritesButton());
    }

}
