package org.longbox.persistence.dao;

import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.User;

public interface ComicBookDao {

    User getComicBookById(long id);

    User getComicBookBySeriesName(String seriesName);

    User saveComicBook(ComicBook comicBook);

    boolean deleteComicBook(ComicBook comicBook);

    boolean modifyComicBook(ComicBook comicBook);

}
