package com.springboot.app.business.airport.model;

import java.util.Objects;

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
	private double lat;
	private double lon;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirportTO other = (AirportTO) obj;
		return Objects.equals(iata, other.iata);
	}
}
