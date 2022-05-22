package com.springboot.app.business.checkCommissariat.model;

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
@Table(name = "check_commissariat")
@Component
public class CheckCommissariatDE implements Serializable {

	private static final long serialVersionUID = 6094407212788597216L;

	@Id
	@Column(name = "id_check_commissariat", nullable = false)
	private int idCheckCommissariat;

	@Column(name = "flight_code", length = 10, nullable = true)
	private String code;

	@Column(name = "a1", length = 10, nullable = true)
	private boolean a1;

	@Column(name = "a2", length = 10, nullable = true)
	private boolean a2;

	@Column(name = "a3", length = 10, nullable = true)
	private boolean a3;

	@Column(name = "a4", length = 10, nullable = true)
	private boolean a4;

	@Column(name = "a5", length = 10, nullable = true)
	private boolean a5;
}
