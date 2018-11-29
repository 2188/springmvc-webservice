package webservice_demoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webservice_demoapp.dao.UserDao;
import webservice_demoapp.model.User;

@Service
public class UserService_Impl implements UserService {

	UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> listAllUser() {
		return userDao.listAllUser();
	}

	public void addUser(User user) {
		System.out.println("@@@@@@@@@@@@  "+user.getFirstName());
		userDao.addUser(user);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	public User findUserByID(User user) {
		return userDao.findUserByID(user);
	}

}
