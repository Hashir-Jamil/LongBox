package org.longbox;

import org.longbox.businesslogic.controller.AuthenticationController;
import org.longbox.config.HibernateUtils;
import org.longbox.persistence.dao.UserDaoImpl;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        UserDaoImpl userDaoImpl = new UserDaoImpl(HibernateUtils.getSessionFactory());
        SwingUtilities.invokeLater(() -> {
            AuthenticationController authenticationController = new AuthenticationController(userDaoImpl);
        });
    }
}