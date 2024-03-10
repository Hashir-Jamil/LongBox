package org.longbox.persistence.stubdatabase;

import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookListItemDtoReadingDto;
import org.longbox.domainobjects.dto.JsonConvertor;
import org.longbox.persistence.dao.ComicBookReadingListDao;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.ComicBookReadingList;
import org.longbox.persistence.entity.User;

import java.util.List;

public class ComicBookReadingListStubDb implements ComicBookReadingListDao, JsonConvertor {
    @Override
    public void saveToReading(Long userId, Long comicBookId) throws UserIDDoesNotExistException {

    }

    @Override
    public void removeFromReading(Long userId, Long comicBookId) {

    }

    @Override
    public boolean doesRecordExist(Long userId, Long comicBookId) {
        return false;
    }

    @Override
    public ComicBookReadingList getReadingComicBook(Long userId, Long comicBookId) {
        return null;
    }

    @Override
    public List<ComicBook> getUsersReadingList(Long userId) {
        return null;
    }

    @Override
    public List<User> getListOfUsersReading(Long comicBookId) {
        return null;
    }

    @Override
    public void serializeStubData() {

    }

    @Override
    public List<ComicBookListItemDtoReadingDto> deserializeStubData(String filepath) {
        return null;
    }
}
