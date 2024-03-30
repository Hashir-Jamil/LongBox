package org.longbox.unit.persistence.stubdatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.StarRatingDto;
import org.longbox.domainobjects.entity.StarRating;
import org.longbox.persistence.stubdatabase.StarRatingStubDb;

public class StarRatingStubDbTest {
	StarRatingStubDb starRatingStubDb;
	List<StarRatingDto> starRatingList;
	StarRatingDto starRating1, starRating2;
	
	@BeforeEach
    void setup(){
        starRatingStubDb = new StarRatingStubDb();
        starRatingStubDb.loadJsonToArrayList();
        starRatingList = starRatingStubDb.deserializeStubData(starRatingStubDb.getABSOLUTE_FILE_PATH());

        starRating1 = new StarRatingDto();
        starRating1.setUserId(1L);
        starRating1.setComicBookId(1L);
        starRating1.setRating(5);

        starRating2 = new StarRatingDto();
        starRating2.setUserId(3L);
        starRating2.setComicBookId(8L);
        starRating2.setRating(3);
    }
	
	 @Test
	    void test_serialize(){
	        starRatingStubDb.deserializeStubData(starRatingStubDb.getABSOLUTE_FILE_PATH());
	        starRatingStubDb.serializeStubData();
	    }
	 
	 @Test
	    void test_deserialize(){
	        assertNotNull(starRatingList);
	        assertEquals(101, starRatingList.size());
	        assertEquals(7, starRatingList.get(0).getUserId());
	        assertEquals(1, starRatingList.get(0).getComicBookId());
	        assertEquals(3, starRatingList.get(0).getRating());
	        assertNotEquals(starRating1, starRatingList.get(1));
	    }
	 
	 @Test
	 	void test_getStarRatingById_1() {
		 StarRating s = starRatingStubDb.getStarRatingById(7L, 1L);
		 assertEquals(3, s.getRating());
	 }
	 
	 @Test
	 	void test_getStarRatingById_2() {
		 StarRating s = starRatingStubDb.getStarRatingById(25L, 2L);
		 assertEquals(5, s.getRating());
	 }
	 
	 @Test
	    void test_getStarRatingsByComic_1(){
	        List<StarRatingDto> actualList = starRatingStubDb.getStarRatingsByComic(1L);
	        int actual = actualList.size();
	        assertEquals(1, actual);
	    }

	    @Test
	    void test_getStarRatingsByComic_2(){
	        List<StarRatingDto> actualList = starRatingStubDb.getStarRatingsByComic(14L);
	        int actual = actualList.size();
	        assertEquals(2, actual);
	    }
	    
	    @Test
	    void test_getStarRatingsByUser_1(){
	        List<StarRatingDto> actualList = starRatingStubDb.getStarRatingsByUser(1L);
	        int actual = actualList.size();
	        assertEquals(0, actual);
	    }

	    @Test
	    void test_getStarRatingsByUser_2(){
	        List<StarRatingDto> actualList = starRatingStubDb.getStarRatingsByUser(5L);
	        int actual = actualList.size();
	        assertEquals(2, actual);
	    }

	    @Test
	    void test_createNewStarRating(){
	    	assertNull(starRatingStubDb.getStarRatingById(3L, 8L));
	    	starRatingStubDb.saveStarRating(starRating2);
	        assertEquals(3, starRatingStubDb.getStarRatingById(3L, 8L).getRating());
	    }

	    @Test
	    void test_updateExistingStarRating(){
	    	starRating2.setRating(4);
	    	starRatingStubDb.saveStarRating(starRating2);
	        assertEquals(4, starRatingStubDb.getStarRatingById(3L, 8L).getRating());
	    }
}
