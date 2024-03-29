package org.longbox.unit.presentation.profile;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.longbox.businesslogic.UserSession;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.presentation.comicbook.ComicBookSearchResultsFrame;
import org.longbox.presentation.tablemodels.ComicBookTableModel;

class ComicBookSearchResultsFrameTest {

	private static ComicBookSearchResultsFrame frame;
    private static List<ComicBookDto> testResults;
    private static UserSession testUser;

    @BeforeAll
    public static void setUp() {
        testResults = new ArrayList<>();
        frame = new ComicBookSearchResultsFrame(testResults, "Nexus", "Title", testUser);
    }

    @Test
    public void testUserSession() {
        assertEquals(testUser, frame.getUserSession());
    }

    @Test
    public void testComicBookDaoImpl() {
        assertNotNull(frame.getAllComicBooks());
    }

    @Test
    public void testComicBookTable() {
        assertNotNull(frame.getComicBookTable());
    }
    
    @Test
    public void testComicBookTitle_1() {
        assertNotNull(frame.getTitle());
        assertTrue(frame.getTitle().equals("Results for Nexus in Title"));
    }
    
    @Test
    public void testComicBookTitle_2() {
    	frame = new ComicBookSearchResultsFrame(testResults, "Manga", "Genre", testUser);
        assertNotNull(frame.getTitle());
        assertTrue(frame.getTitle().equals("Results for Manga in Genre"));
    }
    
    @Test
    public void testComicBookTitle_3() {
    	frame = new ComicBookSearchResultsFrame(testResults, "Manabe", "Author", testUser);
        assertNotNull(frame.getTitle());
        assertTrue(frame.getTitle().equals("Results for Manabe in Author"));
    }
    
    @Test
    public void testComicBookTitle_4() {
    	frame = new ComicBookSearchResultsFrame(testResults, "Dark Horse", "Publisher", testUser);
        assertNotNull(frame.getTitle());
        assertTrue(frame.getTitle().equals("Results for Dark Horse in Publisher"));
    }
    
    @Test
    public void testComicBookTitle_5() {
    	frame = new ComicBookSearchResultsFrame(testResults, "1999", "Year", testUser);
        assertNotNull(frame.getTitle());
        assertTrue(frame.getTitle().equals("Results for 1999 in Year"));
    }

    @Test
    public void testComicBookTableModel() {
        ComicBookTableModel model = (ComicBookTableModel) frame.getComicBookTable().getModel();
        assertNotNull(model);
        assertEquals(7, model.getColumnCount());
    }

}
