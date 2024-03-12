package org.longbox.businesslogic.service;

import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDao;
import org.longbox.persistence.entity.ComicBook;

import java.util.List;

public class ComicBookService {

    ComicBookDao comicBookDao;

    public ComicBookService(ComicBookDao comicBookDao) {
        this.comicBookDao = comicBookDao;
    }

    public ComicBookDto getComicBookById(Long comicId) {
        return new ComicBookDto(comicBookDao.getComicBookById(comicId));
    }

    public ComicBookDto getComicBookBySeriesName(String seriesTitle) {
        return new ComicBookDto(comicBookDao.getComicBookBySeriesTitle(seriesTitle));
    }

    public Long saveComicBook(ComicBookDto comicBookDto) {
        return comicBookDao.saveComicBook(new ComicBook(comicBookDto));
    }

    public boolean deleteComicBook(ComicBookDto comicBookDto) {
        return false;
    }

    public List<ComicBookDto> getAllComicBook() {
        return comicBookDao.getAllComicBooks();
    }
}
