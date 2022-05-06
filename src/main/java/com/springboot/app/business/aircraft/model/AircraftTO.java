package com.springboot.app.business.aircraft.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AircraftTO {
	private String code;
	private String model;
	private int fuelConsumption;
	private int passengerCapacity;
	private int weightTolerance;
	private String crew;
}
