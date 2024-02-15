package org.longbox.persistence.stubdatabase;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.longbox.domainobjects.dto.ComicBookDTO;
import com.google.gson.Gson;

@NoArgsConstructor
@Getter
@Setter
public class ComicBookStubDB {

    private List<ComicBookDTO> comicBookStubData = new ArrayList<>();
    private final String ABSOLUTE_FILE_PATH = "src/main/resources/ComicBookStubDB.json";

    public void loadComicBooks() {

        ComicBookDTO comicBook1 = new ComicBookDTO(
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

        ComicBookDTO comicBook2 = new ComicBookDTO(
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

        ComicBookDTO comicBook3 = new ComicBookDTO(
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

        ComicBookDTO comicBook4 = new ComicBookDTO(
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

    }

    public void serializeComicBookStubDB() {
        String json = new Gson().toJson(comicBookStubData);
        String file = "src/main/resources/ComicBookStubDB.json";
        try (PrintStream out = new PrintStream(new FileOutputStream(file))) {
            out.print(json);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ComicBookDTO> deserializeComicBookStubDB(String filepath) {
        Type listType = new TypeToken<List<ComicBookDTO>>(){}.getType();
        JsonReader reader = null;

        try {
            reader = new JsonReader(new FileReader(filepath));
            return new Gson().fromJson(reader, listType);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
