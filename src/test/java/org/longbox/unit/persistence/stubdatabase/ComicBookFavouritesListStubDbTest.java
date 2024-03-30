package org.longbox.unit.persistence.stubdatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.persistence.stubdatabase.ComicBookFavouritesListStubDb;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ComicBookFavouritesListStubDbTest {

    ComicBookFavouritesListStubDb favouriteListStubDb;

    @BeforeEach
    void setup() {
        favouriteListStubDb = new ComicBookFavouritesListStubDb();
    }

    @Test
    void doesRecordExist() {
        assertFalse(favouriteListStubDb.doesRecordExist(100L,200L));
    }
}
