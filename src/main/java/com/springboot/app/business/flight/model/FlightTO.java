package com.springboot.app.business.flight.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightTO {
	private int idFlight;
	private String origin;
	private String destination;
	private Date departure;
	private Date arrival;
	private String aircraft;
	private String route;
	private String status;

}
