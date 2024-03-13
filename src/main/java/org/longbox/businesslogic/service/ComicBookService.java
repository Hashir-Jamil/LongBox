package org.longbox.businesslogic.service;

import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.mapper.ComicBookMapper;
import org.longbox.persistence.dao.ComicBookDao;
import org.longbox.domainobjects.entity.ComicBook;

import java.util.List;

public class ComicBookService {

    ComicBookDao comicBookDao;

    public ComicBookService(ComicBookDao comicBookDao) {
        this.comicBookDao = comicBookDao;
    }

    public ComicBookDto getComicBookById(Long comicId) {
        return ComicBookMapper.toDto(comicBookDao.getComicBookById(comicId));
        //return new ComicBookDto(comicBookDao.getComicBookById(comicId));
    }

    public ComicBookDto getComicBookBySeriesName(String seriesTitle) {
        return ComicBookMapper.toDto(comicBookDao.getComicBookBySeriesTitle(seriesTitle));
        //return new ComicBookDto(comicBookDao.getComicBookBySeriesTitle(seriesTitle));
    }

    public Long saveComicBook(ComicBookDto comicBookDto) {
        return comicBookDao.saveComicBook(ComicBookMapper.toEntity(comicBookDto));
        //return comicBookDao.saveComicBook(new ComicBook(comicBookDto));
    }

    public boolean deleteComicBook(ComicBookDto comicBookDto) {
        return false;
    }

    public List<ComicBookDto> getAllComicBook() {
        return comicBookDao.getAllComicBooks();
    }
}
