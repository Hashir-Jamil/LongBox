package org.longbox.integration.persistence.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.config.HibernateUtils;
import org.longbox.persistence.dao.ComicBookReadingListDaoImpl;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComicBookReadingListDaoImplTest {
    ComicBookReadingListDaoImpl readingListDao;

    @BeforeEach
    void setup() {
        readingListDao = new ComicBookReadingListDaoImpl(HibernateUtils.getSessionFactory());
    }

    /*
     * This test should always pass
     * The first run will not have an exception
     * The second run will not insert as it is already inserted but insertion will pass
     */
    @Test
    void saveToReadingTest() {
        Long userId = 1L;
        Long comicId = 1L;
        try {
            readingListDao.removeFromReading(userId,comicId);
            readingListDao.saveToReading(userId, comicId);
        } catch (UserIDDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        assertTrue(readingListDao.doesRecordExist(userId,comicId));
    }

    /*
     * This test should always pass
     * The first run will not have an exception
     * The second run will not insert as it is already inserted but insertion will pass
     */
    @Test
    void removeFromReadingTest() {
        Long userId = 1L;
        Long comicId = 1L;
        try {
            readingListDao.saveToReading(userId, comicId);
            readingListDao.removeFromReading(userId,comicId);
        } catch (UserIDDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        assertFalse(readingListDao.doesRecordExist(userId,comicId));
    }

    @Test
    void removeFromReadingNoRecord() {
        Long userId = 20L;
        Long comicId = 100L;
        readingListDao.removeFromReading(userId, comicId);
        assertTrue(true);
    }

    @Test
    void doesRecordExistNoRecord() {
        Long userId = 20L;
        Long comicId = 100L;
        assertFalse(readingListDao.doesRecordExist(userId, comicId));
    }
}
