package org.longbox.businesslogic.comparators;

import org.longbox.domainobjects.dto.CommentDTO;

import java.util.Comparator;

public class CommentDateComparator implements Comparator<CommentDTO> {
    @Override
    public int compare(CommentDTO comment1, CommentDTO comment2) {
        if (comment1.getDateAdded().compareTo(comment2.getDateAdded()) == 1) {
            return 1;
        }
        if (comment1.getDateAdded().compareTo(comment2.getDateAdded()) == -1) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
