package org.longbox.businesslogic.comparators;

import org.longbox.domainobjects.dto.ComicBookDTO;

import java.util.Comparator;

public class ComicBookDateAddeddComparator implements Comparator<ComicBookDTO> {
    @Override
    public int compare(ComicBookDTO comicBook1, ComicBookDTO comicBook2) {

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
