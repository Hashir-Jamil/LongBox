package org.longbox.integration.persistence.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.config.HibernateUtils;
import org.longbox.persistence.dao.ComicBookFavouritesListDaoImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComicBookFavouritesListDaoImplTest {

    ComicBookFavouritesListDaoImpl favouritesListDao;

    @BeforeEach
    void setup() {
        favouritesListDao = new ComicBookFavouritesListDaoImpl(HibernateUtils.getSessionFactory());
        favouritesListDao.removeFromFavourites(5L,30L);
    }

    @Test
    void saveToFavouritesTest() {
        try {
            favouritesListDao.removeFromFavourites(6L,20L);
            favouritesListDao.saveToFavourites(6L,20L);
        } catch (UserIDDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        assertTrue(favouritesListDao.doesRecordExist(6L,20L));
    }

    @Test
    void removeFromFavouritesTest() {
        try {
            favouritesListDao.saveToFavourites(5L,30L);
            favouritesListDao.removeFromFavourites(5L,30L);
        } catch (UserIDDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        assertFalse(favouritesListDao.doesRecordExist(5L,30L));
    }

    @Test
    void doesRecordExistTest() {
        assertFalse(favouritesListDao.doesRecordExist(5L,30L));
    }

}
