package org.longbox.presentation.tablemodels;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.longbox.domainobjects.dto.ComicBookDto;

public class TrendingRegionalTableModel extends DefaultTableModel {

private static final long serialVersionUID = 1L;
	
	List<ComicBookDto> comicBookList;
    private String[] columnNames =
            {"Favorites Count",
            "Series Title",
            "Author",
            "Artist",
            "Number of Issues",
            "Publisher"};

	public TrendingRegionalTableModel(List<ComicBookDto> comicBookList, String region) {	
		if(region.equals("North America")) {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getNorthAmericaFavoritesCount() > comicBookList.get(min).getNorthAmericaFavoritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		} else if(region.equals("South America")) {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getSouthAmericaFavoritesCount() > comicBookList.get(min).getSouthAmericaFavoritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		} else if(region.equals("Europe")) {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getEuropeFavoritesCount() > comicBookList.get(min).getEuropeFavoritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		} else if(region.equals("Asia")) {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getAsiaFavoritesCount() > comicBookList.get(min).getAsiaFavoritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		} else if(region.equals("Africa")) {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getAfricaFavoritesCount() > comicBookList.get(min).getAfricaFavoritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		} else if(region.equals("Oceania")) {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getOceaniaFavoritesCount() > comicBookList.get(min).getOceaniaFavoritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		} else if(region.equals("Antarctica")) {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getAntarcticaFavoritesCount() > comicBookList.get(min).getAntarcticaFavoritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		} else {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getFavoritesCount() > comicBookList.get(min).getFavoritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		}
		
		this.comicBookList = comicBookList;

        for (String columnName : columnNames) {
            addColumn(columnName);
        }

        for (ComicBookDto comicBook : comicBookList) {      	
        	if(region.equals("North America")) {
        		addRow(new Object[]{
	            		comicBook.getNorthAmericaFavoritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	} else if(region.equals("South America")) {
        		addRow(new Object[]{
	            		comicBook.getSouthAmericaFavoritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	} else if(region.equals("Europe")) {
        		addRow(new Object[]{
	            		comicBook.getEuropeFavoritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	} else if(region.equals("Asia")) {
        		addRow(new Object[]{
	            		comicBook.getAsiaFavoritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	} else if(region.equals("Afrcia")) {
        		addRow(new Object[]{
	            		comicBook.getAfricaFavoritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	} else if(region.equals("Oceania")) {
        		addRow(new Object[]{
	            		comicBook.getOceaniaFavoritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	} else if(region.equals("Antarctica")) {
        		addRow(new Object[]{
	            		comicBook.getAntarcticaFavoritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	} else {
        		addRow(new Object[]{
	            		comicBook.getFavoritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	}
        }
	}
	
	@Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public long getComicIdAtRow(int row) {
        return comicBookList.get(row).getId();
    }

//    public void updateData(List<ComicBookDto> newData) {
//        comicBookList = newData;
//        setRowCount(0); // Clear existing rows
//        for (ComicBookDto comicBook : comicBookList) {
//            addRow(new Object[]{
//            		comicBook.getFavoritesCount(),
//                    comicBook.getSeriesTitle(),
//                    comicBook.getAuthor(),
//                    comicBook.getArtist(),
//                    comicBook.getNumberOfIssues(),
//                    comicBook.getPublisher()
//            });
//        }
//    }
}
