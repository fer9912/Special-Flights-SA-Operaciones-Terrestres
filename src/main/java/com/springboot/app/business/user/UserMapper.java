package com.springboot.app.business.user;

import org.springframework.stereotype.Component;

import com.springboot.app.business.user.model.UserDE;
import com.springboot.app.business.user.model.UserTO;

@Component
public class UserMapper {

	public static UserTO mapTo(UserDE de) {
		UserTO to = new UserTO();
		to.setId(de.getId());
		to.setName(de.getName());
		to.setUser(de.getUser());
		to.setPassword(de.getPassword());
		to.setProfile(de.getProfile());
		to.setEmail(de.getEmail());
		return to;
	}
}
