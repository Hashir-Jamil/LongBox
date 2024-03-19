package org.longbox.unit.domainobjects.mapper;

import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.domainobjects.entity.Comment;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.domainobjects.entity.User;
import org.longbox.domainobjects.mapper.CommentMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommentMapperTest {

    @Test
    public void testToDto() {
        // Create a Comment entity
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setMessage("Great comic!");
        comment.setUserName("user1");
        comment.setUser(new User());
        comment.getUser().setId(1L);
        comment.setComicBook(new ComicBook());
        comment.getComicBook().setId(1L);
        comment.setCommentDate(new Date());

        // Map the Comment entity to a CommentDto
        CommentDto commentDto = CommentMapper.toDto(comment);

        // Verify that the mapping is correct
        assertNotNull(commentDto);
        assertEquals(1L, commentDto.getId());
        assertEquals("Great comic!", commentDto.getMessage());
        assertEquals("user1", commentDto.getUserName());
        assertEquals(1L, commentDto.getUserId());
        assertEquals(1L, commentDto.getComicBookId());
        assertNotNull(commentDto.getCommentDate());
    }

    @Test
    public void testToDtoList() {
        // Create a list of Comment entities
        List<Comment> comments = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Comment comment = new Comment();
            comment.setId((long) i);
            comment.setMessage("Message " + i);
            comment.setUserName("User" + i);
            comment.setUser(new User());
            comment.getUser().setId((long) i);
            comment.setComicBook(new ComicBook());
            comment.getComicBook().setId((long) i);
            comment.setCommentDate(new Date());
            comments.add(comment);
        }

        // Map the list of Comment entities to a list of CommentDto objects
        List<CommentDto> commentDtoList = CommentMapper.toDtoList(comments);

        // Verify that the mapping is correct
        assertNotNull(commentDtoList);
        assertEquals(3, commentDtoList.size());
        for (int i = 0; i < 3; i++) {
            assertEquals((long) (i + 1), commentDtoList.get(i).getId());
            assertEquals("Message " + (i + 1), commentDtoList.get(i).getMessage());
            assertEquals("User" + (i + 1), commentDtoList.get(i).getUserName());
            assertEquals((long) (i + 1), commentDtoList.get(i).getUserId());
            assertEquals((long) (i + 1), commentDtoList.get(i).getComicBookId());
            assertNotNull(commentDtoList.get(i).getCommentDate());
        }
    }

    @Test
    public void testToEntity() {
        // Create a CommentDto object
        CommentDto commentDto = new CommentDto();
        commentDto.setId(1L);
        commentDto.setMessage("Great comic!");
        commentDto.setUserName("user1");
        commentDto.setUserId(1L);
        commentDto.setComicBookId(1L);
        commentDto.setCommentDate(new Date());

        // Map the CommentDto object to a Comment entity
        Comment comment = CommentMapper.toEntity(commentDto);

        // Verify that the mapping is correct
        assertNotNull(comment);
        assertEquals(1L, comment.getId());
        assertEquals("Great comic!", comment.getMessage());
        assertEquals("user1", comment.getUserName());
        assertNotNull(comment.getCommentDate());
    }
}

