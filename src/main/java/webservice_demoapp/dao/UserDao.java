package webservice_demoapp.dao;

import java.util.List;
import webservice_demoapp.model.*;

public interface UserDao {

	public List<User> listAllUser();
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public User findUserByID (User user);
	
}
