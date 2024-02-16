package org.longbox.businesslogic;

import org.longbox.domainobjects.dto.UserDTO;

import javax.swing.text.html.HTMLEditorKit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
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
    
    public static UserSession getActiveUser() {
		return activeUser;
	}

    @Override
    public String toString() {
        return user.toString();
    }
    
    

    public static String generateUserProfileHTML (UserSession userSession) {
        String htmlContent = String.format(
                "<html><body><div align='center'><h1>Username: %s</h1><br" +
                        "<h2>First Name: %s</h2><br" +
                        "<h2>Last Name: %s</h2><br" +
                        "<h2>Email Address: %s</h2><br" +
                        "<h2>Date of Birth: %s</h2><br" +
                        "<h2>Country: %s</h2><br" +
                        "<h2>Join Date: %s</h2><br" +
                        "</div></body></html><br",
                userSession.getUser().getUserName(),
                userSession.getUser().getFirstName(),
                userSession.getUser().getLastName(),
                userSession.getUser().getEmail(),
                userSession.getUser().getDob().toString(),
                userSession.getUser().getCountry(),
                userSession.getUser().getJoinDate().toString()
        );
        return htmlContent;
    }

	

}
