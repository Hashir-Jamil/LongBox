package org.longbox.persistence.dao;

import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.domainobjects.entity.ComicBookReadingList;
import org.longbox.domainobjects.entity.User;

import java.util.List;

public interface ComicBookReadingListDao {

    void saveToReading(Long userId, Long comicBookId) throws UserIDDoesNotExistException;

    void removeFromReading(Long userId, Long comicBookId);

    boolean doesRecordExist(Long userId, Long comicBookId);

    ComicBookReadingList getReadingComicBook(Long userId, Long comicBookId);

    List<ComicBook> getUsersReadingList(Long userId);

    List<User> getListOfUsersReading(Long comicBookId);

}
