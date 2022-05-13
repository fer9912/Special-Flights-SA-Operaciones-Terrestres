package com.springboot.app.business.passenger.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PassengerTO {
	private int idPassenger;
	private String name;
	private String docType;
	private String docNumber;
	private String nationality;
	private String age;
	private String sex;
	private String classFlight;
	private String menuType;
	private String medicalCondition;
	private String baggage;
	private int flight;
	private String status;
}
