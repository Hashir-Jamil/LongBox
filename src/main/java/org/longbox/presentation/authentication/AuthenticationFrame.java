package org.longbox.presentation.authentication;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.longbox.businesslogic.UserSession;
import org.longbox.domainobjects.dto.UserDTO;
import org.longbox.persistence.stubdatabase.UserStubDB;
import org.longbox.presentation.profile.HomeFrame;

public class AuthenticationFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel cardPanel;
	private CardLayout cardLayout;
    private LoginPanel loginPanel = new LoginPanel();
    private RegistrationPanel registrationPanel = new RegistrationPanel();
    private UserStubDB userStubDB = new UserStubDB();
    private UserSession userSession;
	private List<UserDTO> users;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthenticationFrame frame = new AuthenticationFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AuthenticationFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 550);
        setTitle("LongBox");
        
        setLocationRelativeTo(null);
        
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        cardPanel.add(loginPanel, "login");
        cardPanel.add(registrationPanel, "registration");

        // Set the default panel to login page
        cardLayout.show(cardPanel, "login");

        add(cardPanel);
        setVisible(true);
        
        // login page buttons action listener
        loginPanel.getSignUpButton().addActionListener(this);
        loginPanel.getSignInButton().addActionListener(this);
        
        //registration page buttons action listener
        registrationPanel.getSignInButton().addActionListener(this);
		registrationPanel.getSignUpButton().addActionListener(this);
		
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginPanel.getSignUpButton()) {
            cardLayout.show(cardPanel, "registration");
        }else if(e.getSource() ==  registrationPanel.getSignInButton()) {
        	cardLayout.show(cardPanel, "login");
        }else if(e.getSource() == loginPanel.getSignInButton()) {
			validateLogin();
        }else if(e.getSource() == registrationPanel.getSignUpButton()) {
			registerUser();
        }
    }

	private void validateLogin(){
		users = userStubDB.deserializeUserStubDB(userStubDB.getABSOLUTE_FILE_PATH());
		for(UserDTO u: users) {
			if (loginPanel.getUsername().equals(u.getUserName())) {
				if (loginPanel.getDecryptedPassword().equals(u.getPassword())) {
					loginPanel.getErrorLabel().setText("Login Successful!");
					loginPanel.getErrorLabel().setForeground(Color.GREEN);
					dispose();
					HomeFrame homeFrame = new HomeFrame(userSession.getInstance(u));
					homeFrame.setVisible(true);

				} else {
					loginPanel.getErrorLabel().setText("Password Incorrect");
					loginPanel.getErrorLabel().setForeground(Color.red);
				}
			} else {
				loginPanel.getErrorLabel().setText("User does not exist");
				loginPanel.getErrorLabel().setForeground(Color.red);
			}
		}
	}

	private void registerUser(){
		userStubDB.setUserStubData(
				userStubDB.deserializeUserStubDB(
						userStubDB.getABSOLUTE_FILE_PATH()));
		userStubDB.getUserStubData().add(registrationPanel.getRegistrationDetails());
		userStubDB.serializeUserStubDB();
		registrationPanel.getMessageLabel().setText("Registeration Successful");
		cardLayout.show(cardPanel, "login");
	}

}