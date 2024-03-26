package org.longbox.persistence.dao;

import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.domainobjects.entity.User;

import java.util.List;

public interface ComicBookFavouritesListDao {

    void saveToFavourites(Long userId, Long comicBookIdd) throws UserIDDoesNotExistException;

    boolean removeFromFavourites(Long userId, Long comicBookId);

    boolean doesRecordExist(Long userId, Long comicBookId);

    List<ComicBook> getFavouritesByUser(Long userId);

    List<User> getUsersByComicBook(Long comicBookId);

    List<ComicBookDto> getAllFavouritesComicBooks();
}
