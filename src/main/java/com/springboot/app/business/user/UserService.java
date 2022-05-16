package com.springboot.app.business.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.business.user.model.UserDE;
import com.springboot.app.business.user.model.UserTO;
import com.springboot.app.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public UserTO searchUser(String user, String password) {
		UserDE de = userRepository.findByUserAndPassword(user, password);
		return UserMapper.mapTo(de);
	}

}
