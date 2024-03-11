package org.longbox.integration.persistence.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.persistence.dao.CommentDaoImpl;
import org.longbox.persistence.entity.Comment;

class CommentDaoImplTest {

	CommentDaoImpl commentDaoImpl;

	
	@BeforeEach
	void init() {
		commentDaoImpl = new CommentDaoImpl();
	}
	
	@Test
	void test_getCommentsByComic_1() {
		List<CommentDto> actualList = commentDaoImpl.getCommentsByComic(1);
		int actual = actualList.size();
		assertEquals(4, actual);
	}

	@Test
	void test_getCommentsByComic_2() {
		List<CommentDto> actualList = commentDaoImpl.getCommentsByComic(14);
		int actual = actualList.size();
		assertEquals(1, actual);
	}

	@Test
	void test_getCommentsByComic_3() {
		List<CommentDto> actualList = commentDaoImpl.getCommentsByComic(23);
		int actual = actualList.size();
		assertEquals(0, actual);
	}

	@Test
	void test_saveComment(){
		CommentDto addComment = new CommentDto(3, 13, "This is a nice comic", "Phoenix");
		commentDaoImpl.saveComment(addComment);
	}

	@Test
	void test_getCommentByUser_1(){
		List<CommentDto> actualList = commentDaoImpl.getCommentsByUser(1);
		int actual = actualList.size();
		assertEquals(9, actual);
	}

	@Test
	void test_getCommentByUser_2(){
		List<CommentDto> actualList = commentDaoImpl.getCommentsByUser(2);
		int actual = actualList.size();
		assertEquals(8, actual);
	}

	@Test
	void test_getCommentByUser_3(){
		List<CommentDto> actualList = commentDaoImpl.getCommentsByUser(5);
		int actual = actualList.size();
		assertEquals(2, actual);
	}

}
