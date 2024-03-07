package org.longbox.businesslogic;

import org.longbox.domainobjects.dto.UserDTO;

import javax.swing.text.html.HTMLEditorKit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserSession {

    private static UserSession activeUser = null;
    private UserDTO user;

    private UserSession(UserDTO user) {
        this.user = user;
    }

    public static UserSession getInstance(UserDTO user) {
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return user.toString();
    }

}
