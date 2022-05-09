package com.springboot.app.business.checkFlight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.business.checkFlight.model.CheckFlightDE;
import com.springboot.app.business.checkFlight.model.CheckFlightTO;
import com.springboot.app.repositories.CheckFlightRepository;

@Service
public class CheckFlightService {
	@Autowired
	private CheckFlightRepository checkFlightRepository;

	public CheckFlightTO getCheckFlight(int idCheckFlight) {
		CheckFlightDE de = checkFlightRepository.findByIdCheckFlight(idCheckFlight);
		return CheckFlightMapper.mapTo(de);
	}

}
