package org.longbox.persistence.stubdatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.persistence.dao.CommentDao;
import org.longbox.domainobjects.entity.Comment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CommentStubDb implements CommentDao, JsonConvertor {
    private List<CommentDto> commentsStubData = new ArrayList<>();
    private final String ABSOLUTE_FILE_PATH = "src/main/resources/CommentStubDb.json";

    @Override
    public Comment getCommentById(long userId, long comicId) {
        return null;
    }

    @Override
    public List<CommentDto> getCommentsByComic(Long comicID) {
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
    public List<CommentDto> getCommentsByUser(Long userID) {
        List<CommentDto> commentList = new ArrayList<>();
        for(CommentDto c: commentsStubData){
            if(c.getUserId() == userID){
                commentList.add(c);
            }
        }
        return commentList;
    }

    public void loadJsonToArrayList(){
        commentsStubData = deserializeStubData(ABSOLUTE_FILE_PATH);
    }

    @Override
    public void serializeStubData() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String json = gson.toJson(commentsStubData);
        try (PrintStream out = new PrintStream(new FileOutputStream(ABSOLUTE_FILE_PATH))) {
            out.print(json);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CommentDto> deserializeStubData(String filepath) {
        Type listType = new TypeToken<ArrayList<CommentDto>>(){}.getType();
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
