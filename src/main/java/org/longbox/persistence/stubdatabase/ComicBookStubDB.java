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
                "Spider Man",
                "Stan Lee",
                "Marvel Comics",
                1994
        );
        comicBookStubData.add(comicBook1);

        ComicBookDTO comicBook2 = new ComicBookDTO(
                "Batman",
                "Frank Miller",
                "DC",
                2005
        );
        comicBookStubData.add(comicBook2);

        ComicBookDTO comicBook3 = new ComicBookDTO(
                "Batman",
                "Mark Twain",
                "DC",
                2005
        );
        comicBookStubData.add(comicBook3);

        ComicBookDTO comicBook4 = new ComicBookDTO(
                "Sin City",
                "Frank Miller",
                "DC",
                2001
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
        Type listType = new TypeToken<ArrayList<ComicBookDTO>>(){}.getType();
        JsonReader reader = null;

        try {
            reader = new JsonReader(new FileReader(filepath));
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<ComicBookDTO> stubComicBooks = new Gson().fromJson(reader, listType);
        return stubComicBooks;
    }

}
