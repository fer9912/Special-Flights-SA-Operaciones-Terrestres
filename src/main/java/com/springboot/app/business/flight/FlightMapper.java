package com.springboot.app.business.flight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.app.business.aircraft.AircraftService;
import com.springboot.app.business.flight.model.FlightDE;
import com.springboot.app.business.flight.model.FlightTO;

@Component
public class FlightMapper {
	@Autowired
	AircraftService aircraftService;

	public FlightTO mapTo(FlightDE de) {
		FlightTO to = new FlightTO();
		to.setCode(de.getCode());
		to.setOrigin(de.getOrigin());
		to.setDestination(de.getDestination());
		to.setDate(de.getDate());
		to.setCompany(de.getCompany());
		to.setDay(de.getDay());
		to.setHour(de.getHour());
		to.setRoute(de.getRoute());
		to.setStatus(de.getStatus());
		to.setAircraft(aircraftService.getAircraft(de.getAircraft()).getModel());
		to.setAircrafts(getAircraft(de.getAircrafts()));
		to.setPeopleEstimate(de.getPeopleEstimate());
		to.setLandingDate(de.getLandingDate());
		to.setLandingHour(de.getLandingHour());
		to.setDuration(de.getDuration());
		to.setFlightRule(de.getFlightRule());
		to.setFlightType(de.getFlightType());
		to.setRouteCode(de.getRouteCode());
		to.setLubricantConsumption(de.getLubricantConsumption());
		to.setFuelConsumption(de.getFuelConsumption());
		return to;
	}

	private List<String> getAircraft(String aircrafts) {
		aircrafts = aircrafts.replace("[", "");
		aircrafts = aircrafts.replace("]", "");
		String[] values = aircrafts.split(",");
		List<String> ret = new ArrayList();
		for (String i : values) {
			ret.add(i.trim());
		}
		return ret;
	}

	public List<FlightTO> mapTOList(List<FlightDE> des) {
		return des.stream().map(d -> mapTo(d)).collect(Collectors.toList());
	}

	public static FlightDE mapDe(FlightTO to) {
		FlightDE de = new FlightDE();
		de.setCode(to.getCode());
		de.setOrigin(to.getOrigin());
		de.setDestination(to.getDestination());
		de.setDate(to.getDate());
		de.setCompany(to.getCompany());
		de.setDay(to.getDay());
		de.setHour(to.getHour());
		de.setRoute(to.getRoute());
		de.setStatus(to.getStatus());
		return de;
	}
}
