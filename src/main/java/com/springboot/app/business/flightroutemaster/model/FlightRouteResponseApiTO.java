package com.springboot.app.business.flightroutemaster.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightRouteResponseApiTO {
	private String aircraft;
	private List<String> optimalAircrafts;
	private int distance;
	private List<String> route;
	private String day;
	private int peopleEstimate;
	private int combustibleEstimado;
	private int lubricanteEstimado;
}
