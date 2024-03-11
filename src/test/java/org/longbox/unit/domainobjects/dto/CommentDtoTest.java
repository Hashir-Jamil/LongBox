package org.longbox.unit.domainobjects.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.domainobjects.dto.UserDto;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class CommentDtoTest {
    CommentDto commentDto1, commentDto2;
    UserDto u1DTO;
    ComicBookDto comicBook1;

    @BeforeEach
    void init() {

        commentDto1 = new CommentDto(1, 1,"nice comic", "Phoenix");

        u1DTO = new UserDto(
                "Always_Scheming",
                "John",
                "Smith",
                new Date(1990, 12, 1),
                "email@domain.com",
                "Always_Scheming",
                "Canada"
        );

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

        commentDto2 = new CommentDto("A new comic and good one to read", u1DTO, comicBook1);

    }

    @Test
    void test_constructor_1(){
        assertEquals(0, commentDto1.getId());
        assertEquals(1, commentDto1.getUserId());
        assertEquals(1, commentDto1.getComicBookId());
        assertSame("nice comic", commentDto1.getMessage());
        assertSame("Phoenix", commentDto1.getUserName());
        assertNull(commentDto1.getUser());
        assertNull(commentDto1.getComicBook());
    }

    @Test
    void test_constructor_2(){
        assertEquals(0, commentDto2.getId());
        assertEquals(0, commentDto2.getUserId());
        assertEquals(0, commentDto2.getComicBookId());
        assertSame("A new comic and good one to read", commentDto2.getMessage());
        assertSame(u1DTO, commentDto2.getUser());
        assertSame(comicBook1, commentDto2.getComicBook());
    }

}
