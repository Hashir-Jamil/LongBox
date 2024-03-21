package org.longbox.domainobjects.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.longbox.businesslogic.utils.GenreUtils;
import org.longbox.domainobjects.dto.ComicBookDto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Date;

@Entity
@Table(name = "comic_book")
@Getter
@Setter
@NoArgsConstructor
public class ComicBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series_title")
    private String seriesTitle;

    @Column(name = "author")
    private String author;
    
    @Column(name = "artist")
    private String artist;

    @Column(name = "genres", columnDefinition = "text[]")
    private String genres;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "number_of_issues")
    private int numberOfIssues;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "year_published")
    private int yearPublished;

    @Column(name = "date_added")
    private Date dateAdded;
    
    @Column(name = "north_america_favorites_count")
    private int northAmericaFavoritesCount;
    
    @Column(name = "south_america_favorites_count")
    private int southAmericaFavoritesCount;
    
    @Column(name = "europe_favorites_count")
    private int europeFavoritesCount;
    
    @Column(name = "asia_favorites_count")
    private int asiaFavoritesCount;
    
    @Column(name = "africa_favorites_count")
    private int africaFavoritesCount;
    
    @Column(name = "oceania_favorites_count")
    private int oceaniaFavoritesCount;
    
    @Column(name = "antarctica_favorites_count")
    private int antarcticaFavoritesCount;
    
    @Column(name = "favorites_count")
    private int favoritesCount;
    
    @OneToMany(mappedBy = "comicBook", cascade = CascadeType.ALL)
    private Set<StarRating> starRatings = new HashSet<>();

    @OneToMany(mappedBy = "comicBook", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "comicBook", cascade = CascadeType.ALL)
    private Set<ComicBookFavoritesList> favoritedByList = new HashSet<>();

    @OneToMany(mappedBy = "comicBook", cascade = CascadeType.ALL)
    private Set<ComicBookFinishedList> finishedByList = new HashSet<>();

    @OneToMany(mappedBy = "comicBook", cascade = CascadeType.ALL)
    private Set<ComicBookReadingList> readingInProgressBy = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicBook comicBook = (ComicBook) o;
        return getYearPublished() == comicBook.getYearPublished() && Objects.equals(getId(), comicBook.getId()) && Objects.equals(getSeriesTitle(), comicBook.getSeriesTitle()) && Objects.equals(getAuthor(), comicBook.getAuthor()) && Objects.equals(getArtist(), comicBook.getArtist()) && Objects.equals(getPublisher(), comicBook.getPublisher());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSeriesTitle(), getAuthor(), getArtist(), getPublisher(), getYearPublished());
    }

    @Override
    public String toString() {
        return "ComicBook{" +
                "id=" + id +
                ", seriesTitle='" + seriesTitle + '\'' +
                ", author='" + author + '\'' +
                ", artist='" + artist + '\'' +
                ", genres='" + genres + '\'' +
                ", description='" + description + '\'' +
                ", numberOfIssues=" + numberOfIssues +
                ", publisher='" + publisher + '\'' +
                ", yearPublished=" + yearPublished +
                ", dateAdded=" + dateAdded +
                ", northAmericaFavoritesCount=" + northAmericaFavoritesCount +
                ", southAmericaFavoritesCount=" + southAmericaFavoritesCount +
                ", europeFavoritesCount=" + europeFavoritesCount +
                ", asiaFavoritesCount=" + asiaFavoritesCount +
                ", africaFavoritesCount=" + africaFavoritesCount +
                ", oceaniaFavoritesCount=" + oceaniaFavoritesCount +
                ", antarcticaFavoritesCount=" + antarcticaFavoritesCount +
                ", favoritesCount=" + favoritesCount +
                '}';
    }
}
