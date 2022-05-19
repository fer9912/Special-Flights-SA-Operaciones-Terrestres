package com.springboot.app.business.flightroutemaster.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightRouteRequestApiTO {
	private String aircraft;
	private List<String> excludeDestinations;
	private List<String> includeDestinations;
}
