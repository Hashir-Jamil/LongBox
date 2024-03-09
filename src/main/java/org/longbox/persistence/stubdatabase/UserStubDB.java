package org.longbox.persistence.stubdatabase;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.exception.EmailDoesNotExistException;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.exception.UserNameDoesNotExistException;
import org.longbox.businesslogic.exception.UsernameOrEmailExistsException;
import org.longbox.domainobjects.dto.UserDto;
import com.google.gson.Gson;
import org.longbox.persistence.dao.UserDao;
import org.longbox.persistence.entity.User;

@Getter
@Setter
public class UserStubDB implements UserDao {

    private List<UserDto> userStubData = new ArrayList<>();
    private final String ABSOLUTE_FILE_PATH = "src/main/resources/UserStubDB.json";

    public UserStubDB() {
        loadUsers();
    }

    @Override
    public User getUserById(long id) throws UserIDDoesNotExistException {
        return null;
    }

    @Override
    public User getUserByUserName(String userName) throws UserNameDoesNotExistException {
        return null;
    }

    @Override
    public User getUserByEmail(String email) throws EmailDoesNotExistException {
        return null;
    }

    @Override
    public void saveUser(User user) throws UsernameOrEmailExistsException {

    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public boolean modifyUser(User user) {
        return false;
    }

    public void loadUsers() {
        UserDto u1 = new UserDto(
                1,
                "Always_Scheming",
                "John",
                "Smith",
                new Date(1990, 12, 1),
                "email@domain.com",
                "Always_Scheming",
                "Canada",
                0,
                0
        );
        userStubData.add(u1);

        UserDto u2 = new UserDto(
                2,
                "Always_Throwing",
                "Neo",
                "Anderson",
                new Date(1929,1,1),
                "address@provider.ca",
                "Always_Throwing",
                "Indonesia",
                0,
                0
        );
        userStubData.add(u2);

        UserDto u3 = new UserDto(
                3,
                "Phoenix",
                "Stan",
                "Lee",
                new Date(2000,4,31),
                "123fake@nowhere.org",
                "Phoenix",
                "United Kingdom",
                0,
                0
        );
        userStubData.add(u3);
    }

    public void serializeUserStubDB() {
        String json = new Gson().toJson(userStubData);
        String file = "src/main/resources/UserStubDB.json";
        try (PrintStream out = new PrintStream(new FileOutputStream(file))) {
            out.print(json);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserDto> deserializeUserStubDB(String filepath) {
        Type listType = new TypeToken<ArrayList<UserDto>>(){}.getType();
        JsonReader reader = null;

        try {
            reader = new JsonReader(new FileReader(filepath));
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<UserDto> dummyUsers = new Gson().fromJson(reader, listType);
        return dummyUsers;
    }
}
