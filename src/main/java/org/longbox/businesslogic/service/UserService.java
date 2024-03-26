package org.longbox.businesslogic.service;

import lombok.*;

import java.util.List;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.EmailDoesNotExistException;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.exception.UserNameDoesNotExistException;
import org.longbox.businesslogic.exception.UsernameOrEmailExistsException;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.domainobjects.mapper.UserMapper;
import org.longbox.persistence.dao.UserDao;
import org.longbox.domainobjects.entity.User;

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
		currentUserLoggedIn.clearUserSession();
		UserSession.setActiveUser(null);
	}

	public UserDto getUserById(Long userId) throws UserIDDoesNotExistException {
		return UserMapper.toDto(userDao.getUserById(userId));
	}

	public UserDto getUserByUserName(String userName) throws UserNameDoesNotExistException {
		return UserMapper.toDto(userDao.getUserByUserName(userName));
	}

	public UserDto getUserByEmail(String email) throws EmailDoesNotExistException {
		return UserMapper.toDto(userDao.getUserByEmail(email));
	}

	public void saveUser(UserDto userDto) throws UsernameOrEmailExistsException {
		userDao.saveUser(UserMapper.toEntity(userDto));
	}

	public List<UserDto> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	public List<UserDto> getUsersMoreThan(String fieldName, int value) {
		return userDao.getUsersMoreThan(fieldName, value);
	}
	
	public List<UserDto> getUsersLessThan(String fieldName, int value) {
		return userDao.getUsersLessThan(fieldName, value);
	}
}
