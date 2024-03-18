package org.longbox.businesslogic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.longbox.config.HibernateUtils;
import org.longbox.persistence.dao.UserDaoImpl;
import org.longbox.presentation.profile.ProfilePanel;

public class ProfileController implements ActionListener {
	
	private ProfilePanel profilePanel;
	
	public ProfileController(ProfilePanel profilePanel) {
		this.profilePanel = profilePanel;
		addListeners();
	}
	
	private void addListeners() {
		this.profilePanel.getAboutMeEditButton().addActionListener(this);
		this.profilePanel.getAboutMeCancelButton().addActionListener(this);
	}
	
    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == this.profilePanel.getAboutMeEditButton()) {
    		if (this.profilePanel.getAboutMeEditButton().getText() == "Edit") {
				this.profilePanel.getAboutMeEditButton().setText("Save");
				this.profilePanel.getAboutMeCancelButton().setEnabled(true);
				this.profilePanel.getAboutMe().setEditable(true);
			}
			else if (this.profilePanel.getAboutMeEditButton().getText() == "Save") {
				UserDaoImpl userDaoImpl = new UserDaoImpl(HibernateUtils.getSessionFactory());
				userDaoImpl.updateAboutMeString(this.profilePanel.getUser().getUser().getId(), this.profilePanel.getAboutMe().getText());
				this.profilePanel.getUser().getUser().setAboutMe(this.profilePanel.getAboutMe().getText());
				this.profilePanel.getAboutMeCancelButton().setEnabled(false);
				this.profilePanel.getAboutMeEditButton().setText("Edit");
				this.profilePanel.getAboutMe().setEditable(false);
			}
    	}
    	
    	if (e.getSource() == this.profilePanel.getAboutMeCancelButton()) {
    		this.profilePanel.getAboutMeCancelButton().setEnabled(false);
    		this.profilePanel.getAboutMe().setEditable(false);
    		this.profilePanel.getAboutMe().setText(this.profilePanel.getUser().getUser().getAboutMe());
			this.profilePanel.getAboutMeEditButton().setEnabled(true);
			this.profilePanel.getAboutMeEditButton().setText("Edit");
    	}
    }
}
