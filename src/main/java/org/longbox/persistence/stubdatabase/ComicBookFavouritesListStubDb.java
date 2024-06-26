package org.longbox.persistence.stubdatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.ComicBookListItemFavouriteDto;
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
public class ComicBookFavouritesListStubDb implements ComicBookFavouritesListDao, JsonConvertor {
    private List<ComicBookListItemFavouriteDto> records = new ArrayList<>();
    private final String ABSOLUTE_FILE_PATH = "src/main/resources/ComicBookFavouritesListStubDb.json";

    @Override
    public void saveToFavourites(Long userId, Long comicBookId) throws UserIDDoesNotExistException {
        List<ComicBookListItemFavouriteDto> readingList = deserializeStubData(ABSOLUTE_FILE_PATH);
        readingList.add(new ComicBookListItemFavouriteDto(userId, comicBookId));
    }

    @Override
    public boolean removeFromFavourites(Long userId, Long comicBookId) {
        List<ComicBookListItemFavouriteDto> favouritesList = deserializeStubData(ABSOLUTE_FILE_PATH);
        for (int i = 0; i < favouritesList.size(); i++) {
            if (favouritesList.get(i).getUserId() == userId && favouritesList.get(i).getComicBookId() == comicBookId) {
                favouritesList.remove(i);
                break;
            }
        }
        records = favouritesList;
        serializeStubData();
        return favouritesList.isEmpty();
    }

    @Override
    public boolean doesRecordExist(Long userId, Long comicBookId) {
        return false;
    }

    @Override
    public List<ComicBook> getFavouritesByUser(Long userId) {
        return null;
    }

    @Override
    public List<User> getUsersByComicBook(Long comicBookId) {
        return null;
    }

    @Override
    public List<ComicBookDto> getAllFavouritesComicBooks() {
        return null;
    }

    @Override
    public void serializeStubData() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String json = gson.toJson(records);
        try (PrintStream out = new PrintStream(new FileOutputStream(ABSOLUTE_FILE_PATH))) {
            out.print(json);
        } catch (FileNotFoundException fe) {
            throw new RuntimeException(fe);
        }
    }

    @Override
    public List<ComicBookListItemFavouriteDto> deserializeStubData(String filepath) {
        Type listType = new TypeToken<List<ComicBookListItemFavouriteDto>>(){}.getType();
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
