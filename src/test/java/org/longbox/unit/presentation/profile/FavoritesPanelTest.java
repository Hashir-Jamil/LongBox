package org.longbox.unit.presentation.profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.UserSession;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.presentation.profile.FavoritesPanel;
import org.longbox.presentation.profile.HomeFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FavoritesPanelTest {
    private FavoritesPanel favoritesPanel;
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
        favoritesPanel = new FavoritesPanel();
    }
    @Test
    void testInitialization() {
        FavoritesPanel favoritesPanel = new FavoritesPanel();
        assertNotNull(favoritesPanel);
        assertTrue(favoritesPanel.getLayout() instanceof BorderLayout);
    }

    @Test
    void testComicCollectionTitle() {
        assertEquals("User Favorites", favoritesPanel.getComicCollectionTitle().getText());
        assertEquals(new Font("Calibri", Font.PLAIN, 30), favoritesPanel.getComicCollectionTitle().getFont());
        assertEquals(SwingConstants.CENTER, favoritesPanel.getComicCollectionTitle().getHorizontalAlignment());
    }

    @Test
    void testScrollPane() {
        assertEquals(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS, favoritesPanel.getScrollPane().getHorizontalScrollBarPolicy());
        assertEquals(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, favoritesPanel.getScrollPane().getVerticalScrollBarPolicy());
    }

    @Test
    void testTextFieldAndTypeSelection() {
        assertTrue(favoritesPanel.getTextField().getText().isEmpty());
        assertEquals(6, favoritesPanel.getTypeSelection().getItemCount());
        assertEquals("Title", favoritesPanel.getTypeSelection().getItemAt(0));
        assertEquals("Author", favoritesPanel.getTypeSelection().getItemAt(1));
        assertEquals("Artist", favoritesPanel.getTypeSelection().getItemAt(2));
        assertEquals("Genre", favoritesPanel.getTypeSelection().getItemAt(3));
        assertEquals("Publisher", favoritesPanel.getTypeSelection().getItemAt(4));
        assertEquals("Year", favoritesPanel.getTypeSelection().getItemAt(5));
    }

    @Test
    void testUnfavoriteButton() {
        assertFalse(favoritesPanel.getUnfavoriteButton().isEnabled());
    }
}
