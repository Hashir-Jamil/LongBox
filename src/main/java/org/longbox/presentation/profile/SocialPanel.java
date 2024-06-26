package org.longbox.presentation.profile;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.*;
import lombok.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;

import org.longbox.businesslogic.service.UserService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.persistence.dao.UserDaoImpl;
import org.longbox.presentation.tablemodels.UsersTableModel;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@Getter
@Setter
public class SocialPanel extends JPanel {

	private static final String RESET_TEXT = "Reset";
	private static final String TITLE_TEXT = "Nerd Hall of Fame";
	private static final String FILTER = "Filter";
	private static final long serialVersionUID = 1L;
	
	private List<UserDto> allUsersList;
	private UserService userService;
	
	private JPanel panel;
	private JLabel titleLabel;
	private JSeparator separator;
	private UsersTableModel usersTableModel;
	private JScrollPane scrollPane;
	private JTable allUsersTable;
	private JButton filterButton;
	private JComboBox numberComboBox, more_less_thanComboBox, choiceComboBox;
	private JButton resetButton;
	private JTableHeader header;
	
	public SocialPanel() {
		userService = new UserService(new UserDaoImpl(HibernateUtils.getSessionFactory()));
		this.allUsersList = userService.getAllUsers();

		initComponents();
	}
	
	private void initComponents() {
		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		addLabels();
		addExtras();
	}
	
	private void addLabels() {
		titleLabel = new JLabel(TITLE_TEXT);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
		titleLabel.setBounds(396, 11, 372, 43);
		panel.add(titleLabel);
		
		separator = new JSeparator();
		separator.setBounds(0, 66, 1144, 14);
		panel.add(separator);
		
		filterButton = new JButton(FILTER);
		filterButton.setBounds(1048, 90, 85, 29);
		panel.add(filterButton);
		
		resetButton = new JButton(RESET_TEXT);
		resetButton.setBounds(1016, 749, 117, 29);
		panel.add(resetButton);

		usersTableModel = new UsersTableModel(allUsersList);
		allUsersTable = new JTable(usersTableModel);

		scrollPane = new JScrollPane(allUsersTable);
		scrollPane.setBounds(29, 120, 1104, 624);
		panel.add(scrollPane);
		
		numberComboBox = new JComboBox();
		numberComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "15", "20", "25"}));
		numberComboBox.setBounds(974, 91, 71, 27);
		panel.add(numberComboBox);
		
		more_less_thanComboBox = new JComboBox();
		more_less_thanComboBox.setModel(new DefaultComboBoxModel(new String[] {"more than", "less than"}));
		more_less_thanComboBox.setBounds(829, 91, 133, 27);
		panel.add(more_less_thanComboBox);
		
		choiceComboBox = new JComboBox();
		choiceComboBox.setModel(new DefaultComboBoxModel(new String[] {"Reading", "Finished"}));
		choiceComboBox.setBounds(705, 91, 112, 27);
		panel.add(choiceComboBox);
	}
	
	private void addExtras() {
		header = allUsersTable.getTableHeader();
		header.setReorderingAllowed(false);
	}
	
	public int getNumberSelected() {
		return Integer.parseInt(numberComboBox.getSelectedItem().toString());
	}
	
	public String get_more_less_thanSelected() {
		return more_less_thanComboBox.getSelectedItem().toString();
	}
	
	public String getChoiceSelected() {
		if(choiceComboBox.getSelectedItem().toString().equals("Reading")){
			return "comicsReading";
		} else if(choiceComboBox.getSelectedItem().toString().equals("Finished")){
			return "comicsFinished";
		}
		return null;
	}

}
