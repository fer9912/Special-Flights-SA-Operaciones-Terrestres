package com.springboot.app.business.flight;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.business.flight.model.FlightDE;
import com.springboot.app.business.flight.model.FlightTO;
import com.springboot.app.repositories.FlightRepository;

@Service
public class FlightService {
	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	private FlightMapper flightMapper;

	public FlightTO getFlight(String code) {
		FlightDE de = flightRepository.findByCode(code);
		return flightMapper.mapTo(de);
	}

	public List<FlightTO> getFlights() {
		List<FlightDE> des = flightRepository.findAll();
		return flightMapper.mapTOList(des);
	}

}
