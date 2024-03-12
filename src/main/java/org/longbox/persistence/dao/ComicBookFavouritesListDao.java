package org.longbox.persistence.dao;

import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.User;

import java.util.List;

public interface ComicBookFavouritesListDao {

    void saveToFavorites(Long userId, Long comicBookIdd) throws UserIDDoesNotExistException;

    void removeFromFavorites(Long userId, Long comicBookId);

    boolean doesRecordExist(Long userId, Long comicBookId);

    List<ComicBook> getFavoritesByUser(Long userId);

    List<User> getUsersByComicBook(Long comicBookId);

    List<ComicBookDto> getAllFavoritesComicBooks();
}
