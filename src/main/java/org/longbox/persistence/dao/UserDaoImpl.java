package org.longbox.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.hibernate.Transaction;
import org.longbox.businesslogic.exception.*;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.domainobjects.entity.User;
import org.longbox.domainobjects.mapper.UserMapper;

public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUserById(long id) throws UserIDDoesNotExistException {
        Session session = null;
        Transaction transaction = null;
        User user = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        if(user == null) {
            throw new UserIDDoesNotExistException();
        }
        return user;
    }
    
    // if the user does not exist (null), it throws a UserNameDoesNotExistException
    @Override
    public User getUserByUserName(String userName) throws UserNameDoesNotExistException {
        Session session = null;
        Transaction transaction = null;
        User user = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            Query query = session.createQuery("FROM User WHERE userName = :userName");
            query.setParameter("userName", userName);
            
            user = (User) query.uniqueResult();

            transaction.commit();
        } 
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        if(user == null) {
        	throw new UserNameDoesNotExistException();
        }
        return user;
    }
    
    @Override
    public User getUserByEmail(String email) throws EmailDoesNotExistException {
    	Session session = null;
        Transaction transaction = null;
        User user = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            Query query = session.createQuery("FROM User WHERE email = :email");
            query.setParameter("email", email);
            
            user = (User) query.uniqueResult();

            transaction.commit();
        } 
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        if(user == null) {
        	throw new EmailDoesNotExistException();
        }
        return user;
    }

    @Override
    public void saveUser(User user) throws UsernameOrEmailExistsException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            user = (User) session.merge(user);

            session.persist(user);
            transaction.commit();
        }
        catch(ConstraintViolationException cve){
            throw new UsernameOrEmailExistsException();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    public void updateAboutMeString(Long userID, String aboutMe) {
    	Session session = null;
    	Transaction transaction = null;
    	
    	try {
    		session = sessionFactory.openSession();
    		transaction = session.beginTransaction();
    		
    		Query query = session.createQuery("UPDATE User SET aboutMe = :aboutMe WHERE id = :userId");
    		query.setParameter("aboutMe", aboutMe);
    		query.setParameter("userId", userID.toString());
    		query.executeUpdate();
    		transaction.commit();
    	}
    	catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public boolean modifyUser(User user) {
        return false;
    }

	@Override
	public List<UserDto> getAllUsers() {
		Session session = null;
        List<User> userList = new ArrayList<>();

        try {
            session = sessionFactory.openSession();
            userList = session.createQuery(
                    "SELECT u FROM User u ORDER BY comicsFinished DESC",
                    User.class).list();
        }
        catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
		return UserMapper.toDtoList(userList);
	}
}
