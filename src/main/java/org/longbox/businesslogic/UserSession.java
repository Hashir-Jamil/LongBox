package org.longbox.businesslogic;

import org.longbox.domainobjects.UserDTO;

public class UserSession {

    private static UserSession activeUser = null;
    private String userName;

    private UserSession(String name) {
        this.userName = name;
    }

    private static UserSession getInstance(UserDTO user) {
        if (activeUser == null) {
            activeUser = new UserSession(user.getUserName());
        }
        return activeUser;
    }

}
