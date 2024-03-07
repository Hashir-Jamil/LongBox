package org.longbox.integration.persistence.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.exception.UserNameDoesNotExistException;
import org.longbox.businesslogic.exception.UsernameOrEmailExistsException;
import org.longbox.domainobjects.dto.UserDTO;
import org.longbox.persistence.dao.UserDaoImpl;
import org.longbox.persistence.entity.User;

import java.util.Date;

class UserDaoImplTest {
	UserDTO u2;
	User user;
	UserDaoImpl userDaoImpl;

	@BeforeEach
	void init(){
		userDaoImpl = new UserDaoImpl();
		u2 = new UserDTO(
				"Always_Throwing",
				"Neo",
				"Anderson",
				new Date(1929,1,1),
				"address@provider.ca",
				"Always_Throwing",
				"Indonesia");

		user = new User(u2.getUserName(),
				u2.getFirstName(),
				u2.getLastName(),
				u2.getDob(),
				u2.getEmail(),
				u2.getPassword(),
				u2.getCountry());
	}

	@Test
	void test_add_1_Fail() {
		assertThrows(UsernameOrEmailExistsException.class, () -> userDaoImpl.saveUser(user));
	}

	@Test
	void test_add_2_Fail(){
		UserDTO u3 = new UserDTO(
				"Phoenix",
				"Stan",
				"Lee",
				new Date(2000,4,31),
				"123fake@nowhere.org",
				"Phoenix",
				"United Kingdom");

		User user1 = new User(u3.getUserName(),
				u3.getFirstName(),
				u3.getLastName(),
				u3.getDob(),
				u3.getEmail(),
				u3.getPassword(),
				u3.getCountry());

		assertThrows(UsernameOrEmailExistsException.class, () -> userDaoImpl.saveUser(user1));
    }

	@Test
	void test_getUserByUserName(){
		User expected = new User(
				"Always_Scheming",
				"John",
				"Smith",
				new Date(1990, 12, 1),
				"email@domain.com",
				"Always_Scheming",
				"Canada");
		
		User actual;
		try {
			actual = userDaoImpl.getUserByUserName(expected.getUserName());
			assertEquals(expected.getUserName(), actual.getUserName());
			assertEquals(expected.getFirstName(), actual.getFirstName());
			assertEquals(expected.getLastName(), actual.getLastName());
			assertEquals(expected.getEmail(), actual.getEmail());
		} catch (UserNameDoesNotExistException e) {
			fail();
		}
	}
	
	@Test
	void test_getUserByUserName_Fail(){
		assertThrows(UserNameDoesNotExistException.class, () -> userDaoImpl.getUserByUserName("Jimmy"));
	}
	
	@Test
	void test_getUserByID(){
		User expected = new User(
				"Always_Scheming",
				"John",
				"Smith",
				new Date(1990, 12, 1),
				"email@domain.com",
				"Always_Scheming",
				"Canada");
		
		User actual;
		try {
			actual = userDaoImpl.getUserById(1);
			assertEquals(expected.getUserName(), actual.getUserName());
			assertEquals(expected.getFirstName(), actual.getFirstName());
			assertEquals(expected.getLastName(), actual.getLastName());
			assertEquals(expected.getEmail(), actual.getEmail());
		} catch (UserIDDoesNotExistException e) {
			fail();
		}
	}
	
	@Test
	void test_getUserByID_Fail(){
		assertThrows(UserIDDoesNotExistException.class, () -> userDaoImpl.getUserById(10));
	}


}
