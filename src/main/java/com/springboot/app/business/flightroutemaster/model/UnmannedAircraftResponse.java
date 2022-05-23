package com.springboot.app.business.flightroutemaster.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UnmannedAircraftResponse {
	private String aircraftModel;
	private List<String> optimalAircrafts;
	private int distance;
}
