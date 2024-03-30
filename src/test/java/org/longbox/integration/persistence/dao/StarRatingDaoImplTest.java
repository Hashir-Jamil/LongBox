package org.longbox.integration.persistence.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.StarRatingDto;
import org.longbox.domainobjects.entity.StarRating;
import org.longbox.persistence.dao.StarRatingDao;
import org.longbox.persistence.dao.StarRatingDaoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

public class StarRatingDaoImplTest {

    StarRatingDaoImpl starRatingDaoImpl;

    @BeforeEach
    void setup() {
        starRatingDaoImpl = new StarRatingDaoImpl(HibernateUtils.getSessionFactory());
    }

    @Test
	void test_getStarRatingsByComic_1() {
		List<StarRatingDto> actualList = starRatingDaoImpl.getStarRatingsByComic(1L);
		int actual = actualList.size();
		assertEquals(2, actual);
	}
    
    @Test
   	void test_getStarRatingsByComic_2() {
   		List<StarRatingDto> actualList = starRatingDaoImpl.getStarRatingsByComic(2L);
   		int actual = actualList.size();
   		assertEquals(2, actual);
   	}
    
    @Test
   	void test_getStarRatingsByComic_3() {
   		List<StarRatingDto> actualList = starRatingDaoImpl.getStarRatingsByComic(3L);
   		int actual = actualList.size();
   		assertEquals(2, actual);
   	}
    
    @Test
    void test_ratingNotFound() {
        StarRating starRating = starRatingDaoImpl.getStarRatingById(101L,100L);
        assertEquals(null,starRating);
    }
    
    @Test
    void test_saveStarRating() {
    	StarRatingDto addStarRating = new StarRatingDto();
    	addStarRating.setUserId(1);
    	addStarRating.setComicBookId(1);
    	addStarRating.setRating(5);
    	starRatingDaoImpl.saveStarRating(addStarRating);
    	assertEquals(starRatingDaoImpl.getStarRatingById(1, 1).getRating(), 5);
    }
    
    @Test
    void test_getStarRatingbyId_1() {
    	StarRating actualStarRating = starRatingDaoImpl.getStarRatingById(1L, 1L);
    	assertEquals(actualStarRating.getRating(), 5);
    }

    @Test
    void test_getStarRatingbyId_2() {
    	StarRating actualStarRating = starRatingDaoImpl.getStarRatingById(3L, 1L);
    	assertNull(actualStarRating);
    }

    @Test
    void test_getStarRatingbyId_3() {
    	StarRating actualStarRating = starRatingDaoImpl.getStarRatingById(25L, 2L);
    	assertEquals(actualStarRating.getRating(), 5);
    }
}
