package org.longbox.domainobjects;


import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ComicBookDTO {

    private String issueTitle;

    private String seriesTitle;

    private String author;

    private int volumeNumber;

    private int issueNumber;

    private String publisher;

    private int year;

    private Date dateAdded;

    public ComicBookDTO(
      String issueTitle,
      String seriesTitle,
      String author,
      int volumeNumber,
      int issueNumber,
      String publisher,
      int year
    ) {
        this.issueTitle = issueTitle;
        this.seriesTitle = seriesTitle;
        this.author = author;
        this.volumeNumber = volumeNumber;
        this.issueNumber = issueNumber;
        this.year = year;
        this.dateAdded = new Date();
    };

}


