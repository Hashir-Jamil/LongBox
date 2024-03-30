package org.longbox.unit.domainobjects.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.StarRatingDto;
import org.longbox.domainobjects.dto.UserDto;

public class StarRatingDtoTest {
	StarRatingDto starRatingDto1, starRatingDto2;
	UserDto userDto;
	ComicBookDto comicBook1;
	
	@BeforeEach
	void init() {
		
		starRatingDto1 = new StarRatingDto();
		starRatingDto1.setComicBookId(1L);
		starRatingDto1.setUserId(3L);
		starRatingDto1.setRating(5);
		
        userDto = new UserDto();
        userDto.setUserName("user1");
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setDob(new Date());
        userDto.setEmail("email@domain.com");
        userDto.setPassword("Always_Scheming");
        userDto.setCountry("USA");
        userDto.setDefaults();

        comicBook1 = new ComicBookDto();
        comicBook1.setSeriesTitle("Zot!");
        comicBook1.setAuthor("Scott McCloud");
        comicBook1.setArtist("Scott McCloud");
        comicBook1.setGenres(new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"});
        comicBook1.setDescription("Description");
        comicBook1.setNumberOfIssues(36);
        comicBook1.setPublisher("Eclipse");
        comicBook1.setYearPublished(1984);
	}

    @Test
    void test_constructor_1(){
        assertEquals(5, starRatingDto1.getRating());
        assertEquals(3, starRatingDto1.getUserId());
        assertEquals(1, starRatingDto1.getComicBookId());
    }

    @Test
    public void testConstructorWithUserAndComicBook() {
        // Create a UserDto object
        UserDto userDto = new UserDto();
        userDto.setId(1L);

        // Create a ComicBookDto object
        ComicBookDto comicBookDto = new ComicBookDto();
        comicBookDto.setId(1L);

        // Test the constructor

        StarRatingDto starRatingDto = new StarRatingDto();
        starRatingDto.setUserId(userDto.getId());
        starRatingDto.setComicBookId(comicBookDto.getId());

        // Verify that the StarRatingDto object is initialized correctly
        assertEquals(1L, starRatingDto.getUserId());
        assertEquals(1L, starRatingDto.getComicBookId());
    }

    @Test
    public void testConstructorWithUserIdAndComicBookId() {
        // Test the constructor
    	StarRatingDto starRatingDto = new StarRatingDto();
    	starRatingDto.setUserId(1L);
    	starRatingDto.setComicBookId(1L);

        // Verify that the StarRatingDto object is initialized correctly
        assertEquals(1L, starRatingDto.getUserId());
        assertEquals(1L, starRatingDto.getComicBookId());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create two StarRatingDto objects with identical properties
    	StarRatingDto starRatingDto1 = new StarRatingDto();
    	starRatingDto1.setUserId(1L);
    	starRatingDto1.setComicBookId(1L);

        StarRatingDto starRatingDto2 = new StarRatingDto();
        starRatingDto2.setUserId(1L);
        starRatingDto2.setComicBookId(1L);

        // Create another StarRatingDto object with different properties
        StarRatingDto starRatingDto3 = new StarRatingDto();
        starRatingDto3.setUserId(2L);
        starRatingDto3.setComicBookId(1L);

        // Test equals() method
        assertTrue(starRatingDto1.equals(starRatingDto2)); // starRatingDto1 should be equal to starRatingDto2
        assertFalse(starRatingDto1.equals(starRatingDto3)); // starRatingDto1 should not be equal to starRatingDto3

        // Test hashCode() method
        assertEquals(starRatingDto1.hashCode(), starRatingDto2.hashCode()); // cstarRatingDto1 and starRatingDto2 should have the same hash code
    }

    @Test
    public void testToString() {
        // Create a StarRatingDto object
    	StarRatingDto starRatingDto = new StarRatingDto();
    	starRatingDto.setUserId(1L);
    	starRatingDto.setComicBookId(1L);
    	starRatingDto.setRating(5);


        // Test toString() method
        String expectedString = "StarRatingDTO [userId=1, comicBookId=1, rating=5]";
        assertEquals(expectedString, starRatingDto.toString());
    }

}
