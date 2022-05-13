package com.springboot.app.business.passenger.model;

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
@Table(name = "passenger")
@Component
public class PassengerDE implements Serializable {

	private static final long serialVersionUID = 6094407212788597216L;

	@Id
	@Column(name = "id_passenger", nullable = false)
	private int idPassenger;

	@Column(name = "name", length = 45, nullable = false)
	private String name;

	@Column(name = "doc_type", length = 45, nullable = false)
	private String docType;

	@Column(name = "doc_number", length = 45, nullable = false)
	private String docNumber;

	@Column(name = "nationality", length = 45, nullable = false)
	private String nationality;

	@Column(name = "age", length = 45, nullable = true)
	private String age;

	@Column(name = "sex", length = 45, nullable = true)
	private String sex;

	@Column(name = "class", length = 45, nullable = true)
	private String classFlight;

	@Column(name = "menu_type", length = 45, nullable = true)
	private String menuType;

	@Column(name = "medical_condition", length = 45, nullable = true)
	private String medicalCondition;

	@Column(name = "baggage", length = 45, nullable = true)
	private String baggage;

	@Column(name = "flight", nullable = true)
	private int idFlight;

	@Column(name = "status", length = 45, nullable = true)
	private String status;

}
