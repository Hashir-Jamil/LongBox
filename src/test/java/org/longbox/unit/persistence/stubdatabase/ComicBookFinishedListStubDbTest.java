package org.longbox.unit.persistence.stubdatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.persistence.stubdatabase.ComicBookFinishedListStubDb;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComicBookFinishedListStubDbTest {

    ComicBookFinishedListStubDb finishedListStubDb;

    @BeforeEach
    void setup() {
        finishedListStubDb = new ComicBookFinishedListStubDb();
    }

    @Test
    void doesRecordExist() {
        assertFalse(finishedListStubDb.doesRecordExist(100L,200L));
    }
}
