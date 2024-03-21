package org.longbox.businesslogic.controller;

import org.longbox.businesslogic.service.UserService;
import org.longbox.businesslogic.utils.ComicBookSearchUtils;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.persistence.dao.UserDao;
import org.longbox.persistence.dao.UserDaoImpl;
import org.longbox.presentation.profile.SocialPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SocialController implements ActionListener {
    private SocialPanel socialPanel;
    private UserDao userDao;
    private UserService userService;
    public SocialController(SocialPanel socialPanel){
        this.socialPanel = socialPanel;

        this.userDao = new UserDaoImpl(HibernateUtils.getSessionFactory());
        this.userService = new UserService(userDao);

        addListeners();
    }

    private void addListeners(){
        this.socialPanel.getResetButton().addActionListener(this);
        this.socialPanel.getFilterButton().addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.socialPanel.getResetButton()){
            this.socialPanel.reset();
        }
        if(e.getSource() == this.socialPanel.getFilterButton()){
            List<UserDto> userList = new ArrayList<>();
            if(this.socialPanel.get_more_less_thanSelected().equals("more than")){
                userList = userService.getUsersMoreThan(this.socialPanel.getChoiceSelected(), this.socialPanel.getNumberSelected());
            }
            if(this.socialPanel.get_more_less_thanSelected().equals("less than")){
                userList = userService.getUsersLessThan(this.socialPanel.getChoiceSelected(), this.socialPanel.getNumberSelected());
            }
            //this.socialPanel.getUsersTableModel().setRowCount(0);
            this.socialPanel.addElementsToTable(userList);
        }
    }
}
