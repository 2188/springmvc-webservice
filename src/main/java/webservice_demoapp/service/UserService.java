package webservice_demoapp.service;

import java.util.List;
import webservice_demoapp.model.User;

public interface UserService {

	public List<User> listAllUser();
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public User findUserByID (User user);
	
}
