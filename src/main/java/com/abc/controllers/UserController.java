package com.abc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.model.User;
import com.abc.model.UserDao;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(long id) {
		try {
			User user = new User(id);
			userDao.delete(user);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "User succesfully deleted!";
	}

	@RequestMapping(value = "/get-by-email")
	@ResponseBody
	public String getByEmail(String email) {
		String userId;
		try {
			User user = userDao.getByEmail(email);
			userId = String.valueOf(user.getId());
		} catch (Exception ex) {
			return "User not found";
		}
		return "The user id is: " + userId;
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public User create(String email, String name) {
		User user=null;
		try {
			System.out.println("hi");
			user = new User(email, name);
			userDao.save(user);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}

	@RequestMapping(value = "/test")
	@ResponseBody
	public String test() {
		return "test";
	}


	@Autowired
	private UserDao userDao;

}
