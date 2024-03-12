package org.longbox.persistence.dao;

import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.entity.ComicBook;

import java.util.List;

public interface ComicBookDao {

    ComicBook getComicBookById(Long id);

    ComicBook getComicBookBySeriesTitle(String seriesName);

    Long saveComicBook(ComicBook comicBook);

    boolean deleteComicBook(ComicBook comicBook);

    boolean modifyComicBook(ComicBook comicBook);

    List<ComicBookDto> getAllComicBooks();

}
