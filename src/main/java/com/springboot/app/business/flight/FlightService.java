package com.springboot.app.business.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.business.flight.model.FlightDE;
import com.springboot.app.business.flight.model.FlightTO;
import com.springboot.app.repositories.FlightRepository;

@Service
public class FlightService {
	@Autowired
	private FlightRepository flightRepository;

	public FlightTO getFlight(int idFlight) {
		FlightDE de = flightRepository.findByIdFlight(idFlight);
		return FlightMapper.mapTo(de);
	}

}
