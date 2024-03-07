package org.longbox.unit.comiccollection.comiccollection;

import org.longbox.presentation.profile.ComicRepositoryPanel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ComicRepositoryTest {
	
	private ComicRepositoryPanel comicRepositoryPanel;

    public void setUp() {
        comicRepositoryPanel = new ComicRepositoryPanel();
    }

    @Test
    public void testInitialization() {
    	setUp();
        assertNotNull(comicRepositoryPanel);
    }

    @Test
    public void testComicCollectionTitle() {
    	setUp();
        assertEquals("Comic Repository", comicRepositoryPanel.getComicRepositoryTitle().getText());
    }

    @Test
    public void testSearchTextField() {
    	setUp();
        assertTrue(comicRepositoryPanel.getTextField().getText().isBlank());
    }

    @Test
    public void testComicBookTable() {
    	setUp();
        assertNotNull(comicRepositoryPanel.getComicBookTable());
        assertNotNull(comicRepositoryPanel.getComicBookTableModel());
    }
    
    @Test
    public void testComicBookAdvancedSearchOptions() {
    	setUp();
    	assertTrue(comicRepositoryPanel.getTypeSelection().getItemCount() == 6);
    	assertTrue(comicRepositoryPanel.getTypeSelection().getItemAt(0) == "Title");
    	assertTrue(comicRepositoryPanel.getTypeSelection().getItemAt(1) == "Author");
    	assertTrue(comicRepositoryPanel.getTypeSelection().getItemAt(2) == "Artist");
    	assertTrue(comicRepositoryPanel.getTypeSelection().getItemAt(3) == "Genre");
    	assertTrue(comicRepositoryPanel.getTypeSelection().getItemAt(4) == "Publisher"); 
    	assertTrue(comicRepositoryPanel.getTypeSelection().getItemAt(5) == "Year");
    	assertTrue(comicRepositoryPanel.getTypeSelection().getItemAt(6) == null);
    }
    
 
	
}
