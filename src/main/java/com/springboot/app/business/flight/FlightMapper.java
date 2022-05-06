package com.springboot.app.business.flight;

import com.springboot.app.business.flight.model.FlightDE;
import com.springboot.app.business.flight.model.FlightTO;

public class FlightMapper {

	public static FlightTO mapTo(FlightDE de) {
		FlightTO to = new FlightTO();
		to.setOrigin(de.getOrigin());
		to.setIdFlight(de.getIdFlight());
		to.setDestination(de.getDestination());
		to.setDeparture(de.getDeparture());
		to.setArrival(de.getArrival());
		to.setCrew(de.getCrew().split(","));
		to.setRoute(de.getRoute());
		to.setStatus(de.getStatus());

		return to;
	}

	public static FlightDE mapDe(FlightTO to) {
		FlightDE de = new FlightDE();
		de.setOrigin(to.getOrigin());
		de.setIdFlight(to.getIdFlight());
		de.setDestination(to.getDestination());
		de.setDeparture(to.getDeparture());
		de.setArrival(to.getArrival());
		de.setCrew(to.getCrew().toString());
		de.setRoute(to.getRoute());
		de.setStatus(to.getStatus());

		return de;
	}
}
