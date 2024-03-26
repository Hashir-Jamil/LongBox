package org.longbox.unit.presentation.profile;

import org.junit.jupiter.api.Test;
import org.longbox.presentation.profile.AddComicToRepoPanel;

import static org.junit.jupiter.api.Assertions.*;

public class AddComicToRepoPanelTest {

    @Test
    void testFavouriteCheckboxAdded() {
        AddComicToRepoPanel panel = new AddComicToRepoPanel();
        assertNotNull(panel.getFavouriteCheckbox());
    }

    @Test
    void testFavouriteCheckboxText() {
        AddComicToRepoPanel panel = new AddComicToRepoPanel();
        assertEquals("Is Favourite", panel.getFavouriteCheckbox().getText());
    }


}
