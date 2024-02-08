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
import org.longbox.domainobjects.UserDTO;
import com.google.gson.Gson;

@NoArgsConstructor
@Getter
@Setter
public class UserStubDB {

    private List<UserDTO> userStubData = new ArrayList<>();

    public void loadUsers() {
        UserDTO u1 = new UserDTO(
                "Always_Scheming",
                "John",
                "Smith",
                new Date(1990, 12, 1),
                "email@domain.com",
                "asdifp483qradv",
                "Canada",
                new Date());
        userStubData.add(u1);

        UserDTO u2 = new UserDTO(
                "Always_Throwing",
                "Neo",
                "Anderson",
                new Date(1929,1,1),
                "address@provider.ca",
                "asdf4308asdf=as03",
                "Indonesia",
                new Date());
        userStubData.add(u2);

        UserDTO u3 = new UserDTO(
                "Phoenix",
                "Stan",
                "Lee",
                new Date(2000,4,31),
                "123fake@nowhere.org",
                "xlk;uu500834",
                "United Kingdom",
                new Date());
        userStubData.add(u3);
    }

    public void serializeComicBookStubDB() {
        String json = new Gson().toJson(userStubData);
        String file = "src/main/resources/UserStubDB.json";
        try (PrintStream out = new PrintStream(new FileOutputStream(file))) {
            out.print(json);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserDTO> deserializeUserStubDB(String filepath) {
        Type listType = new TypeToken<ArrayList<UserDTO>>(){}.getType();
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
