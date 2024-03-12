package org.longbox;

import org.longbox.businesslogic.controller.AuthenticationController;
import org.longbox.presentation.authentication.AuthenticationFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AuthenticationController authenticationController = new AuthenticationController();
        });
    }
}