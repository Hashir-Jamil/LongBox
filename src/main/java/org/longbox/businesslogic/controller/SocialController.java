package org.longbox.businesslogic.controller;

import org.longbox.businesslogic.service.UserService;
import org.longbox.businesslogic.utils.UserSearchUtils;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.persistence.dao.UserDao;
import org.longbox.persistence.dao.UserDaoImpl;
import org.longbox.presentation.otheruser.OtherUserProfileFrame;
import org.longbox.presentation.profile.SocialPanel;
import org.longbox.presentation.tablemodels.UsersTableModel;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class SocialController implements ActionListener, MouseListener {
    private SocialPanel socialPanel;
    private UserDao userDao;
    private UserService userService;
    private final String columnName = "User Name";
    
    public SocialController(SocialPanel socialPanel){
        this.socialPanel = socialPanel;

        this.userDao = new UserDaoImpl(HibernateUtils.getSessionFactory());
        this.userService = new UserService(userDao);

        addListeners();
    }

    private void addListeners(){
        this.socialPanel.getResetButton().addActionListener(this);
        this.socialPanel.getFilterButton().addActionListener(this);
        this.socialPanel.getAllUsersTable().addMouseListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.socialPanel.getResetButton()){
            reloadTable(userService.getAllUsers());
        }
        if(e.getSource() == this.socialPanel.getFilterButton()){
            List<UserDto> userList = new ArrayList<>();
            if(this.socialPanel.get_more_less_thanSelected().equals("more than")){
                userList = userService.getUsersMoreThan(this.socialPanel.getChoiceSelected(), this.socialPanel.getNumberSelected());
            }
            if(this.socialPanel.get_more_less_thanSelected().equals("less than")){
                userList = userService.getUsersLessThan(this.socialPanel.getChoiceSelected(), this.socialPanel.getNumberSelected());
            }

            reloadTable(userList);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    	int headerCol = this.socialPanel.getHeader().columnAtPoint(e.getPoint());
		String selection = this.socialPanel.getAllUsersTable().getColumnName(headerCol).toString();
        int row = this.socialPanel.getAllUsersTable().rowAtPoint(e.getPoint());
        int col = this.socialPanel.getAllUsersTable().columnAtPoint(e.getPoint());
        if ((selection.equals(columnName) && e.getClickCount() == 2)) {
            System.out.println("\n mouse clicked \n");
            UserDto userDto = UserSearchUtils.getSearchedUser(this.socialPanel.getAllUsersList(), this.socialPanel.getAllUsersTable().getValueAt(row, col).toString());
            new OtherUserProfileFrame(userDto);
        }
    }

    public void reloadTable(List<UserDto> userDtoList){
        this.socialPanel.getPanel().remove(this.socialPanel.getScrollPane());

        this.socialPanel.setAllUsersList(userDtoList);

        this.socialPanel.setUsersTableModel(new UsersTableModel(this.socialPanel.getAllUsersList()));

        this.socialPanel.setAllUsersTable(new JTable(this.socialPanel.getUsersTableModel()));

        this.socialPanel.setScrollPane(new JScrollPane(this.socialPanel.getAllUsersTable()));
        this.socialPanel.getScrollPane().setBounds(29, 120, 1104, 624);
        this.socialPanel.getPanel().add(this.socialPanel.getScrollPane());

        this.socialPanel.getAllUsersTable().addMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //METHOD NOT TO BE IMPLEMENTED
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //METHOD NOT TO BE IMPLEMENTED
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //METHOD NOT TO BE IMPLEMENTED
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //METHOD NOT TO BE IMPLEMENTED
    }
}
