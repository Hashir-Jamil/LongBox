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
	
	Comment comment1, comment2;
	CommentDto commentDto1, commentDto2;
	CommentDaoImpl commentDaoImpl;
	List<CommentDto> expectedList;
	
	@BeforeEach
	void init() {
		commentDto1 = new CommentDto(1, 1, "This is a good comic", "Always_Scheming");
		commentDto2 = new CommentDto(3, 1, "I like this comic", "Pheonix");
		
		comment1 = new Comment(commentDto1);
		comment2 = new Comment(commentDto2);
		
		commentDaoImpl = new CommentDaoImpl();
		
		expectedList = new ArrayList<CommentDto>();
		expectedList.add(commentDto1);
		expectedList.add(commentDto2);
	}
	
	// RUN ONLY ONCE
//	@Test
//	void test_add_comment() {
//		commentDaoImpl.saveComment(commentDTO1);
//		commentDaoImpl.saveComment(commentDTO2);
//	}
	
	@Test
	void test_getCommentsByComic() {
		List<CommentDto> actualList = commentDaoImpl.getCommentsByComic(1);
		for(CommentDto c: actualList) {
			System.out.println(c.toString());
		}
	}

}
