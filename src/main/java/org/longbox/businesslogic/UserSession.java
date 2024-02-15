package org.longbox.businesslogic;

import org.longbox.domainobjects.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
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

    public UserDTO getUser() {
    	return this.user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
