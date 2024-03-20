package org.longbox.unit.domainobjects.entity;

import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.domainobjects.entity.Comment;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.domainobjects.entity.User;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class CommentTest {

    @Test
    public void testConstructor() {
        // Create a CommentDto object
        CommentDto commentDto = new CommentDto();
        commentDto.setMessage("Great comic!");
        commentDto.setCommentDate(new Date());

        // Create a User entity
        User user = new User();
        user.setId(1L);
        user.setUserName("user1");

        // Create a ComicBook entity
        ComicBook comicBook = new ComicBook();
        comicBook.setId(1L);

        // Test the constructor
        Comment comment = new Comment(commentDto, user, comicBook);

        // Verify that the Comment object is initialized correctly
        assertEquals(commentDto.getMessage(), comment.getMessage());
        assertEquals(commentDto.getCommentDate(), comment.getCommentDate());
        assertEquals(user, comment.getUser());
        assertEquals(user.getUserName(), comment.getUserName());
        assertEquals(comicBook, comment.getComicBook());
        assertEquals(comicBook.getId(), comment.getComicBookId());
        assertEquals(user.getId(), comment.getUserId());
    }

    @Test
    public void testGettersAndSetters() {
        // Create a Comment object
        Comment comment = new Comment();

        // Set values using setters
        comment.setMessage("Nice artwork!");
        comment.setCommentDate(new Date());

        // Verify that getters return the correct values
        assertEquals("Nice artwork!", comment.getMessage());
        assertNotNull(comment.getCommentDate());
    }
}
