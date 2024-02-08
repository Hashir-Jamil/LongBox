package org.longbox.persistence;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.longbox.domainobjects.ComicBookDTO;
import com.google.gson.Gson;
import org.longbox.domainobjects.UserDTO;

@NoArgsConstructor
@Getter
@Setter
public class ComicBookStubDB {

    private List<ComicBookDTO> comicBookStubData = new ArrayList<>();

    public void loadComicBooks() {

        ComicBookDTO comicBook1 = new ComicBookDTO(
                "Spider Wars",
                "Spider Man",
                "Stan Lee",
                43,
                233,
                "Marvel Comics",
                1994,
                new Date()
        );
        comicBookStubData.add(comicBook1);

        ComicBookDTO comicBook2 = new ComicBookDTO(
                "Arkhamn Asylym",
                "Batman",
                "Frank Miller",
                1,
                1,
                "DC",
                2005,
                new Date()
        );
        comicBookStubData.add(comicBook2);

        ComicBookDTO comicBook3 = new ComicBookDTO(
                "Superman vs. Batman",
                "Batman",
                "Mark Twain",
                2,
                1,
                "DC",
                2005,
                new Date()
        );
        comicBookStubData.add(comicBook3);

        ComicBookDTO comicBook4 = new ComicBookDTO(
                "Sin City",
                "Sin City",
                "Frank Miller",
                1,
                1,
                "DC",
                2001,
                new Date()
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

    public List<UserDTO> deserializeUserStubDB(String filepath) {
        Type listType = new TypeToken<ArrayList<ComicBookDTO>>(){}.getType();
        JsonReader reader = null;

        try {
            reader = new JsonReader(new FileReader(filepath));
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<UserDTO> dummyUsers = new Gson().fromJson(reader, listType);
        return dummyUsers;
    }

}
