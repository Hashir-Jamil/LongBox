package org.longbox.unit.domainobjects.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.domainobjects.entity.Comment;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class CommentDtoTest {
    CommentDto commentDto1, commentDto2;
    UserDto u1DTO;
    ComicBookDto comicBook1;

    @BeforeEach
    void init() {

        commentDto1 = new CommentDto(1, 1,"nice comic", "Phoenix");

        u1DTO = new UserDto();
        u1DTO.setUserName("user1");
        u1DTO.setFirstName("John");
        u1DTO.setLastName("Doe");
        u1DTO.setDob(new Date());
        u1DTO.setEmail("email@domain.com");
        u1DTO.setPassword("Always_Scheming");
        u1DTO.setCountry("USA");
        u1DTO.setDefaults();

        comicBook1 = new ComicBookDto(
                "Zot!",
                "Scott McCloud",
                "Scott McCloud",
                new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
                "Description",
                36,
                "Eclipse",
                1984
        );

    }

    @Test
    void test_constructor_1(){
        assertSame("nice comic", commentDto1.getMessage());
        assertSame("Phoenix", commentDto1.getUserName());
        assertNull(commentDto1.getUser());
        assertNull(commentDto1.getComicBook());
    }

    @Test
    public void testConstructorWithMessageUserComicBook() {
        // Create a UserDto object
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUserName("user1");

        // Create a ComicBookDto object
        ComicBookDto comicBookDto = new ComicBookDto();
        comicBookDto.setId(1L);

        // Test the constructor
        CommentDto commentDto = new CommentDto("Great comic!", userDto, comicBookDto);

        // Verify that the CommentDto object is initialized correctly
        assertEquals("Great comic!", commentDto.getMessage());
        assertEquals("user1", commentDto.getUserName());
        assertEquals(1L, commentDto.getUserId());
        assertEquals(1L, commentDto.getComicBookId());
        assertNotNull(commentDto.getCommentDate());
    }

    @Test
    public void testConstructorWithUserIdComicBookIdMessageUserName() {
        // Test the constructor
        CommentDto commentDto = new CommentDto(1L, 1L, "Great comic!", "user1");

        // Verify that the CommentDto object is initialized correctly
        assertEquals(1L, commentDto.getUserId());
        assertEquals(1L, commentDto.getComicBookId());
        assertEquals("Great comic!", commentDto.getMessage());
        assertEquals("user1", commentDto.getUserName());
        assertNotNull(commentDto.getCommentDate());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create two CommentDto objects with identical properties
        CommentDto commentDto1 = new CommentDto(1L, 1L, "Great comic!", "user1");
        CommentDto commentDto2 = new CommentDto(1L, 1L, "Great comic!", "user1");

        // Create another CommentDto object with different properties
        CommentDto commentDto3 = new CommentDto(2L, 1L, "Awesome!", "user2");

        // Test equals() method
        assertTrue(commentDto1.equals(commentDto2)); // commentDto1 should be equal to commentDto2
        assertFalse(commentDto1.equals(commentDto3)); // commentDto1 should not be equal to commentDto3

        // Test hashCode() method
        assertEquals(commentDto1.hashCode(), commentDto2.hashCode()); // commentDto1 and commentDto2 should have the same hash code
    }

    @Test
    public void testToString() {
        // Create a CommentDto object
        CommentDto commentDto = new CommentDto(1L, 1L, "Great comic!", "user1");

        // Test toString() method
        String expectedString = "CommentDTO [id=0, userId=1, comicBookId=1, message=Great comic!, username=user1, user=null, comicBook=null, dateAdded=" + commentDto.getCommentDate() + "]";
        assertEquals(expectedString, commentDto.toString());
    }
}
