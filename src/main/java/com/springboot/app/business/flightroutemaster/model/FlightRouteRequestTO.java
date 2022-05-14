package com.springboot.app.business.flightroutemaster.model;

import java.util.List;

import com.springboot.app.business.aircraft.model.AircraftTO;
import com.springboot.app.business.airport.model.AirportTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightRouteRequestTO {
	private AircraftTO aircraft;
	private List<AirportTO> excludeDestinations;
	private List<AirportTO> includeDestinations;
}
