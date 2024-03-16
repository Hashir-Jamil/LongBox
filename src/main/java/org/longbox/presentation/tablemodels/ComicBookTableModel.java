package org.longbox.presentation.tablemodels;

import org.longbox.domainobjects.dto.ComicBookDto;

import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ComicBookTableModel extends DefaultTableModel {
    List<ComicBookDto> comicBookList;
    private String[] columnNames = {
        "Series Title",
        "Author",
        "Artist",
        "Genres",
        "Number of Issues",
        "Publisher",
        "Year Published"
    };

    public ComicBookTableModel(List<ComicBookDto> comicBookList) {
        this.comicBookList = comicBookList;

        for (String columnName : columnNames) {
            addColumn(columnName);
        }

        for (ComicBookDto comicBook : comicBookList) {
            String genres = "";
            for (int i = 0; i < comicBook.getGenres().length - 1; i++) {
                genres += comicBook.getGenres()[i] + ", ";
            }
            genres += comicBook.getGenres()[comicBook.getGenres().length - 1];
            addRow(new Object[]{
                    comicBook.getSeriesTitle(),
                    comicBook.getAuthor(),
                    comicBook.getArtist(),
                    genres,
                    comicBook.getNumberOfIssues(),
                    comicBook.getPublisher(),
                    comicBook.getYearPublished(),
                    comicBook.getDateAdded()
            });
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public long getComicIdAtRow(int row) {
        return comicBookList.get(row).getId();
    }

    public void updateData(List<ComicBookDto> newData) {
        comicBookList = newData;
        setRowCount(0); // Clear existing rows
        for (ComicBookDto comicBook : comicBookList) {
            String genres = String.join(", ", comicBook.getGenres());
            addRow(new Object[]{
                    comicBook.getSeriesTitle(),
                    comicBook.getAuthor(),
                    comicBook.getArtist(),
                    genres,
                    comicBook.getNumberOfIssues(),
                    comicBook.getPublisher(),
                    comicBook.getYearPublished(),
                    comicBook.getDateAdded()
            });
        }
    }
}
