package org.longbox.persistence.stubdatabase;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.longbox.businesslogic.exception.EmailDoesNotExistException;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.exception.UserNameDoesNotExistException;
import org.longbox.businesslogic.exception.UsernameOrEmailExistsException;
import org.longbox.domainobjects.dto.UserDto;
import com.google.gson.Gson;
import org.longbox.domainobjects.mapper.UserMapper;
import org.longbox.persistence.dao.UserDao;
import org.longbox.domainobjects.entity.User;

@Getter
@Setter
@NoArgsConstructor
public class UserStubDb implements UserDao, JsonConvertor {
    private List<UserDto> userStubData;
    private final String ABSOLUTE_FILE_PATH = "src/main/resources/UserStubDb.json";

    @Override
    public User getUserById(long id) throws UserIDDoesNotExistException {
        userStubData = deserializeStubData(ABSOLUTE_FILE_PATH);
        for (UserDto user : userStubData) {
            if (user.getId() == id) {
                User u = UserMapper.toEntity(user);
                u.setId(user.getId());
                return u;
            }
        }
        throw new UserIDDoesNotExistException();
    }

    @Override
    public User getUserByUserName(String userName) throws UserNameDoesNotExistException {
        userStubData = deserializeStubData(ABSOLUTE_FILE_PATH);
        for (UserDto user : userStubData) {
            if (Objects.equals(user.getUserName(), userName)) {
                User u = UserMapper.toEntity(user);
                u.setId(user.getId());
                return u;
            }
        }
        throw new UserNameDoesNotExistException();
    }

    @Override
    public User getUserByEmail(String email) throws EmailDoesNotExistException {
        userStubData = deserializeStubData(ABSOLUTE_FILE_PATH);
        for (UserDto user : userStubData) {
            if (Objects.equals(user.getEmail(), email)) {
                User u = UserMapper.toEntity(user);
                u.setId(user.getId());
                return u;
            }
        }
        throw new EmailDoesNotExistException();
    }

    @Override
    public void saveUser(User user) throws UsernameOrEmailExistsException {
        userStubData = deserializeStubData(ABSOLUTE_FILE_PATH);
        for (UserDto u : userStubData) {
            if (u.getEmail().equals(user.getEmail()) ||
                u.getUserName().equals(user.getUserName())) {
                throw new UsernameOrEmailExistsException();
            }
        }
        userStubData.add(UserMapper.toDto(user));
        serializeStubData();
    }

    @Override
    public void serializeStubData() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String json = gson.toJson(userStubData);
        try (PrintStream out = new PrintStream(new FileOutputStream(ABSOLUTE_FILE_PATH))) {
            out.print(json);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<UserDto> deserializeStubData(String filepath) {
        Type listType = new TypeToken<ArrayList<UserDto>>(){}.getType();
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

	@Override
	public List<UserDto> getAllUsers() {
		return userStubData;
	}

	@Override
	public List<UserDto> getUsersMoreThan(String fieldName, int value) {
		return null;
	}

	@Override
	public List<UserDto> getUsersLessThan(String fieldName, int value) {
		return null;
	}
}
