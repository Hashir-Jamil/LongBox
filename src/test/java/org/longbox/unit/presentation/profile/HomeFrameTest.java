package org.longbox.unit.presentation.profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.presentation.profile.AddComicToRepoPanel;

import org.longbox.presentation.profile.HomeFrame;
public class HomeFrameTest {

    private HomeFrame homeFrame;
    private AddComicToRepoPanel addComicToRepoPanel;
    @BeforeEach
    public void setUp() {
        homeFrame = new HomeFrame();
        addComicToRepoPanel = new AddComicToRepoPanel();
        addComicToRepoPanel.getComicSeriesTitleTextField().setText("");
        addComicToRepoPanel.getComicBookAuthorTextField().setText("");
        addComicToRepoPanel.getComicBookArtistTextField().setText("");
        addComicToRepoPanel.getGenresTextField().setText("");
        addComicToRepoPanel.getDescriptionTextField().setText("");
        addComicToRepoPanel.getNumberOfIssuesTextField().setText("");
        addComicToRepoPanel.getPublisherTextField().setText("");
        addComicToRepoPanel.getYearPublishedTextField().setText("");
    }
}
