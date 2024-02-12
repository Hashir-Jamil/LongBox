package org.longbox.businesslogic.comparators;

import org.longbox.domainobjects.dto.ComicBookDTO;

import java.util.Comparator;

public class ComicBookNameComparator implements Comparator<ComicBookDTO> {

    @Override
    public int compare(ComicBookDTO comicBook1, ComicBookDTO comicBook2) {

        if (comicBook1.getSeriesTitle().charAt(0) < comicBook2.getSeriesTitle().charAt(0)) {
            return -1;
        }
        if (comicBook1.getSeriesTitle().charAt(0) > comicBook2.getSeriesTitle().charAt(0)) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
