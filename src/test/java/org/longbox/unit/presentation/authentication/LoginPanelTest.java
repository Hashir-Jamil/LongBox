package org.longbox.unit.presentation.authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.presentation.authentication.LoginPanel;

public class LoginPanelTest {

    private LoginPanel loginPanel;

    @BeforeEach
    public void setUp() {
        loginPanel = new LoginPanel();
    }

    @Test
    public void testUIInitialization() {
        assertNotNull(loginPanel.getSignUpButton());
        assertNotNull(loginPanel.getSignInButton());
        assertNotNull(loginPanel.getErrorLabel());
        assertEquals("", loginPanel.getErrorLabel().getText());
    }

    @Test
    public void testGetUsername() {
        loginPanel.getUsernameText().setText("testUser");
        assertEquals("testUser", loginPanel.getUsername());
    }

    @Test
    public void testGetDecryptedPassword() {
        loginPanel.getPasswordField().setText("testPassword");
        assertEquals("testPassword", loginPanel.getDecryptedPassword());
    }

    @Test
    public void testSignInButtonAction() {
        ActionListener signInActionListener = mock(ActionListener.class);
        loginPanel.getSignInButton().addActionListener(signInActionListener);

        loginPanel.getSignInButton().doClick();

        verify(signInActionListener, times(1)).actionPerformed(any(ActionEvent.class));
    }

    @Test
    public void testSignUpButtonAction() {
        ActionListener signUpActionListener = mock(ActionListener.class);
        loginPanel.getSignUpButton().addActionListener(signUpActionListener);

        loginPanel.getSignUpButton().doClick();

        verify(signUpActionListener, times(1)).actionPerformed(any(ActionEvent.class));
    }

    // Add more test cases as needed, covering different scenarios and edge cases.
}
