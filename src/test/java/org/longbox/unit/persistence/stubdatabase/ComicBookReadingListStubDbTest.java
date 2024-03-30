package org.longbox.unit.persistence.stubdatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.persistence.stubdatabase.ComicBookReadingListStubDb;

import static org.junit.jupiter.api.Assertions.assertFalse;
public class ComicBookReadingListStubDbTest {

    ComicBookReadingListStubDb readingListStubDb;

    @BeforeEach
    void setup() {
        readingListStubDb = new ComicBookReadingListStubDb();
    }

    @Test
    void doesRecordExist() {
        assertFalse(readingListStubDb.doesRecordExist(100L,200L));
    }
}
