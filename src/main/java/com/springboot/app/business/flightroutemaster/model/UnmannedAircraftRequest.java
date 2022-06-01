package com.springboot.app.business.flightroutemaster.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UnmannedAircraftRequest {
	private double originLat;
	private double originLon;
	private double destinationLat;
	private double destinationLon;

}
