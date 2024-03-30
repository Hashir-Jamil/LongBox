package org.longbox.persistence.stubdatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookListItemFinishedDto;
import org.longbox.persistence.dao.ComicBookFinishedListDao;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.domainobjects.entity.ComicBookFinishedList;
import org.longbox.domainobjects.entity.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

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
            if (Objects.equals(finishedList.get(i).getUserId(), userId) && Objects.equals(finishedList.get(i).getComicBookId(), comicBookId)) {
                finishedList.remove(i);
            }
        }
        records = finishedList;
        serializeStubData();
    }

    @Override
    public boolean doesRecordExist(Long userId, Long comicBookId) {
        List<ComicBookListItemFinishedDto> finishedList = deserializeStubData(ABSOLUTE_FILE_PATH);
        for (ComicBookListItemFinishedDto record : finishedList) {
            if (Objects.equals(record.getUserId(), userId) && Objects.equals(record.getComicBookId(), comicBookId)) {
                return true;
            }
        }
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
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String json = gson.toJson(records);
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
        } catch (FileNotFoundException fe) {
            throw new RuntimeException(fe);
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        return new Gson().fromJson(reader, listType);
    }
}
