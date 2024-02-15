package org.longbox.presentation.profile;

import org.longbox.domainobjects.dto.ComicBookDTO;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ComicBookTableModel extends DefaultTableModel {

    List<ComicBookDTO> comicBookList;
    private String[] columnNames =
            {"Series Title",
            "Author",
            "Artist",
            "Genres",
            "Description",
            "Number of Issues",
            "Publisher",
            "Year Published"};

    public ComicBookTableModel(List<ComicBookDTO> comicBookList) {
        this.comicBookList = comicBookList;

        for (String columnName : columnNames) {
            addColumn(columnName);
        }

        for (ComicBookDTO comicBook : comicBookList) {
            addRow(new Object[]{
                    comicBook.getSeriesTitle(),
                    comicBook.getAuthor(),
                    comicBook.getArtist(),
                    comicBook.getGenres(),
                    comicBook.getDescription(),
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
}
