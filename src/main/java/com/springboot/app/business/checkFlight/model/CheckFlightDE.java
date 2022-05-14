package com.springboot.app.business.checkFlight.model;

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
@Table(name = "check_flight")
@Component
public class CheckFlightDE implements Serializable {

	private static final long serialVersionUID = 6094407212788597216L;

	@Id
	@Column(name = "flight_code", length = 10, nullable = true)
	private String code;

	@Column(name = "id_check_flight", nullable = false)
	private int idCheckFlight;

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

	@Column(name = "a6", length = 10, nullable = true)
	private boolean a6;

	@Column(name = "a7", length = 10, nullable = true)
	private boolean a7;

	@Column(name = "a8", length = 10, nullable = true)
	private boolean a8;

	@Column(name = "a9", length = 10, nullable = true)
	private boolean a9;

	@Column(name = "a10", length = 10, nullable = true)
	private boolean a10;

	@Column(name = "a11", length = 10, nullable = true)
	private boolean a11;

	@Column(name = "a12", length = 10, nullable = true)
	private boolean a12;

	@Column(name = "a13", length = 10, nullable = true)
	private boolean a13;

	@Column(name = "a14", length = 10, nullable = true)
	private boolean a14;

	@Column(name = "a15", length = 10, nullable = true)
	private boolean a15;

	@Column(name = "a16", length = 10, nullable = true)
	private boolean a16;

	@Column(name = "a17", length = 10, nullable = true)
	private boolean a17;

	@Column(name = "a18", length = 10, nullable = true)
	private boolean a18;

	@Column(name = "a19", length = 10, nullable = true)
	private boolean a19;

	@Column(name = "a20", length = 10, nullable = true)
	private boolean a20;

	@Column(name = "a21", length = 10, nullable = true)
	private boolean a21;

	@Column(name = "a22", length = 10, nullable = true)
	private boolean a22;

	@Column(name = "a23", length = 10, nullable = true)
	private boolean a23;

	@Column(name = "a24", length = 10, nullable = true)
	private boolean a24;

	@Column(name = "b1", length = 10, nullable = true)
	private boolean b1;

	@Column(name = "b2", length = 10, nullable = true)
	private boolean b2;

	@Column(name = "b3", length = 10, nullable = true)
	private boolean b3;

	@Column(name = "b4", length = 10, nullable = true)
	private boolean b4;

	@Column(name = "b5", length = 10, nullable = true)
	private boolean b5;

	@Column(name = "b6", length = 10, nullable = true)
	private boolean b6;

	@Column(name = "b7", length = 10, nullable = true)
	private boolean b7;

	@Column(name = "b8", length = 10, nullable = true)
	private boolean b8;

	@Column(name = "b9", length = 10, nullable = true)
	private boolean b9;

	@Column(name = "b10", length = 10, nullable = true)
	private boolean b10;

	@Column(name = "b11", length = 10, nullable = true)
	private boolean b11;

	@Column(name = "b12", length = 10, nullable = true)
	private boolean b12;

	@Column(name = "b13", length = 10, nullable = true)
	private boolean b13;

	@Column(name = "b14", length = 10, nullable = true)
	private boolean b14;

	@Column(name = "c1", length = 10, nullable = true)
	private boolean c1;

	@Column(name = "c2", length = 10, nullable = true)
	private boolean c2;

	@Column(name = "c3", length = 10, nullable = true)
	private boolean c3;

	@Column(name = "c4", length = 10, nullable = true)
	private boolean c4;

	@Column(name = "c5", length = 10, nullable = true)
	private boolean c5;

	@Column(name = "c6", length = 10, nullable = true)
	private boolean c6;

	@Column(name = "c7", length = 10, nullable = true)
	private boolean c7;

	@Column(name = "c8", length = 10, nullable = true)
	private boolean c8;

	@Column(name = "c9", length = 10, nullable = true)
	private boolean c9;

	@Column(name = "c10", length = 10, nullable = true)
	private boolean c10;

	@Column(name = "c11", length = 10, nullable = true)
	private boolean c11;

	@Column(name = "c12", length = 10, nullable = true)
	private boolean c12;

	@Column(name = "d1", length = 10, nullable = true)
	private boolean d1;

	@Column(name = "d2", length = 10, nullable = true)
	private boolean d2;

	@Column(name = "d3", length = 10, nullable = true)
	private boolean d3;

	@Column(name = "e1", length = 10, nullable = true)
	private boolean e1;

}
