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
        
        ComicBookDTO comicBook5 = new ComicBookDTO(
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
        
        ComicBookDTO comicBook6 = new ComicBookDTO(
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
        
        ComicBookDTO comicBook7 = new ComicBookDTO(
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
        
        ComicBookDTO comicBook8 = new ComicBookDTO(
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
        
        ComicBookDTO comicBook9 = new ComicBookDTO(
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
        
        ComicBookDTO comicBook10 = new ComicBookDTO(
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
        
        ComicBookDTO comicBook11 = new ComicBookDTO(
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
