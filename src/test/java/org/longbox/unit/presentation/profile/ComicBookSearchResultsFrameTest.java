package org.longbox.unit.presentation.profile;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.*;

import org.junit.jupiter.api.*;
import org.longbox.businesslogic.UserSession;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.presentation.comicbook.ComicBookSearchResultsFrame;
import org.longbox.presentation.tablemodels.ComicBookTableModel;
import org.mockito.Mockito;

class ComicBookSearchResultsFrameTest {

	private static ComicBookSearchResultsFrame frame;
    private static List<ComicBookDto> testResults;
    private static UserSession testUser;

    @BeforeAll
    public static void setUp() {
        testResults = new ArrayList<>();
        testUser = Mockito.mock(UserSession.class);
        frame = new ComicBookSearchResultsFrame(testResults, "Nexus", "Title", testUser);
    }

    @Test
    public void testUserSession() {
        assertEquals(testUser, frame.getUserSession());
    }

    @Test
    public void testComicBookDaoImpl() {
        assertNotNull(frame.getComicBookDaoImpl());
    }

    @Test
    public void testComicBookTable() {
        assertNotNull(frame.getComicBookTable());
    }
    
    @Test
    public void testComicBookTable() {
        assertNotNull(frame.getComicBookTable());
    }

    @Test
    public void testComicBookTableModel() {
        ComicBookTableModel model = (ComicBookTableModel) frame.getComicBookTable().getModel();
        assertNotNull(model);
        assertEquals(7, model.getColumnCount());
    }

}
