package com.springboot.app.business.automaticProcess;

import java.util.ArrayList;
import java.util.Calendar;
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
		Calendar date = Calendar.getInstance();
		String day = getDayOfWeek(date);
		List<RouteDE> routes = this.routeRepository.findByDay(day);
		List<FlightDE> flights = new ArrayList<>();
		date.add(Calendar.DATE, 7);
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
			flight.setCompany("Special Flights SA");
			flight.setDay(route.getDay());
			flight.setDate(date.getTime());
			flight.setCode(route.getCodVuelo() + "-" + dateToString(date, route.getHour()));
			flight.setOrigin(route.getOrigin());
			flight.setDestination(route.getDestination());
			flight.setRoute(response.getRoute().toString());
			AircraftDE aircraft = this.aircraftRepository.findByModel(response.getAircraft());
			flight.setAircraft(aircraft.getId());
			flight.setAircrafts(response.getOptimalAircrafts().toString());
			flight.setHour(route.getHour());
			flight.setStatus("APROBADO");
			flight.setPeopleEstimate(response.getPeopleEstimate() > 300 ? 300 : response.getPeopleEstimate());
			flight.setFlightRule(route.getFlightRule());
			flight.setFlightType(route.getFlightType());
			flight.setDuration(getDuration(aircraft, response.getDistance()));
			flight.setLandingDate(getLandingDate(date, flight.getHour(), flight.getDuration()).getTime());
			flight.setLandingHour(getLandingHour(flight.getHour(), flight.getDuration()));
			flights.add(flight);
		}
		this.flightRepository.saveAll(flights);
	}

	private int getDuration(AircraftDE aircraft, int distance) {
		return (distance / aircraft.getVelocity()) <= 0 ? 1 : (distance / aircraft.getVelocity());
	}

	private Calendar getLandingDate(Calendar fecha, String hora, int duracion) {
		Calendar date = fecha;
		int hour = Integer.parseInt(hora.substring(0, 2));
		if ((hour + duracion) >= 24) {
			date.add(Calendar.DATE, 1);
		}
		return date;
	}

	private String getLandingHour(String hora, int duracion) {
		int hour = Integer.parseInt(hora.substring(0, 2));
		int h = (hour + duracion);
		if (h >= 24) {
			h = h - 24;
		}
		if ((h + "").length() == 1) {
			return "0" + h + hora.substring(2);
		} else {
			return h + hora.substring(2);
		}
	}

	private String dateToString(Calendar date, String hour) {
		String a = date.get(Calendar.YEAR) + "";
		String month = (date.get(Calendar.MONTH) + 1) + "";
		String mes = month.length() < 2 ? ("0" + month) : month;
		String day = date.get(Calendar.DATE) + "";
		String dia = day.length() < 2 ? ("0" + day) : day;
		String hora = hour.replace(":", "");
		return a + mes + dia + hora;

	}

	private String getDayOfWeek(Calendar c) {
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
