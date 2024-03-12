package org.longbox.businesslogic.controller;

import org.longbox.businesslogic.exception.EmailDoesNotExistException;
import org.longbox.businesslogic.exception.UserNameDoesNotExistException;
import org.longbox.businesslogic.exception.UsernameOrEmailExistsException;
import org.longbox.businesslogic.service.UserService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.persistence.dao.UserDaoImpl;
import org.longbox.presentation.authentication.AuthenticationFrame;
import org.longbox.presentation.profile.HomeFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticationController implements ActionListener {

    AuthenticationFrame authenticationFrame;
    UserDaoImpl userDaoImpl = new UserDaoImpl(HibernateUtils.getSessionFactory());
    UserService userService = new UserService(userDaoImpl);

    public AuthenticationController(){
        authenticationFrame = new AuthenticationFrame();
        addListeners();
    }

    private void addListeners(){
        this.authenticationFrame.getLoginPanel().getSignUpButton().addActionListener(this);
        this.authenticationFrame.getLoginPanel().getSignInButton().addActionListener(this);

        this.authenticationFrame.getRegistrationPanel().getSignUpButton().addActionListener(this);
        this.authenticationFrame.getRegistrationPanel().getSignInButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.authenticationFrame.getLoginPanel().getSignUpButton()) {
            this.authenticationFrame.showRegistration();
        }else if(e.getSource() ==  this.authenticationFrame.getRegistrationPanel().getSignInButton()) {
            this.authenticationFrame.showLogin();
        }else if(e.getSource() == this.authenticationFrame.getLoginPanel().getSignInButton()) {
            validateLogin();
        }else if(e.getSource() == this.authenticationFrame.getRegistrationPanel().getSignUpButton()) {
            registerUser();
        }
    }

    private void validateLogin(){

        String userName =  this.authenticationFrame.getLoginPanel().getUsername();
        String decryptedPassword =  this.authenticationFrame.getLoginPanel().getDecryptedPassword();

        UserDto user;

        try{
            if (userName.contains(Character.toString('@'))) {
                user = userService.getUserByEmail(userName);
            } else {
                user = userService.getUserByUserName(userName);
            }
            if(userName.equals(user.getUserName()) || userName.equals(user.getEmail())){
                if(decryptedPassword.equals(user.getPassword())){
                    this.authenticationFrame.getLoginPanel().getErrorLabel().setText("Login Successful!");
                    this.authenticationFrame.getLoginPanel().getErrorLabel().setForeground(Color.GREEN);
                    this.authenticationFrame.dispose();
                    HomeFrame homeFrame = new HomeFrame(userService.startSession(user));
                    HomeController homeController = new HomeController(homeFrame);
                    homeFrame.setVisible(true);
                } else {
                    this.authenticationFrame.getLoginPanel().getErrorLabel().setText("Password Incorrect");
                    this.authenticationFrame.getLoginPanel().getErrorLabel().setForeground(Color.red);
                }
            }
        } catch (UserNameDoesNotExistException e) {
            this.authenticationFrame.getLoginPanel().getErrorLabel().setText("User does not exist");
            this.authenticationFrame.getLoginPanel().getErrorLabel().setForeground(Color.red);
        } catch (EmailDoesNotExistException e) {
            this.authenticationFrame.getLoginPanel().getErrorLabel().setText("Email does not exist");
            this.authenticationFrame.getLoginPanel().getErrorLabel().setForeground(Color.red);
        }
    }

    private void registerUser(){
        try{
            userService.saveUser(this.authenticationFrame.getRegistrationPanel().getRegistrationDetails());
            this.authenticationFrame.getRegistrationPanel().getMessageLabel().setText("Registration Successful");
            this.authenticationFrame.showLogin();
        } catch (UsernameOrEmailExistsException e) {
            this.authenticationFrame.getRegistrationPanel().getMessageLabel().setText("Username or Email Exists! Please choose a unique username or Email.");
            this.authenticationFrame.getRegistrationPanel().getMessageLabel().setForeground(Color.red);
        }
    }
}
