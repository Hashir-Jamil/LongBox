package org.longbox.unit.businesslogic.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.service.CommentService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.persistence.dao.CommentDaoImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentServiceTest {

    CommentService commentService;


    @BeforeEach
    void init() {
        commentService = new CommentService(new CommentDaoImpl(HibernateUtils.getSessionFactory()));
    }

    @Test
    void test_getCommentsByComic_1() {
        List<CommentDto> actualList = commentService.getCommentsByComic(1L);
        int actual = actualList.size();
        assertEquals(2, actual);
    }

    @Test
    void test_getCommentsByComic_2() {
        List<CommentDto> actualList = commentService.getCommentsByComic(14L);
        int actual = actualList.size();
        assertEquals(3, actual);
    }

    @Test
    void test_getCommentsByComic_3() {
        List<CommentDto> actualList = commentService.getCommentsByComic(23L);
        int actual = actualList.size();
        assertEquals(0, actual);
    }

    @Test
    void test_saveComment(){
        CommentDto addComment = new CommentDto();
        addComment.setUserId(3);
        addComment.setComicBookId(13);
        addComment.setMessage("This is a nice comic");
        addComment.setUserName("Phoenix");
        commentService.saveComment(addComment);
    }

    @Test
    void test_getCommentByUser_1(){
        List<CommentDto> actualList = commentService.getCommentsByUser(1L);
        int actual = actualList.size();
        assertEquals(10, actual);
    }

    @Test
    void test_getCommentByUser_2(){
        List<CommentDto> actualList = commentService.getCommentsByUser(2L);
        int actual = actualList.size();
        assertEquals(9, actual);
    }

    @Test
    void test_getCommentByUser_3(){
        List<CommentDto> actualList = commentService.getCommentsByUser(5L);
        int actual = actualList.size();
        assertEquals(4, actual);
    }

}
