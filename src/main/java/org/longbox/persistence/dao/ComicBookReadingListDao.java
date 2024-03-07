package org.longbox.persistence.dao;

import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.User;

import java.util.List;

public interface ComicBookReadingListDao {

    boolean saveToReading(long userId, long comicBookIdd) throws UserIDDoesNotExistException;

    boolean removeFromReading(long userId, long comicBookId);

    List<ComicBook> getUsersReadingList(long userId);

    List<User> getListOfUsersReading(long comicBookId);

}
