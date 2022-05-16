package com.springboot.app.business.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.business.user.model.UserTO;

@CrossOrigin("*")
@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService service;

	@GetMapping("/validate")
	public ResponseEntity<?> getUser(@RequestParam(value = "user") String user,
			@RequestParam(value = "password") String password) {
		try {
			UserTO ret = this.service.searchUser(user, password);
			return ResponseEntity.ok(ret);
		} catch (Exception e) {
			return ResponseEntity.ok(null);
		}
	}

}
