package org.longbox.businesslogic;

import org.longbox.domainobjects.dto.UserDto;

public class UserSession {
    private static UserSession activeUser = null;
    private UserDto user;

    private UserSession(UserDto user) {
        this.user = user;
    }

    public static UserSession getInstance(UserDto user) {
        if (activeUser == null) {
            activeUser = new UserSession(user);
        }
        return activeUser;
    }

    public static void setActiveUser(UserSession userSession) {
        activeUser = userSession;
    }


    public void clearUserSession() {
    	this.user = null;
    }
    
    public static UserSession getActiveUser() {
		return activeUser;
	}

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return user.toString();
    }

}
