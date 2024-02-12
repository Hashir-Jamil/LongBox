package org.longbox.persistence.dao;

import org.longbox.persistence.entity.User;

public interface UserDao {

    User getUserById(long id);

    User getUserByUserName(String userName);

    User saveUser(User user);

    boolean deleteUser(User user);

    boolean modifyUser(User user);

}
