package org.longbox.comics;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComicBookDTO {

    private String issueTitle;

    private String seriesTitle;

    private String author;

    private int volumeNumber;

    private int issueNumber;

    private String publisher;

    private int year;

    private Date dateAdded;

}
