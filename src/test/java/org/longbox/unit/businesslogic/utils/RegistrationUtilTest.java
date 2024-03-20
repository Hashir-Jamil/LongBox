package org.longbox.unit.businesslogic.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.utils.RegistrationUtils;

//Tests to determine that methods implemented are working correctly for checking password and valid email
class RegistrationUtilTest {
	@Test
	void testInvalidPassword1() {
		String password = "passwor";
		Assertions.assertFalse(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void testInvalidPassword2() {
		String password = "PASSWORD123412dasdas";
		assertFalse(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void testInvalidPassword3() {
		String password = "PassWord123";
		assertFalse(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void testInvalidPassword4() {
		String password = "Pa$sword";
		assertFalse(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void testValidPassword1(){
		String password = "Passw0rd!";
        assertTrue(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void testValidPassword2(){
		String password = "AbCdEfGh!123";
		assertTrue(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void testValidPassword3(){
		String password = "StrongPwd1!";
		assertTrue(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void testValidPassword4(){
		String password = "Pa$$w0rd123!80";
		assertTrue(RegistrationUtils.isValidPassword(password));
	}

	@Test
	void testInvalidEmail1(){
		String email = "john.doe@example";
		assertFalse(RegistrationUtils.isValidEmailAddress(email));
	}

	@Test
	void testInvalidEmail2(){
		String email = "alice.smith123@gmail..com";
		assertFalse(RegistrationUtils.isValidEmailAddress(email));
	}

	@Test
	void testInvalidEmail3(){
		String email = "invalid-email@com";
		assertFalse(RegistrationUtils.isValidEmailAddress(email));
	}

	@Test
	void testInvalidEmail4(){
		String email = "user@domain_with_spaces .com";
		assertFalse(RegistrationUtils.isValidEmailAddress(email));
	}

	@Test
	void testValidEmail1(){
		String email = "john.doe@example.com";
		assertTrue(RegistrationUtils.isValidEmailAddress(email));
	}

	@Test
	void testValidEmail2(){
		String email = "alice_smith123@gmail.com";
		assertTrue(RegistrationUtils.isValidEmailAddress(email));
	}

	@Test
	void testValidEmail3(){
		String email = "info@company.co.uk";
		assertTrue(RegistrationUtils.isValidEmailAddress(email));
	}

	@Test
	void testValidEmail4(){
		String email = "user_name123@subdomain.com";
		assertTrue(RegistrationUtils.isValidEmailAddress(email));
	}

	@Test
	public void testIsValidEmailAddressValidEmail() {
		assertTrue(RegistrationUtils.isValidEmailAddress("test@example.com"));
	}

	@Test
	public void testIsValidEmailAddressInvalidEmail() {
		assertFalse(RegistrationUtils.isValidEmailAddress("test@example"));
	}

	@Test
	public void testIsValidPasswordValidPassword() {
		assertTrue(RegistrationUtils.isValidPassword("Password@123"));
	}

	@Test
	public void testIsValidPasswordInvalidPasswordTooShort() {
		assertFalse(RegistrationUtils.isValidPassword("Pass@1"));
	}

	@Test
	public void testIsValidPasswordInvalidPasswordNoUppercase() {
		assertFalse(RegistrationUtils.isValidPassword("password@123"));
	}

	@Test
	public void testIsValidPasswordInvalidPasswordNoLowercase() {
		assertFalse(RegistrationUtils.isValidPassword("PASSWORD@123"));
	}

	@Test
	public void testIsValidPasswordInvalidPasswordNoNumber() {
		assertFalse(RegistrationUtils.isValidPassword("Password@"));
	}

	@Test
	public void testIsValidPasswordInvalidPasswordNoSpecialCharacter() {
		assertFalse(RegistrationUtils.isValidPassword("Password123"));
	}

	@Test
	public void testIsValidPasswordNullPassword() {
		assertFalse(RegistrationUtils.isValidPassword(null));
	}

	@Test
	public void testIsValidPasswordInvalidPasswordTooLong() {
		assertFalse(RegistrationUtils.isValidPassword("ThisIsAVeryLongPassword@123456"));
	}

	@Test
	public void testIsValidUserNameValidUserName() {
		assertTrue(RegistrationUtils.isValidUserName("username"));
	}

	@Test
	public void testIsValidUserNameInvalidUserNameContainsAtSymbol() {
		assertFalse(RegistrationUtils.isValidUserName("user@name"));
	}

	@Test
	public void testIsValidUserNameInvalidUserNameBlank() {
		assertFalse(RegistrationUtils.isValidUserName(""));
	}

	@Test
	public void testIsValidUserNameInvalidUserNameNull() {
		assertFalse(RegistrationUtils.isValidUserName(null));
	}
}
