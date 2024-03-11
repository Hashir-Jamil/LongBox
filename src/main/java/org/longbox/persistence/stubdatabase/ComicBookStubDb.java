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
import org.longbox.domainobjects.dto.ComicBookDto;
import com.google.gson.Gson;
import org.longbox.persistence.dao.ComicBookDao;
import org.longbox.persistence.entity.ComicBook;

@Getter
@Setter
public class ComicBookStubDb implements ComicBookDao, JsonConvertor {

    private List<ComicBookDto> comicBookStubData;
    private final String ABSOLUTE_FILE_PATH = "src/main/resources/ComicBookStubDb.json";

    public ComicBookStubDb() {
    }

    @Override
    public ComicBook getComicBookById(long id) {
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
    public ComicBook getComicBookBySeriesName(String seriesTitle) {
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
    public Long saveComicBook(ComicBookDto comicBook) {
        List<ComicBookDto> comics = deserializeStubData(ABSOLUTE_FILE_PATH);
        comics.add(comicBook);
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
                comic.setGenres(ComicBookDto.genreStringToList(comicBook.getGenres()));
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
        return deserializeStubData(ABSOLUTE_FILE_PATH);
    }

    public void loadComicBooks() {

        ComicBookDto comicBook1 = new ComicBookDto(
                "Zot!",
                "Scott McCloud",
                "Scott McCloud",
                new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
                "Description",
                36,
                "Eclipse",
                1984
        );
        comicBookStubData.add(comicBook1);

        ComicBookDto comicBook2 = new ComicBookDto(
                "Sanctuary",
                "Sho Fumimura",
                "Ryoichi Ikegami",
                new String[] {"Polital", "Crime", "Thriller", "Manga"},
                "Description",
                108,
                "Viz",
                1990
        );
        comicBookStubData.add(comicBook2);

        ComicBookDto comicBook3 = new ComicBookDto(
                "Nexus (1981)",
                "Mike Baron",
                "Steve Rude",
                new String[] {"Superhero", "Planetary Romance", "Superpower", "Science Fiction", "Adventure", "Fantasy"},
                "Description",
                3,
                "Capital",
                1981
        );
        comicBookStubData.add(comicBook3);

        ComicBookDto comicBook4 = new ComicBookDto(
                "The Maxx",
                "Sam Keith",
                "Sam Keith",
                new String[] {"Fantasy", "Drama", "Comedy", "Superhero"},
                "Description",
                35,
                "Image",
                1993
        );
        comicBookStubData.add(comicBook4);
        
        ComicBookDto comicBook5 = new ComicBookDto(
                "Winter Wolrd",
                "Chuck Dixon",
                "Jorge Zaffino",
                new String[] {"Adventure", "Post-Apocalyptic", "Gunslinger"},
                "Description",
                3,
                "Eclipse",
                1987
        );
        comicBookStubData.add(comicBook5);
        
        ComicBookDto comicBook6 = new ComicBookDto(
                "Hellhounds Panzer Cops",
                "Mamoru Oshii",
                "Kamui Fujiwara",
                new String[] {"Military", "Police", "Adventure", "Dystopian", "Manga"},
                "Description",
                6,
                "Dark Horse",
                1994
        );
        comicBookStubData.add(comicBook6);
        
        ComicBookDto comicBook7 = new ComicBookDto(
                "Jon Sable Freelance (1983)",
                "Mike Grell",
                "Mike Grell",
                new String[] {"Action", "Adventure", "Crime", "Vigilantes"},
                "Description",
                56,
                "First Comics",
                1983
        );
        comicBookStubData.add(comicBook7);
        
        ComicBookDto comicBook8 = new ComicBookDto(
                "Chronicles of Corum",
                "Mike Baron",
                "Mike Mignola",
                new String[] {"Action", "Adventure", "Sword and Socery"},
                "Description",
                12,
                "First Comics",
                1987
        );
        comicBookStubData.add(comicBook8);
        
        ComicBookDto comicBook9 = new ComicBookDto(
                "Drakuun",
                "Johji Manabe",
                "Johnji Manabe",
                new String[] {"Adventure", "Sword and Socery", "Comedy", "Manga"},
                "Description",
                24,
                "Dark Horse",
                1997
        );
        comicBookStubData.add(comicBook9);
        
        ComicBookDto comicBook10 = new ComicBookDto(
                "Sojourn",
                "Ron Marz",
                "Greg Land",
                new String[] {"Action", "Adventure", "Sword and Sorcery"},
                "Description",
                34,
                "Crossgen",
                2001
        );
        comicBookStubData.add(comicBook10);
        
        ComicBookDto comicBook11 = new ComicBookDto(
                "Trekker (1988)",
                "Ron Randall",
                "Ron Randall",
                new String[] {"Action", "Crime", "Vigilantes", "Dystopian"},
                "Description",
                6,
                "Dark Horse",
                1988
        );
        comicBookStubData.add(comicBook11);
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
