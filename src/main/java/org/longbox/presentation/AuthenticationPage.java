package org.longbox.presentation;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.longbox.domainobjects.UserDTO;
import org.longbox.persistence.UserStubDB;

public class AuthenticationPage extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel cardPanel;
	private CardLayout cardLayout;
    LoginPage loginPage = new LoginPage();
    RegistrationPage registrationPage = new RegistrationPage();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthenticationPage frame = new AuthenticationPage();
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
	public AuthenticationPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 550);
        setTitle("LongBox");
        
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        cardPanel.add(loginPage, "login");
        cardPanel.add(registrationPage, "registration");

        // Set the default panel to login page
        cardLayout.show(cardPanel, "login");

        add(cardPanel);
        setVisible(true);
        
        // login page buttons action listener
        loginPage.getSignUpButton().addActionListener(this);
        loginPage.getSignInButton().addActionListener(this);
        
        //registration page buttons action listener
        registrationPage.getSignInButton().addActionListener(this);
		registrationPage.getSignUpButton().addActionListener(this);
		
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginPage.getSignUpButton()) {
            cardLayout.show(cardPanel, "registration");
        }else if(e.getSource() ==  registrationPage.getSignInButton()) {
        	cardLayout.show(cardPanel, "login");
        }else if(e.getSource() == loginPage.getSignInButton()) {
        	UserStubDB user = new UserStubDB();
        	user.loadUsers();
        	List<UserDTO> users = user.getUsers();
        	for(UserDTO u: users) {
        		if(loginPage.getUsername().equals(u.getUserName())) {
        			if(loginPage.getDecryptedPassword().equals(u.getPassword())) {
        				loginPage.getErrorLabel().setText("Login Successful!");
        				dispose();
        				HomePage homePage = new HomePage();
        				homePage.setVisible(true);
        			}else {
        				loginPage.getErrorLabel().setText("Passowrd Incorrect");
        			}
        		}else {
        			loginPage.getErrorLabel().setText("User does not exist");
        		}
        	}
        }
    }



}
