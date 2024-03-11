package org.longbox.persistence.stubdatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookListItemFinishedDto;
import org.longbox.persistence.dao.ComicBookFinishedListDao;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.ComicBookFinishedList;
import org.longbox.persistence.entity.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.util.List;
@Getter
@Setter
public class ComicBookFinishedListStubDb implements ComicBookFinishedListDao, JsonConvertor {

    private List<ComicBookListItemFinishedDto> records;
    private final String ABSOLUTE_FILE_PATH = "src/main/resources/ComicBookFinishedListStubDb.json";

    @Override
    public void saveToFinished(Long userId, Long comicBookId) throws UserIDDoesNotExistException {
        List<ComicBookListItemFinishedDto> finishedList = deserializeStubData(ABSOLUTE_FILE_PATH);
        finishedList.add(new ComicBookListItemFinishedDto(userId, comicBookId));
        records = finishedList;
        serializeStubData();
    }

    @Override
    public void removeFromFinished(Long userId, Long comicBookId) {
        List<ComicBookListItemFinishedDto> finishedList = deserializeStubData(ABSOLUTE_FILE_PATH);
        for (int i = 0; i < finishedList.size(); i++) {
            if (finishedList.get(i).getUserId() == userId && finishedList.get(i).getComicBookId() == comicBookId) {
                finishedList.remove(i);
            }
        }
        records = finishedList;
        serializeStubData();
    }

    @Override
    public boolean doesRecordExist(Long userId, Long comicBookId) {
        return false;
    }

    @Override
    public ComicBookFinishedList getFinishedComicBook(Long userId, Long comicBookId) {
        return null;
    }

    @Override
    public List<ComicBook> getUsersFinishedList(Long userId) {
        return null;
    }

    @Override
    public List<User> getListOfUsersFinished(Long comicBookId) {
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
    public List<ComicBookListItemFinishedDto> deserializeStubData(String filepath) {
        Type listType = new TypeToken<List<ComicBookListItemFinishedDto>>(){}.getType();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(filepath));
            return new Gson().fromJson(reader, listType);
        } catch (FileNotFoundException fe) {
            throw new RuntimeException(fe);
        }
    }
}
