package org.longbox.domainobjects;


import lombok.*;
import org.longbox.businesslogic.comparators.CommentDateComparator;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ComicBookDTO {

    private String seriesTitle;
    private String author;
    private String publisher;
    private int year;
    private Date dateAdded;
    private List<CommentDTO> commentsList = new ArrayList<>();

    public ComicBookDTO(
      String seriesTitle,
      String author,
      String publisher,
      int year
    ) {
        this.seriesTitle = seriesTitle;
        this.author = author;
        this.year = year;
        this.dateAdded = new Date();
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComicBookDTO that)) return false;
        return getYear() == that.getYear() && Objects.equals(getSeriesTitle(), that.getSeriesTitle()) && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getPublisher(), that.getPublisher()) && Objects.equals(getDateAdded(), that.getDateAdded());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeriesTitle(), getAuthor(), getPublisher(), getYear(), getDateAdded());
    }

    public void sortCommentsByDateAscending() {
        Collections.sort(this.commentsList, new CommentDateComparator());
    }

    public void sortCommentsByDateDescending() {
        sortCommentsByDateAscending();
        Collections.reverse(this.commentsList);
    }
}


