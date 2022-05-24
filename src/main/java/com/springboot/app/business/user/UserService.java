package com.springboot.app.business.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.business.user.model.UserDE;
import com.springboot.app.business.user.model.UserTO;
import com.springboot.app.repositories.UserRepository;
import com.springboot.app.utils.AES;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserTO searchUser(UserRequest request) {
		String cryptoPass = AES.decryptText(request.getPassword(), "grops2022");
		String pass = AES.encrypt(cryptoPass, "grops2022");
		UserDE de = userRepository.findByUserAndPassword(request.getUser(), pass);
		return UserMapper.mapTo(de);
	}

}
