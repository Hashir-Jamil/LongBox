package org.longbox.persistence.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.exception.UsernameExistsException;
import org.longbox.domainobjects.dto.UserDTO;
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
	void test_add_1() {
		assertThrows(UsernameExistsException.class, () -> userDaoImpl.saveUser(user));
	}

	@Test
	void test_add_2(){
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
		try{
			userDaoImpl.saveUser(user1);
		} catch (UsernameExistsException e) {
           	fail();
        }
    }

}
