package org.longbox.businesslogic.service;

import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookFavouritesListDao;
import org.longbox.persistence.dao.ComicBookFinishedListDao;
import org.longbox.persistence.dao.ComicBookReadingListDao;
import org.longbox.domainobjects.entity.ComicBook;

import java.util.List;

public class UserComicBookCollectionService {

    ComicBookFavouritesListDao favouritesListDao;
    ComicBookReadingListDao readingListDao;
    ComicBookFinishedListDao finishedListDao;

    public UserComicBookCollectionService(
        ComicBookFavouritesListDao favouritesListDao,
        ComicBookReadingListDao readingListDao,
        ComicBookFinishedListDao finishedListDao
    ) {
        this.favouritesListDao = favouritesListDao;
        this.readingListDao = readingListDao;
        this.finishedListDao = finishedListDao;
    }

    // Favourites list services
    public void saveToFavourites(Long userId, Long comicBookId) throws UserIDDoesNotExistException {
        favouritesListDao.saveToFavourites(userId, comicBookId);
    }

    public boolean removeFromFavourites(Long userId, Long comicBookId) {
        return favouritesListDao.removeFromFavourites(userId, comicBookId);
    }

    public List<ComicBookDto> getAllFavouritesComicBooks() {
        return favouritesListDao.getAllFavouritesComicBooks();
    }

    // Reading list services
    public void saveToReading(Long userId, Long comicBookId) throws UserIDDoesNotExistException {
        readingListDao.saveToReading(userId, comicBookId);
    }

    public void removeFromReading(Long userId, Long comicBookId) {
        readingListDao.removeFromReading(userId, comicBookId);
    }

    public List<ComicBook> getUserReadingList(Long userId) {
        return readingListDao.getUsersReadingList(userId);
    }

    // Finished list services
    public void saveToFinished(Long userId, Long comicBookId) throws UserIDDoesNotExistException {
        finishedListDao.saveToFinished(userId, comicBookId);
    }

    public void removeFromFinished(Long userId, Long comicBookId) {
        finishedListDao.removeFromFinished(userId, comicBookId);
    }

    public List<ComicBook> getUserFinishedList(Long userId) {
        return finishedListDao.getUsersFinishedList(userId);
    }
}
