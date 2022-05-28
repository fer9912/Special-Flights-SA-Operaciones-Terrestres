package com.springboot.app.business.automaticProcess;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.business.aircraft.model.AircraftDE;
import com.springboot.app.business.automaticProcess.model.RouteDE;
import com.springboot.app.business.flight.model.FlightDE;
import com.springboot.app.business.flightroutemaster.FlightRouteMasterService;
import com.springboot.app.business.flightroutemaster.model.FlightRouteRequestApiTO;
import com.springboot.app.business.flightroutemaster.model.FlightRouteResponseApiTO;
import com.springboot.app.repositories.AircraftRepository;
import com.springboot.app.repositories.FlightRepository;
import com.springboot.app.repositories.RouteRepository;

@Service
public class AutomaticProcessService {
	@Autowired
	private RouteRepository routeRepository;
	@Autowired
	private FlightRouteMasterService flightRouteMasterService;
	@Autowired
	private AircraftRepository aircraftRepository;
	@Autowired
	private FlightRepository flightRepository;

	@Transactional(rollbackFor = Exception.class)
	public void generateFlights() throws Exception {
		Date date = new Date();
		String day = getDayOfWeek(date);
		List<RouteDE> routes = this.routeRepository.findByDay(day);
		List<FlightDE> flights = new ArrayList<>();
		for (RouteDE route : routes) {
			List<String> destinationsToInclude = new ArrayList<>();
			FlightRouteRequestApiTO request = new FlightRouteRequestApiTO();
			FlightDE flight = new FlightDE();
			destinationsToInclude.add(route.getOrigin());
			if (route.getStop1() != null && !route.getStop1().trim().isEmpty()) {
				destinationsToInclude.add(route.getStop1());
			}
			if (route.getStop2() != null && !route.getStop2().trim().isEmpty()) {
				destinationsToInclude.add(route.getStop2());
			}
			destinationsToInclude.add(route.getDestination());
			request.setIncludeDestinations(destinationsToInclude);
			FlightRouteResponseApiTO response = this.flightRouteMasterService.generateFlightRouteApi(request);
			flight.setCode(route.getCodVuelo());
			flight.setCompany("Special Flights SA");
			flight.setDay(route.getDay());
			date.setDate(date.getDate() + 7);
			flight.setDate(date);
			flight.setOrigin(route.getOrigin());
			flight.setDestination(route.getDestination());
			flight.setRoute(response.getRoute().toString());
			System.out.println(response.getAircraft());
			AircraftDE aircraft = this.aircraftRepository.findByModel(response.getAircraft());
			flight.setAircraft(aircraft.getId());
			flight.setAircrafts(response.getOptimalAircrafts().toString());
			flight.setHour(route.getHour());
			flight.setStatus("APROBADA");
			flight.setPeopleEstimate(response.getPeopleEstimate());
			flights.add(flight);
		}
		this.flightRepository.saveAll(flights);
	}

	private String getDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return "Domingo";
		}
		if (dayOfWeek == 2) {
			return "Lunes";
		}
		if (dayOfWeek == 3) {
			return "Martes";
		}
		if (dayOfWeek == 4) {
			return "Miercoles";
		}
		if (dayOfWeek == 5) {
			return "Jueves";
		}
		if (dayOfWeek == 6) {
			return "Viernes";
		}
		if (dayOfWeek == 7) {
			return "Sabado";
		}
		return "";
	}

}
