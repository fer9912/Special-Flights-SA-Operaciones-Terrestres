package com.springboot.app.business.user.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserTO {
	private int id;
	private String user;
	private String password;
	private String profile;
	private String email;
	private String name;

}
