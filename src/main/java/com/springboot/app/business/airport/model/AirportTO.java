package com.springboot.app.business.airport.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AirportTO {
	private String iata;
	private String country;
	private String city;
	private String state;
	private String name;
	private String oaci;
}
