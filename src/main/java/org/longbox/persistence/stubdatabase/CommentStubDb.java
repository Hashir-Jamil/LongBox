package org.longbox.persistence.stubdatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.Setter;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.persistence.dao.CommentDao;
import org.longbox.persistence.entity.Comment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class CommentStubDb implements CommentDao {
    private List<CommentDto> commentsStubData = new ArrayList<>();
    private final String ABSOLUTE_FILE_PATH = "src/main/resources/CommentStubDb.json";

    public CommentStubDb() {
        loadJsonToArrayList();
    }

    @Override
    public Comment getCommentById(long userId, long comicId) {
        return null;
    }

    @Override
    public List<CommentDto> getCommentsByComic(long comicID) {
        List<CommentDto> commentList = new ArrayList<>();
        for(CommentDto c: commentsStubData){
            if(c.getComicBookId() == comicID){
                commentList.add(c);
            }
        }
        return commentList;
    }

    @Override
    public void saveComment(CommentDto commentDTO) {
        commentsStubData.add(commentDTO);
    }

    @Override
    public List<CommentDto> getCommentsByUser(long userID) {
        List<CommentDto> commentList = new ArrayList<>();
        for(CommentDto c: commentsStubData){
            if(c.getUserId() == userID){
                commentList.add(c);
            }
        }
        return commentList;
    }


    public void serializeUserStubDB() {
        String json = new Gson().toJson(commentsStubData);
        String file = "src/main/resources/UserStubDb.json";
        try (PrintStream out = new PrintStream(new FileOutputStream(file))) {
            out.print(json);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CommentDto> deserializeUserStubDB(String filepath) {
        Type listType = new TypeToken<ArrayList<CommentDto>>(){}.getType();
        JsonReader reader = null;

        try {
            reader = new JsonReader(new FileReader(filepath));
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<CommentDto> dummyUsers = new Gson().fromJson(reader, listType);
        return dummyUsers;
    }

    public void loadJsonToArrayList(){
        commentsStubData = deserializeUserStubDB(ABSOLUTE_FILE_PATH);
    }

}
