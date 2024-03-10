package org.longbox.persistence.stubdatabase;

import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookListItemDtoFinishedDto;
import org.longbox.domainobjects.dto.JsonConvertor;
import org.longbox.persistence.dao.ComicBookFinishedListDao;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.ComicBookFinishedList;
import org.longbox.persistence.entity.User;

import java.util.List;

public class ComicBookFinishedListStubDb implements ComicBookFinishedListDao, JsonConvertor {


    List<ComicBookFinishedListStubDb> finishedList;
    @Override
    public void saveToFinished(Long userId, Long comicBookId) throws UserIDDoesNotExistException {

    }

    @Override
    public void removeFromFinished(Long userId, Long comicBookId) {

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

    }

    @Override
    public List<ComicBookListItemDtoFinishedDto> deserializeStubData(String filepath) {
        return null;
    }
}
