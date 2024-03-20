package org.longbox.persistence.stubdatabase;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.utils.GenreUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import com.google.gson.Gson;
import org.longbox.persistence.dao.ComicBookDao;
import org.longbox.domainobjects.entity.ComicBook;

@Getter
@Setter
public class ComicBookStubDb implements ComicBookDao, JsonConvertor {
    private List<ComicBookDto> comicBookStubData;
    private final String ABSOLUTE_FILE_PATH = "src/main/resources/ComicBookStubDb.json";

    public ComicBookStubDb() {
    }

    @Override
    public ComicBook getComicBookById(Long id) {
        comicBookStubData = deserializeStubData(ABSOLUTE_FILE_PATH);
        for (ComicBookDto comic : comicBookStubData) {
            if (comic.getId() == id) {
                ComicBook c = new ComicBook(comic);
                c.setId(comic.getId());
                return c;
            }
        }
        return new ComicBook();
    }

    @Override
    public ComicBook getComicBookBySeriesTitle(String seriesTitle) {
        comicBookStubData = deserializeStubData(ABSOLUTE_FILE_PATH);
        for (ComicBookDto comic : comicBookStubData) {
            if (Objects.equals(comic.getSeriesTitle(), seriesTitle)) {
                ComicBook c = new ComicBook(comic);
                c.setId(comic.getId());
                return c;
            }
        }
        return new ComicBook();
    }

    @Override
    public Long saveComicBook(ComicBook comicBook) {
        List<ComicBookDto> comics = deserializeStubData(ABSOLUTE_FILE_PATH);
        ComicBookDto comic = new ComicBookDto(comicBook);
        comics.add(comic);
        comicBookStubData = comics;
        serializeStubData();
        return comicBook.getId();
    }

    @Override
    public boolean deleteComicBook(ComicBook comicBook) {
        List<ComicBookDto> comics = deserializeStubData(ABSOLUTE_FILE_PATH);
        if (comics.remove(comicBook)) {
            comicBookStubData = comics;
            serializeStubData();
            return true;
        }
        return false;
    }

    @Override
    public boolean modifyComicBook(ComicBook comicBook) {
        List<ComicBookDto> comics = deserializeStubData(ABSOLUTE_FILE_PATH);
        ComicBookDto comicBookDto = new ComicBookDto(comicBook);
        for (ComicBookDto comic : comics) {
            if (comic.equals(comicBookDto)) {
                comic.setId(comicBook.getId());
                comic.setSeriesTitle(comicBook.getSeriesTitle());
                comic.setAuthor(comicBook.getAuthor());
                comic.setArtist(comicBook.getArtist());
                comic.setGenres(GenreUtils.genreStringToList(comicBook.getGenres()));
                comic.setDescription(comicBook.getDescription());
                comic.setNumberOfIssues(comicBook.getNumberOfIssues());
                comic.setYearPublished(comicBook.getYearPublished());
                comic.setPublisher(comicBook.getPublisher());
                comic.setDateAdded(comicBook.getDateAdded());
                setComicBookStubData(comics);
                serializeStubData();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ComicBookDto> getAllComicBooks() {
        //return ComicBookMapper.toEntityList(deserializeStubData(ABSOLUTE_FILE_PATH));
        return deserializeStubData(ABSOLUTE_FILE_PATH);
    }

    @Override
    public List<ComicBookDto> getRecommendationsByGenre(String[] genres) {
        return null;
    }

    @Override
    public void serializeStubData() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String json = gson.toJson(comicBookStubData);
        try (PrintStream out = new PrintStream(new FileOutputStream(ABSOLUTE_FILE_PATH))) {
            out.print(json);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ComicBookDto> deserializeStubData(String filepath) {
        Type listType = new TypeToken<List<ComicBookDto>>(){}.getType();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(filepath));
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        return gson.fromJson(reader, listType);
    }
}
