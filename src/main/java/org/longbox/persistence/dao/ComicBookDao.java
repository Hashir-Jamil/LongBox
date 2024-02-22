package org.longbox.persistence.dao;

import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.User;

public interface ComicBookDao {

    ComicBook getComicBookById(long id);

    ComicBook getComicBookBySeriesName(String seriesName);

    void saveComicBook(ComicBook comicBook);

    boolean deleteComicBook(ComicBook comicBook);

    boolean modifyComicBook(ComicBook comicBook);

}
