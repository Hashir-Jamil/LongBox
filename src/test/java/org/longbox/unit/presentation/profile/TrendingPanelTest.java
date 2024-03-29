package org.longbox.unit.presentation.profile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.service.ComicBookService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.profile.TrendingPanel;

class TrendingPanelTest {
	
	private static TrendingPanel trendingPanel;
	private static UserDto nexus;

	@BeforeAll
	public static void setUpBeforeClass() {
		nexus = new UserDto();
        nexus.setUserName("Nexus");
        nexus.setFirstName("Horatio");
        nexus.setLastName("Hellpop");
        nexus.setDob(new Date());
        nexus.setEmail("horatio@nexus.com");
        nexus.setPassword("123");
        nexus.setCountry("USA");
        nexus.setDefaults();
		
        UserSession userSession1 = UserSession.getInstance(nexus);        
    	ComicBookService comicBookService = new ComicBookService(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));

        trendingPanel = new TrendingPanel(userSession1);
        trendingPanel.setComicBookService(comicBookService);
	}

	@Test
	void testRegionComboBox() {
		assertEquals(7, trendingPanel.getRegionBox().getItemCount());
        assertEquals("North America", trendingPanel.getRegionBox().getItemAt(0));
        assertEquals("South America", trendingPanel.getRegionBox().getItemAt(1));
        assertEquals("Europe", trendingPanel.getRegionBox().getItemAt(2));
        assertEquals("Asia", trendingPanel.getRegionBox().getItemAt(3));
        assertEquals("Africa", trendingPanel.getRegionBox().getItemAt(4));
        assertEquals("Oceania", trendingPanel.getRegionBox().getItemAt(5));
        assertEquals("Antarctica", trendingPanel.getRegionBox().getItemAt(6));
	}
	
	@Test
	void testRegionComboBoxFakeRegion() {
		for (int i = 0; i < trendingPanel.getRegionBox().getItemCount(); i++) {
			assertNotEquals("Aegis", trendingPanel.getRegionBox().getItemAt(i));
		}
	}
	
	@Test
	void testTitles() {
		assertEquals("Trending", trendingPanel.getTrendingTitle().getText());
		assertEquals("All Time Favourites:", trendingPanel.getAllTimeFavouritesLabel().getText());
		assertEquals("Regional Favourites: ", trendingPanel.getRegionalFavourites().getText());
	}

}
