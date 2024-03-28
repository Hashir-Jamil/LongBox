package org.longbox.integration.persistence.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.entity.StarRating;
import org.longbox.persistence.dao.StarRatingDao;
import org.longbox.persistence.dao.StarRatingDaoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StarRatingDaoImplTest {

    StarRatingDao starRatingDao;

    @BeforeEach
    void setup() {
        starRatingDao = new StarRatingDaoImpl(HibernateUtils.getSessionFactory());
    }

    @Test
    void ratingNotFound() {
        StarRating starRating = starRatingDao.getStarRatingById(100L,100L);
        assertEquals(null,starRating);
    }

}
