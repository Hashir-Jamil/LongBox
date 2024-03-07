package org.longbox.persistence.dao;

import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.User;

import java.util.List;

public interface ComicBookFinishedListDao {

    boolean saveToFinished(User user, ComicBook comicBook) throws UserIDDoesNotExistException;

    int removeFromFavorites(Long userId, Long comicBookId);

    List<ComicBook> getUsersFinishedList(Long userId);

    List<User> getListOfUsersFinished(Long comicBookId);
}
