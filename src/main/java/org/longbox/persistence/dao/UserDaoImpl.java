package org.longbox.persistence.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.hibernate.Transaction;
import org.longbox.businesslogic.exception.UserNameDoesNotExistException;
import org.longbox.businesslogic.exception.UsernameExistsException;
import org.longbox.persistence.entity.User;
import org.longbox.utils.HibernateUtils;

public class UserDaoImpl implements UserDao{
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public User getUserById(long id) {
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
    public void saveUser(User user) throws UsernameExistsException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
        catch(ConstraintViolationException cve){
            throw new UsernameExistsException();
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
}
