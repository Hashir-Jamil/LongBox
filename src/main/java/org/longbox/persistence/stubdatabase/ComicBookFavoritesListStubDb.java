package org.longbox.persistence.stubdatabase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.ComicBookListItemDtoFavoriteDto;
import org.longbox.domainobjects.dto.JsonConvertor;
import org.longbox.persistence.dao.ComicBookFavouritesListDao;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ComicBookFavoritesListStubDb implements ComicBookFavouritesListDao, JsonConvertor {

    private List<ComicBookListItemDtoFavoriteDto> comicBookStubData = new ArrayList<>();
    private final String ABSOLUTE_FILE_PATH = "src/main/resources/ComicBookFavoritesListStubDb.json";

    public void loadData() {
        //TO-DO
    }

    @Override
    public void saveToFavorites(long userId, long comicBookIdd) throws UserIDDoesNotExistException {

    }

    @Override
    public int removeFromFavorites(long userId, long comicBookId) {
        return 0;
    }

    @Override
    public List<ComicBook> getFavoritesByUser(long userId) {
        return null;
    }

    @Override
    public List<User> getUsersByComicBook(long comicBookId) {
        return null;
    }

    @Override
    public List<ComicBookDto> getAllFavoritesComicBooks() {
        return null;
    }

    @Override
    public void serializeStubData() {

    }

    @Override
    public List<ComicBookListItemDtoFavoriteDto> deserializeStubData(String filepath) {
        return null;
    }
}
