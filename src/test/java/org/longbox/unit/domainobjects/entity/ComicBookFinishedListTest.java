package org.longbox.unit.domainobjects.entity;

import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.domainobjects.entity.ComicBookFinishedList;
import org.longbox.domainobjects.entity.ComicBookListId;
import org.longbox.domainobjects.entity.User;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
public class ComicBookFinishedListTest {

    @Test
    void testEqualsHash() {
        ComicBookFinishedList cbf = new ComicBookFinishedList();
        cbf.setUser(new User());
        cbf.setComicBook(new ComicBook());
        cbf.setId(new ComicBookListId(1L,1L));
        cbf.setDateAdded(new Date());
        ComicBookFinishedList cbf2 = new ComicBookFinishedList();
        cbf2.setUser(new User());
        cbf2.setComicBook(new ComicBook());
        cbf2.setId(new ComicBookListId(1L,1L));
        assertEquals(cbf,cbf2);
        assertEquals(cbf.hashCode(),cbf2.hashCode());
    }

    @Test
    void testEquals2() {
        ComicBookFinishedList cbf = new ComicBookFinishedList();
        cbf.setUser(new User());
        cbf.getUser().setId(1L);
        cbf.setComicBook(new ComicBook());
        cbf.getComicBook().setId(1L);
        cbf.setId(new ComicBookListId(1L,1L));
        assertEquals(1L,cbf.getUser().getId());
        assertEquals(1L,cbf.getComicBook().getId());
    }

    @Test
    void testToString() {
        ComicBookFinishedList c = new ComicBookFinishedList();
        c.setUser(new User());
        c.setComicBook(new ComicBook());
        c.setId(new ComicBookListId(1L,1L));
        c.setDateAdded(new Date());
        String expected = "ComicBookFinishedList{id=ComicBookListId(userId=1, comicBookId=1)}";
        assertEquals(expected,c.toString());
    }
}
