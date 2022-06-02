package com.springboot.app.business.flightroutemaster.model;

import java.util.List;

import com.springboot.app.business.aircraft.model.AircraftTO;
import com.springboot.app.business.airport.model.AirportTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightRouteResponseTO {
	private AircraftTO aircraft;
	private List<AircraftTO> optimalAircrafts;
	private int duration;
	private int distance;
	private List<AirportTO> route;
	private String day;
	private int peopleEstimate;
	private int combustibleEstimado;
	private int lubricanteEstimado;
}
