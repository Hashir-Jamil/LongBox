package org.longbox.presentation.tablemodels;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.longbox.domainobjects.dto.UserDto;

public class UsersTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	List<UserDto> userList;
    
	private String[] columnNames =
            {"User Name",
            "Finished",
            "Reading",
            "Country"};

	public UsersTableModel(List<UserDto> userList) {
		super();
		this.userList = userList;
		
		for (String columnName : columnNames) {
            addColumn(columnName);
        }
		
		for(UserDto u: this.userList) {
			addRow(new Object[] {
				u.getUserName(),
				u.getComicsFinished(),
				u.getComicsReading(),
				u.getCountry()
			});
		}
	}
}
