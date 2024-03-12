package org.longbox.integration.persistence.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.config.HibernateUtils;
import org.longbox.persistence.dao.ComicBookFinishedListDaoImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComicBookFinishedListDaoImplTest {
    ComicBookFinishedListDaoImpl finishedListDao;

    @BeforeEach
    void setup() {
        finishedListDao = new ComicBookFinishedListDaoImpl(HibernateUtils.getSessionFactory());
    }

    /*
     * This test should always pass
     * The first run will not have an exception
     * The second run will not insert as it is already inserted but insertion will pass
     */
    @Test
    void saveToFinishedTest() {
        Long userId = 1L;
        Long comicId = 1L;
        try {
            finishedListDao.removeFromFinished(userId,comicId);
            finishedListDao.saveToFinished(userId, comicId);
        } catch (UserIDDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        assertTrue(finishedListDao.doesRecordExist(userId,comicId));
    }

    /*
     * This test should always pass
     * The first run will not have an exception
     * The second run will not insert as it is already inserted but insertion will pass
     */
    @Test
    void removeFromFinishedTest() {
        Long userId = 1L;
        Long comicId = 1L;
        try {
            finishedListDao.saveToFinished(userId, comicId);
            finishedListDao.removeFromFinished(userId,comicId);
        } catch (UserIDDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        assertFalse(finishedListDao.doesRecordExist(userId,comicId));
    }

    @Test
    void removeFromFinishedListNoRecord() {
        Long userId = 20L;
        Long comicId = 100L;
        finishedListDao.removeFromFinished(userId,comicId);
        assertTrue(true);
    }

    @Test
    void doesRecordExistNoRecord() {
        Long userId = 20L;
        Long comicId = 100L;
        assertFalse(finishedListDao.doesRecordExist(userId,comicId));
    }

}
