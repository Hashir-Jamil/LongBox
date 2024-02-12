package org.longbox.businesslogic.comparators;

import org.longbox.domainobjects.dto.ComicBookDTO;

import java.util.Comparator;

public class ComicBookYearPublishedComparator implements Comparator<ComicBookDTO> {
    @Override
    public int compare(ComicBookDTO comicBook1, ComicBookDTO comicBook2) {

        if (comicBook1.getYear() > comicBook2.getYear()) {
            return 1;
        }
        if (comicBook1.getYear() < comicBook2.getYear()) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
