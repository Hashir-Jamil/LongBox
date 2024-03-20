package org.longbox.unit.presentation.profile;

import org.junit.jupiter.api.Test;
import org.longbox.presentation.profile.AddComicToRepoPanel;

import static org.junit.jupiter.api.Assertions.*;

public class AddComicToRepoPanelTest {

    @Test
    void testFavoriteCheckboxAdded() {
        AddComicToRepoPanel panel = new AddComicToRepoPanel();
        assertNotNull(panel.getFavoriteCheckbox());
    }

    @Test
    void testFavoriteCheckboxText() {
        AddComicToRepoPanel panel = new AddComicToRepoPanel();
        assertEquals("Is Favorite", panel.getFavoriteCheckbox().getText());
    }


}
