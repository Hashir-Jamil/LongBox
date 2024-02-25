package org.longbox.comiccollection;

import org.longbox.persistence.stubdatabase.ComicBookStubDB;
import org.longbox.presentation.profile.ComicCollectionPanel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ComicCollectionTest {
	
	private ComicCollectionPanel comicCollectionPanel;

    public void setUp() {
        comicCollectionPanel = new ComicCollectionPanel();
    }

    @Test
    public void testInitialization() {
    	setUp();
        assertNotNull(comicCollectionPanel);
    }

    @Test
    public void testComicCollectionTitle() {
    	setUp();
        assertEquals("Comic Repo", comicCollectionPanel.getComicCollectionTitle().getText());
    }

    @Test
    public void testSearchTextField() {
    	setUp();
        assertNotNull(comicCollectionPanel.getTextField().getText());
    }

    @Test
    public void testComicBookTable() {
    	setUp();
        assertNotNull(comicCollectionPanel.getComicBookTable());
        assertNotNull(comicCollectionPanel.getComicBookTableModel());
    }
    
    @Test
    public void testComicBookAdvancedSearchOptions() {
    	setUp();
    	assertTrue(comicCollectionPanel.getTypeSelection().getItemAt(0) == "Title");
    	assertTrue(comicCollectionPanel.getTypeSelection().getItemAt(1) == "Author");
    	assertTrue(comicCollectionPanel.getTypeSelection().getItemAt(2) == "Artist");
    	assertTrue(comicCollectionPanel.getTypeSelection().getItemAt(3) == "Genre");
    	assertTrue(comicCollectionPanel.getTypeSelection().getItemAt(4) == "Publisher"); 
    	assertTrue(comicCollectionPanel.getTypeSelection().getItemAt(5) == "Year");
    }
 
	
}
