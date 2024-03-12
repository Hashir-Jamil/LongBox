package org.longbox.businesslogic.comparators;

import org.longbox.domainobjects.dto.CommentDto;

import java.util.Comparator;

public class CommentDateComparator implements Comparator<CommentDto> {
    @Override
    public int compare(CommentDto comment1, CommentDto comment2) {
        if (comment1.getCommentDate().compareTo(comment2.getCommentDate()) == 1) {
            return 1;
        }
        if (comment1.getCommentDate().compareTo(comment2.getCommentDate()) == -1) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
