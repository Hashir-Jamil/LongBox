package org.longbox.businesslogic;

import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.utils.RegistrationUtils;
//Tests to determine that methods implemented are working correctly for checking password and valid email
class RegistrationUtilTests {
	@Test
	void test_invalid_password_1() {
		String password = "passwor";
		assertFalse(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void test_invalid_password_2() {
		String password = "PASSWORD123412dasdas";
		assertFalse(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void test_invalid_password_3() {
		String password = "PassWord123";
		assertFalse(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void test_invalid_password_4() {
		String password = "Pa$sword";
		assertFalse(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void test_valid_password_1(){
		String password = "Passw0rd!";
        assertTrue(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void test_valid_password_2(){
		String password = "AbCdEfGh!123";
		assertTrue(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void test_valid_password_3(){
		String password = "StrongPwd1!";
		assertTrue(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void test_valid_password_4(){
		String password = "Pa$$w0rd123!80";
		assertTrue(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void test_invalid_email_1(){
		String email = "john.doe@example";
		assertFalse(RegistrationUtils.isValidEmailAddress(email));
	}

	@Test
	void test_invalid_email_2(){
		String email = "alice.smith123@gmail..com";
		assertFalse(RegistrationUtils.isValidEmailAddress(email));
	}

	@Test
	void test_invalid_email_3(){
		String email = "invalid-email@com";
		assertFalse(RegistrationUtils.isValidEmailAddress(email));
	}

	@Test
	void test_invalid_email_4(){
		String email = "user@domain_with_spaces .com";
		assertFalse(RegistrationUtils.isValidEmailAddress(email));
	}

	@Test
	void test_valid_email_1(){
		String email = "john.doe@example.com";
		assertTrue(RegistrationUtils.isValidEmailAddress(email));
	}

	@Test
	void test_valid_email_2(){
		String email = "alice_smith123@gmail.com";
		assertTrue(RegistrationUtils.isValidEmailAddress(email));
	}

	@Test
	void test_valid_email_3(){
		String email = "info@company.co.uk";
		assertTrue(RegistrationUtils.isValidEmailAddress(email));
	}

	@Test
	void test_valid_email_4(){
		String email = "user_name123@subdomain.com";
		assertTrue(RegistrationUtils.isValidEmailAddress(email));
	}

}
