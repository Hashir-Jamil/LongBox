package org.longbox.persistence.dao;

import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.User;

import java.util.List;

public class ComicBookReadingListDaoImpl implements ComicBookReadingListDao {
    @Override
    public boolean saveToReading(long userId, long comicBookIdd) throws UserIDDoesNotExistException {
        return false;
    }

    @Override
    public boolean removeFromReading(long userId, long comicBookId) {
        return false;
    }

    @Override
    public List<ComicBook> getUsersReadingList(long userId) {
        return null;
    }

    @Override
    public List<User> getListOfUsersReading(long comicBookId) {
        return null;
    }
}
