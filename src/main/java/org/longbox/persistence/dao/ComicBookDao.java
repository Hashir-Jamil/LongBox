package org.longbox.persistence.dao;

import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.User;

import java.util.List;

public interface ComicBookDao {

    ComicBook getComicBookById(long id);

    ComicBook getComicBookBySeriesName(String seriesName);

    void saveComicBook(ComicBookDTO comicBook);

    boolean deleteComicBook(ComicBook comicBook);

    boolean modifyComicBook(ComicBook comicBook);

    List<ComicBookDTO> getAllComicBooks();

}
