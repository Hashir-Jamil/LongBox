package org.longbox.persistence.dao;

import org.longbox.businesslogic.exception.EmailDoesNotExistException;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.exception.UserNameDoesNotExistException;
import org.longbox.businesslogic.exception.UsernameOrEmailExistsException;
import org.longbox.persistence.entity.User;

public interface UserDao {

    User getUserById(long id) throws UserIDDoesNotExistException;

    User getUserByUserName(String userName) throws UserNameDoesNotExistException;
    
    User getUserByEmail(String email) throws EmailDoesNotExistException;

    void saveUser(User user) throws UsernameOrEmailExistsException;

    boolean deleteUser(User user);

    boolean modifyUser(User user);

}
