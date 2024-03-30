package org.longbox.integration.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.service.StarRatingService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.StarRatingDto;
import org.longbox.persistence.dao.StarRatingDaoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StarRatingServiceTest {
    StarRatingService starRatingService;

    @BeforeEach
    void setup() {
        starRatingService = new StarRatingService(new StarRatingDaoImpl(HibernateUtils.getSessionFactory()));
    }

    @Test
    void test_getStarRatingsByComic_1() {
        String actualAvgStarRating = starRatingService.getAvgStarRatingsByComic(1L);
        assertEquals("4", actualAvgStarRating);
    }

    @Test
    void test_getStarRatingsByComic_2() {
        String actualAvgStarRating = starRatingService.getAvgStarRatingsByComic(11L);
        assertEquals("3.5", actualAvgStarRating);
    }

    @Test
    void test_getStarRatingsByComic_3() {
        String actualAvgStarRating = starRatingService.getAvgStarRatingsByComic(6L);
        assertEquals("3", actualAvgStarRating);
    }

    @Test
    void test_ratingNotFound() {
        String starRating = starRatingService.getStarRatingByID(101L,100L);
        assertEquals("Not rated",starRating);
    }

    @Test
    void test_saveStarRating() {
        StarRatingDto addStarRating = new StarRatingDto();
        addStarRating.setUserId(1);
        addStarRating.setComicBookId(1);
        addStarRating.setRating(5);
        starRatingService.saveStarRating(addStarRating);
        assertEquals(starRatingService.getStarRatingByID(1L, 1L), "5");
    }

    @Test
    void test_getStarRatingById_1() {
        String actualStarRating = starRatingService.getStarRatingByID(1L, 1L);
        assertEquals(actualStarRating, "5");
    }

    @Test
    void test_getStarRatingById_2() {
        String actualStarRating = starRatingService.getStarRatingByID(3L, 1L);
        assertEquals("Not rated", actualStarRating);
    }

    @Test
    void test_getStarRatingById_3() {
        String actualStarRating = starRatingService.getStarRatingByID(25L, 2L);
        assertEquals(actualStarRating, "5");
    }
}
