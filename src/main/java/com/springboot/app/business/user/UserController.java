package com.springboot.app.business.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.business.user.model.UserTO;

@CrossOrigin("*")
@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping("/validate")
	public ResponseEntity<?> getUser(@RequestBody UserRequest request) {
		try {
			UserTO ret = this.service.searchUser(request);
			return ResponseEntity.ok(ret);
		} catch (Exception e) {
			return ResponseEntity.ok(null);
		}
	}

}
