package com.springboot.app.business.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
@Component
public class UserDE implements Serializable {

	private static final long serialVersionUID = 6094407212788597216L;

	@Id
	@Column(name = "id_user", nullable = true)
	private int id;

	@Column(name = "user", length = 45, nullable = false)
	private String user;

	@Column(name = "password", length = 255, nullable = false)
	private String password;

	@Column(name = "profile", length = 100, nullable = true)
	private String profile;

	@Column(name = "email", length = 100, nullable = true)
	private String email;

	@Column(name = "name", length = 100, nullable = true)
	private String name;

	@Column(name = "rol", nullable = true)
	private int rol;

}
