package org.longbox.businesslogic.utils;

import org.longbox.businesslogic.UserSession;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.presentation.comicbook.ComicBookFrame;
import org.longbox.presentation.comicbook.ComicBookSearchResultsFrame;

import java.util.ArrayList;
import java.util.List;

public class ComicBookSearchUtils {

    public static ComicBookDto searchComicBook(List<ComicBookDto> comicBookList, String searchQuery) {

        ComicBookDto comicBook = new ComicBookDto();

        for (ComicBookDto comic : comicBookList) {
            if (comic.getSeriesTitle().toUpperCase().contains(searchQuery.toUpperCase())) {
                System.out.println(comic);
                return comic;
            }
        }
        return comicBook;
    }
    
    public static List<ComicBookDto> searchComicBookByPublisher(List<ComicBookDto> comicBookList, String publisher) {
    	
    	List<ComicBookDto> resultList = new ArrayList<ComicBookDto>();

    	for (ComicBookDto comic : comicBookList) {
            if (comic.getPublisher().toUpperCase().contains(publisher.toUpperCase())) {
            	resultList.add(comic);
            }
        }
    	return resultList;
    }
    
    public static List<ComicBookDto> searchComicBookByTitle(List<ComicBookDto> comicBookList, String title) {
    	
    	List<ComicBookDto> resultList = new ArrayList<ComicBookDto>();

    	for (ComicBookDto comic : comicBookList) {
            if (comic.getSeriesTitle().toUpperCase().contains(title.toUpperCase())) {
            	resultList.add(comic);
            }
        }
    	return resultList;
    }
    
    public static List<ComicBookDto> searchComicBookByAuthor(List<ComicBookDto> comicBookList, String author) {
    	
    	List<ComicBookDto> resultList = new ArrayList<ComicBookDto>();

    	for (ComicBookDto comic : comicBookList) {
            if (comic.getAuthor().toUpperCase().contains(author.toUpperCase())) {
            	resultList.add(comic);
            }
        }
    	return resultList;
    }
    
    public static List<ComicBookDto> searchComicBookByArtist(List<ComicBookDto> comicBookList, String artist) {
    	
    	List<ComicBookDto> resultList = new ArrayList<ComicBookDto>();

    	for (ComicBookDto comic : comicBookList) {
            if (comic.getArtist().toUpperCase().contains(artist.toUpperCase())) {
            	resultList.add(comic);
            }
        }
    	return resultList;
    }
    public static List<ComicBookDto> searchComicBookByYear(List<ComicBookDto> comicBookList, String year) {
    	
    	List<ComicBookDto> resultList = new ArrayList<ComicBookDto>();

    	for (ComicBookDto comic : comicBookList) {
            if (String.valueOf(comic.getYearPublished()).equals(year)) {
            	resultList.add(comic);
            }
        }
    	return resultList;
    }
    
    public static List<ComicBookDto> searchComicBookByGenre(List<ComicBookDto> comicBookList, String genre) {
    	
    	List<ComicBookDto> resultList = new ArrayList<ComicBookDto>();
    	String comicGenre;
   	
    	for (ComicBookDto comic : comicBookList) {
    		for(int j = 0; j < comic.getGenres().length; j++) {
    			comicGenre = comic.getGenres()[j].toUpperCase().replaceAll("[^a-zA-Z ]+", "");
    			if ((comicGenre.toUpperCase()).contains(genre.toUpperCase())) {
            		resultList.add(comic);
            		break;
            	}
    		}
        }
    	return resultList;
    }
    
    public static void loadComicBookPage(ComicBookDto comicBook, UserSession user) {
		ComicBookFrame comicBookFrame = new ComicBookFrame(comicBook, user);
		comicBookFrame.setVisible(true);
	}
    
    public static List<ComicBookDto> comicAdvancedSearch(String searchBy, String target, List<ComicBookDto> searchResults, List<ComicBookDto> searchList, UserSession user) {
    	switch (searchBy) {
			case "Title":
				searchResults = searchComicBookByTitle(searchList, target);
				break;
			case "Author":
				searchResults = searchComicBookByAuthor(searchList, target);
				break;
			case "Artist":
				searchResults = searchComicBookByArtist(searchList, target);
				break;
			case "Genre":
				searchResults = searchComicBookByGenre(searchList, target);
				break;
			case "Publisher":
				searchResults = searchComicBookByPublisher(searchList, target);
				break;
			case "Year":
				searchResults = searchComicBookByYear(searchList, target);
				break;
			default:
				searchResults = searchComicBookByPublisher(searchList, "");
				break;
    	}
    	loadComicBookResultsPage(searchResults, target, searchBy, user);
    	return searchResults;
    }
    
    private static void loadComicBookResultsPage(List<ComicBookDto> displayResults, String target, String searchBy, UserSession user) {
		ComicBookSearchResultsFrame resultsPage = new ComicBookSearchResultsFrame(displayResults, target, searchBy, user);
		resultsPage.setVisible(true);
	}

    public static String generateComicBookHTML(ComicBookDto comicBook) {
        String htmlContent = String.format(
                "<html><body><div align='center'><h1>Series Title: %s</h1><br" +
                        "<h2>Author Name: %s</h2><br" +
                        "<h2>Artist Name: %s</h2><br" +
                        "<h2>Genres: %s</h2><br" +
                        "<h2>Description: %s</h2><br" +
                        "<h2>Number of Issues %d</h2><br" +
                        "<h2>Year Published %d</h2><br" +
                        "<h2>Publisher: %s</h2><br" +
                        "<h2>Date Added To LongBox: %s</h2><br" +
                        "</div></body></html><br",
                comicBook.getSeriesTitle(),
                comicBook.getAuthor(),
                comicBook.getArtist(),
				GenreUtils.genreListToString(comicBook.getGenres()),
                comicBook.getDescription(),
                comicBook.getNumberOfIssues(),
                comicBook.getYearPublished(),
                comicBook.getPublisher(),
                comicBook.getDateAdded().toString()
        );
        return htmlContent;
    }
}
