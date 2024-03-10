package org.longbox.persistence.stubdatabase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.domainobjects.dto.JsonConvertor;
import org.longbox.persistence.dao.CommentDao;
import org.longbox.persistence.entity.Comment;

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
    public List<CommentDto> getCommentsByComic(long comicID) {
        return null;
    }

    @Override
    public void saveComment(CommentDto commentDTO) {
    }

    @Override
    public List<CommentDto> getCommentsByUser(long userID) {
        return null;
    }

    public void loadComments() {

        CommentDto comment1 = new CommentDto(
                "This comic was really good",
                "Always_Scheming",
                "Spider Man"
        );
        commentsStubData.add(comment1);

        CommentDto comment2 = new CommentDto(
                "This comic was really bad",
                "Always_Throwing",
                "Spider man"
        );
        commentsStubData.add(comment2);

        CommentDto comment3 = new CommentDto(
                "This comic changed my life",
                "Phoenix",
                "Arkham Asylum"
        );
        commentsStubData.add(comment3);
    }

    @Override
    public void serializeStubData() {

    }

    @Override
    public <T> List<T> deserializeStubData(String filepath) {
        return null;
    }
}
