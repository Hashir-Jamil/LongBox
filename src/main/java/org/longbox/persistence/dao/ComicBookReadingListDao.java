package org.longbox.persistence.dao;

import org.hibernate.SessionFactory;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.User;
import org.longbox.utils.HibernateUtils;

import java.util.List;

public interface ComicBookReadingListDao {

    boolean saveToReading(User user, ComicBook comicBook) throws UserIDDoesNotExistException;

    void removeFromReading(Long userId, Long comicBookId);

    List<ComicBook> getUsersReadingList(Long userId);

    List<User> getListOfUsersReading(Long comicBookId);

}
