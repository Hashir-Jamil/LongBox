package org.longbox.businesslogic.service;

import lombok.*;
import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.EmailDoesNotExistException;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.exception.UserNameDoesNotExistException;
import org.longbox.businesslogic.exception.UsernameOrEmailExistsException;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.persistence.dao.UserDao;
import org.longbox.persistence.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserService {

        private UserDao userDao;
        private UserSession currentUserLoggedIn;

        public UserService(UserDao userDao) {
            this.userDao = userDao;
        }

        public UserSession startSession(UserDto userDto) {
            //TO-DO: Login logic for starting user session
            return UserSession.getInstance(userDto);
        }

        public UserDto getLoggedInUser() {
            return currentUserLoggedIn.getUser();
        }

        public void endSession() {
            //TO-DO: Logout logic for ending user session
        }

        public UserDto getUserById(Long userId) throws UserIDDoesNotExistException {
            return new UserDto(userDao.getUserById(userId));
        }

        public UserDto getUserByUserName(String userName) throws UserNameDoesNotExistException {
            return new UserDto(userDao.getUserByUserName(userName));
        }

        public UserDto getUserByEmail(String email) throws EmailDoesNotExistException {
            return new UserDto(userDao.getUserByEmail(email));
        }

        public void saveUser(UserDto user) throws UsernameOrEmailExistsException {
            userDao.saveUser(new User(user));
        }
}
