package org.longbox.persistence;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comic_book")
public class ComicBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "issue_title")
    private String issueTitle;

    @Column(name = "series_title")
    private String seriesTitle;

    @Column(name = "author")
    private String author;

    @Column(name = "volume_number")
    private int volumeNumber;

    @Column(name = "issue_number")
    private int issueNumber;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "year")
    private int year;

    @Column(name = "date_added")
    private Date dateAdded;

}
