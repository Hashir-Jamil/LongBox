package org.longbox.presentation.profile;

import org.longbox.domainobjects.dto.ComicBookDto;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ReadingAndFinishedComicBookTableModel extends DefaultTableModel {
	List<ComicBookDto> comicBookList;
	private String[] columnNames = {"Series Title","Author"};

	public ReadingAndFinishedComicBookTableModel(List<ComicBookDto> comicBookList) {
        this.comicBookList = comicBookList;

        for (String columnName : columnNames) {
            addColumn(columnName);
        }

        for (ComicBookDto comicBook : comicBookList) {
            addRow(new Object[]{
                    comicBook.getSeriesTitle(),
                    comicBook.getAuthor()
            });
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
