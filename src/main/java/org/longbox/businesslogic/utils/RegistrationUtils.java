package org.longbox.businesslogic.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

public class RegistrationUtils {
	// validate email library
	public static boolean isValidEmailAddress(String email) {
		return EmailValidator.getInstance().isValid(email);
	}

	// validate password https://www.geeksforgeeks.org/how-to-validate-a-password-using-regular-expressions-in-java/
	public static boolean isValidPassword(String password){
		String regex = "^(?=.*[0-9])"
				+ "(?=.*[a-z])(?=.*[A-Z])"
				+ "(?=.*[@#$%^&+=!])"
				+ "(?=\\S+$).{8,20}$";
		Pattern p = Pattern.compile(regex);

		if (password == null) {
			return false;
		}

		Matcher m = p.matcher(password);

		return m.matches();
	}	
	
	public static boolean isValidUserName(String userName) {
		if (userName.contains(Character.toString('@')) || userName.isBlank()) {
			return false;
		} else {
			return true;
		}
	}
}
