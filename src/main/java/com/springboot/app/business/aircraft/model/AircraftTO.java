package com.springboot.app.business.aircraft.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AircraftTO {
	private int id;
	private String model;
	private int fuelConsumption;
	private int lubricantConsumption;
	private int passengerCapacity;
	private int weightTolerance;
	private int maxDistance;
}
