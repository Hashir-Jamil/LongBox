package org.longbox.unit.persistence.stubdatabase;

import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.persistence.stubdatabase.CommentStubDb;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommentStubDbTest {
    CommentStubDb commentStubDb;
    List<CommentDto> commentList;
    CommentDto comment1, comment2;

    @BeforeEach
    void setup(){
        commentStubDb = new CommentStubDb();
        commentStubDb.loadJsonToArrayList();
        commentList = commentStubDb.deserializeStubData(commentStubDb.getABSOLUTE_FILE_PATH());

        comment1 = new CommentDto(1, 1, "Wow, the art in this comic is absolutely breathtaking! The attention to detail and vibrant colors bring the characters to life in a way thats truly mesmerizing.", "Always_Scheming");
        comment2 = new CommentDto(3,8,"nice comic", "Phoenix");
    }

    @Test
    void test_serialize(){
        commentStubDb.deserializeStubData(commentStubDb.getABSOLUTE_FILE_PATH());
        commentStubDb.serializeStubData();
    }

    @Test
    void test_deserialize(){
        assertNotNull(commentList);
        assertEquals(35, commentList.size());
        assertEquals(comment1.getUserId(), commentList.get(0).getUserId());
        assertEquals(comment1.getComicBookId(), commentList.get(0).getComicBookId());
        assertEquals(comment1.getMessage(), commentList.get(0).getMessage());
        assertEquals(comment1.getUserName(), commentList.get(0).getUserName());
        assertNotEquals(comment1, commentList.get(1));
    }

    @Test
    void test_getCommentByComic_1(){
        List<CommentDto> actualList = commentStubDb.getCommentsByComic(1L);
        int actual = actualList.size();
        assertEquals(3, actual);
    }

    @Test
    void test_getCommentByComic_2(){
        List<CommentDto> actualList = commentStubDb.getCommentsByComic(14L);
        int actual = actualList.size();
        assertEquals(1, actual);
    }

    @Test
    void test_getCommentsByUser_1(){
        List<CommentDto> actualList = commentStubDb.getCommentsByUser(1L);
        int actual = actualList.size();
        assertEquals(9, actual);
    }

    @Test
    void test_getCommentsByUser_2(){
        List<CommentDto> actualList = commentStubDb.getCommentsByUser(5L);
        int actual = actualList.size();
        assertEquals(2, actual);
    }

    @Test
    void test_saveComment(){
        commentStubDb.saveComment(comment2);
        commentList = commentStubDb.getCommentsStubData();
        assertEquals(36, commentList.size());
    }

}
