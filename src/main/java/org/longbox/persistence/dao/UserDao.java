package org.longbox.persistence.dao;

import java.util.List;

import org.longbox.businesslogic.exception.EmailDoesNotExistException;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.exception.UserNameDoesNotExistException;
import org.longbox.businesslogic.exception.UsernameOrEmailExistsException;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.domainobjects.entity.User;

public interface UserDao {

    User getUserById(long id) throws UserIDDoesNotExistException;

    User getUserByUserName(String userName) throws UserNameDoesNotExistException;
    
    User getUserByEmail(String email) throws EmailDoesNotExistException;

    void saveUser(User user) throws UsernameOrEmailExistsException;
    
    List<UserDto> getAllUsers();
    
    List<UserDto> getUsersMoreThan(String fieldName, int value);
    
    List<UserDto> getUsersLessThan(String fieldName, int value);

}
