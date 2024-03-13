package org.longbox.persistence.stubdatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.ComicBookListItemFavoriteDto;
import org.longbox.persistence.dao.ComicBookFavouritesListDao;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.domainobjects.entity.User;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ComicBookFavoritesListStubDb implements ComicBookFavouritesListDao, JsonConvertor {
    private List<ComicBookListItemFavoriteDto> records = new ArrayList<>();
    private final String ABSOLUTE_FILE_PATH = "src/main/resources/ComicBookFavoritesListStubDb.json";

    @Override
    public void saveToFavorites(Long userId, Long comicBookId) throws UserIDDoesNotExistException {
        List<ComicBookListItemFavoriteDto> readingList = deserializeStubData(ABSOLUTE_FILE_PATH);
        readingList.add(new ComicBookListItemFavoriteDto(userId, comicBookId));
    }

    @Override
    public void removeFromFavorites(Long userId, Long comicBookId) {
        List<ComicBookListItemFavoriteDto> favoritesList = deserializeStubData(ABSOLUTE_FILE_PATH);
        for (int i = 0; i < favoritesList.size(); i++) {
            if (favoritesList.get(i).getUserId() == userId && favoritesList.get(i).getComicBookId() == comicBookId) {
                favoritesList.remove(i);
                break;
            }
        }
        records = favoritesList;
        serializeStubData();
    }

    @Override
    public boolean doesRecordExist(Long userId, Long comicBookId) {
        return false;
    }

    @Override
    public List<ComicBook> getFavoritesByUser(Long userId) {
        return null;
    }

    @Override
    public List<User> getUsersByComicBook(Long comicBookId) {
        return null;
    }

    @Override
    public List<ComicBookDto> getAllFavoritesComicBooks() {
        return null;
    }

    @Override
    public void serializeStubData() {
        String json = new Gson().toJson(records);
        try (PrintStream out = new PrintStream(new FileOutputStream(ABSOLUTE_FILE_PATH))) {
            out.print(json);
        } catch (FileNotFoundException fe) {
            throw new RuntimeException(fe);
        }
    }

    @Override
    public List<ComicBookListItemFavoriteDto> deserializeStubData(String filepath) {
        Type listType = new TypeToken<List<ComicBookListItemFavoriteDto>>(){}.getType();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(filepath));
        } catch (FileNotFoundException fe) {
            throw new RuntimeException(fe);
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        return new Gson().fromJson(reader, listType);
    }
}
