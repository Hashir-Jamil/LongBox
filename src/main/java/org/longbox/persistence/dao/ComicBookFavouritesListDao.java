package org.longbox.persistence.dao;

import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.User;

import java.util.List;

public interface ComicBookFavouritesListDao {

    void saveToFavorites(long userId, long comicBookIdd) throws UserIDDoesNotExistException;

    void removeFromFavorites(long userId, long comicBookId);

    List<ComicBook> getFavoritesByUser(long userId);

    List<User> getUsersByComicBook(long comicBookId);

    List<ComicBookDTO> getAllFavoritesComicBooks();
}