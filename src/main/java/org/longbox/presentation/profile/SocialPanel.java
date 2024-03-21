package org.longbox.presentation.profile;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.longbox.businesslogic.service.UserService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.persistence.dao.UserDaoImpl;
import org.longbox.presentation.tablemodels.UsersTableModel;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class SocialPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private List<UserDto> allUsersList;
	private UserService userService;
	
	private JPanel panel;
	private JLabel titleLabel;
	private JSeparator separator;
	private UsersTableModel usersTableModel;
	private JScrollPane scrollPane;
	private JTable allUsersTable;
	
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
	}
	
	private void addLabels() {
		titleLabel = new JLabel("Nerd Hall of Fame");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
		titleLabel.setBounds(396, 11, 372, 43);
		panel.add(titleLabel);
		
		separator = new JSeparator();
		separator.setBounds(10, 92, 1144, 14);
		panel.add(separator);
		
		usersTableModel = new UsersTableModel(allUsersList);
		
		allUsersTable = new JTable(usersTableModel);
		allUsersTable.setBounds(0, 0, 1, 1);
		panel.add(allUsersTable);
		
		scrollPane = new JScrollPane(allUsersTable);
		scrollPane.setBounds(29, 120, 1104, 653);
		panel.add(scrollPane);
	}
}
