package org.longbox.persistence.dao;

import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.ComicBookFinishedList;
import org.longbox.persistence.entity.User;

import java.util.List;

public interface ComicBookFinishedListDao {

    void saveToFinished(Long userId, Long comicBookId) throws UserIDDoesNotExistException;

    void removeFromFinished(Long userId, Long comicBookId);

    boolean doesRecordExist(Long userId, Long comicBookId);

    ComicBookFinishedList getFinishedComicBook(Long userId, Long comicBookId);

    List<ComicBook> getUsersFinishedList(Long userId);

    List<User> getListOfUsersFinished(Long comicBookId);
}
