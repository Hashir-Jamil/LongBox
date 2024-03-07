package org.longbox.integration.persistence.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.CommentDTO;
import org.longbox.persistence.dao.CommentDaoImpl;
import org.longbox.persistence.entity.Comment;

class CommentDaoImplTest {
	
	Comment comment1, comment2;
	CommentDTO commentDTO1, commentDTO2;
	CommentDaoImpl commentDaoImpl;
	List<CommentDTO> expectedList;
	
	@BeforeEach
	void init() {
		commentDTO1 = new CommentDTO(1, 1, "This is a good comic", "Always_Scheming");
		commentDTO2 = new CommentDTO(3, 1, "I like this comic", "Pheonix");
		
		comment1 = new Comment(commentDTO1);
		comment2 = new Comment(commentDTO2);
		
		commentDaoImpl = new CommentDaoImpl();
		
		expectedList = new ArrayList<CommentDTO>();
		expectedList.add(commentDTO1);
		expectedList.add(commentDTO2);
	}
	
	// RUN ONLY ONCE
//	@Test
//	void test_add_comment() {
//		commentDaoImpl.saveComment(commentDTO1);
//		commentDaoImpl.saveComment(commentDTO2);
//	}
	
	@Test
	void test_getCommentsByComic() {
		List<CommentDTO> actualList = commentDaoImpl.getCommentsByComic(1);
		for(CommentDTO c: actualList) {
			System.out.println(c.toString());
		}
	}

}
