package webservice_demoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import webservice_demoapp.model.*;
import webservice_demoapp.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping (value="/user/", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<User>> listAllUser () {
		
		List<User> userList = userService.listAllUser();
		
		if (userList.size() == 0)
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}
	
	
	@RequestMapping (value = "/addUser/", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Void> addUser (@RequestBody User user) {
		System.out.println("###############  "+user.getFirstName());
		userService.addUser(user);
		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping (value = "/update/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<Void> updateUser (@PathVariable int id, @RequestBody User user) {
		user.setId(id);
		userService.updateUser(user);
		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	
	@RequestMapping (value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<Void> deleteUser (@PathVariable int id, @RequestBody User user) {
		user.setId(id);
		userService.deleteUser(user);
		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping (value = "/findUserById/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public User findUserById (@PathVariable int id) {
		User user = new User();
		user.setId(id);
		User userResponse = userService.findUserByID(user);
		
		HttpHeaders headers = new HttpHeaders();
		return userResponse;
	}
	
	
	
}
