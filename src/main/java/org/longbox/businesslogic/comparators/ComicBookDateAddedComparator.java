package org.longbox.businesslogic.comparators;

import org.longbox.domainobjects.dto.ComicBookDto;

import java.util.Comparator;

public class ComicBookDateAddedComparator implements Comparator<ComicBookDto> {
    @Override
    public int compare(ComicBookDto comicBook1, ComicBookDto comicBook2) {

        if (comicBook1.getDateAdded().compareTo(comicBook2.getDateAdded()) == 1) {
            return 1;
        }
        if (comicBook1.getDateAdded().compareTo(comicBook2.getDateAdded()) == 0) {
            return 0;
        }
        else {
            return -1;
        }

    }
}
