package org.longbox.integration.persistence.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.exception.UserNameDoesNotExistException;
import org.longbox.businesslogic.exception.UsernameOrEmailExistsException;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.persistence.dao.UserDaoImpl;
import org.longbox.domainobjects.entity.User;


import java.util.Date;

class UserDaoImplTest {
	private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	UserDto u2;
	User user;
	UserDaoImpl userDaoImpl;

	@BeforeEach
	void init(){
		userDaoImpl = new UserDaoImpl(HibernateUtils.getSessionFactory());

		u2 = new UserDto();
		u2.setUserName("Always_Throwing");
		u2.setFirstName("Neo");
		u2.setLastName("Anderson");
		u2.setDob(new Date(1929,1,1));
		u2.setEmail("address@provider.ca");
		u2.setPassword("Always_Throwing");
		u2.setCountry("Indonesia");
		u2.setDefaults();

		user = new User();
		user.setUserName(u2.getUserName());
		user.setFirstName(u2.getFirstName());
		user.setLastName(u2.getLastName());
		user.setDob(u2.getDob());
		user.setEmail(u2.getEmail());
		user.setPassword(u2.getPassword());
		user.setCountry(u2.getCountry());
		user.setDefaults();
	}

	@Test
	void test_add_1_Fail() {
		assertThrows(UsernameOrEmailExistsException.class, () -> userDaoImpl.saveUser(user));
	}

	@Test
	void test_add_2_Fail(){
		UserDto u3 = new UserDto();
		u3.setUserName("Phoenix");
		u3.setFirstName("Stan");
		u3.setLastName("Lee");
		u3.setDob(new Date(2000,4,31));
		u3.setEmail("123fake@nowhere.org");
		u3.setPassword("Phoenix");
		u3.setCountry("United Kingdom");
		u3.setDefaults();

		User user1 = new User();
		user1.setUserName(u3.getUserName());
		user1.setFirstName(u3.getFirstName());
		user1.setLastName(u3.getLastName());
		user1.setDob(u3.getDob());
		user1.setEmail(u3.getEmail());
		user1.setPassword(u3.getPassword());
		user1.setCountry(u3.getCountry());
		user1.setDefaults();

		assertThrows(UsernameOrEmailExistsException.class, () -> userDaoImpl.saveUser(user1));
    }

	@Test
	void test_getUserByUserName(){
		User expected = new User();
		expected.setUserName("Always_Scheming");
		expected.setFirstName("John");
		expected.setLastName("Smith");
		expected.setDob(new Date(1990, 12, 1));
		expected.setEmail("email@domain.com");
		expected.setPassword("Always_Scheming");
		expected.setCountry("Canada");
		expected.setDefaults();

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
		User expected = new User();
		expected.setUserName("Always_Scheming");
		expected.setFirstName("John");
		expected.setLastName("Smith");
		expected.setDob(new Date(1990, 12, 1));
		expected.setEmail("email@domain.com");
		expected.setPassword("Always_Scheming");
		expected.setCountry("Canada");
		expected.setDefaults();

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
		assertThrows(UserIDDoesNotExistException.class, () -> userDaoImpl.getUserById(20));
	}

	@Test
	void test_updateAboutMeString() {
		Session session = null;
		String actualAboutMe;
		
		//run the method to update aboutMe field in database
		userDaoImpl.updateAboutMeString((long) 1, "hello");
		
		//gets the updated about me
				try {
		    		session = sessionFactory.openSession();
		    		
		    		Query query = session.createQuery("SELECT aboutMe FROM User WHERE id = :userId");
		    		query.setParameter("userId", (long) 1);
		    		actualAboutMe = (String) query.uniqueResult();

		    	}
		    	
		        finally {
		            if (session != null) {
		                session.close();
		            }
		        }
		String expectedAboutMe = "hello";
		assertEquals(expectedAboutMe, actualAboutMe);
		
		//run the method again to update aboutMe field in database
				userDaoImpl.updateAboutMeString((long) 1, "bye");
  
				//gets the updated about me
				try {
					session = sessionFactory.openSession();
		
					Query query = session.createQuery("SELECT aboutMe FROM User WHERE id = :userId");
					query.setParameter("userId", (long) 1);
					actualAboutMe = (String) query.uniqueResult();

				}
	
				finally {
					if (session != null) {
						session.close();
					}
				}
				expectedAboutMe = "bye";
				assertEquals(expectedAboutMe, actualAboutMe);
	}
}
