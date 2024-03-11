package org.longbox.unit.persistence.stubdatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.persistence.stubdatabase.ComicBookStubDb;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComicBookStubDbTest {

    ComicBookStubDb comicBookStubDb;

    @BeforeEach
    void setup() {
        comicBookStubDb = new ComicBookStubDb();
    }

}
