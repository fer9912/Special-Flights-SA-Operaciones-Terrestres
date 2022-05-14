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

	public CheckFlightTO getCheckFlight(String code) {
		CheckFlightDE de = checkFlightRepository.findByCode(code);
		return CheckFlightMapper.mapTo(de);
	}

	public CheckFlightTO saveCheckFlight(CheckFlightTO checkFlight) {
		CheckFlightDE de = CheckFlightMapper.mapDe(checkFlight);
		de = checkFlightRepository.save(de);
		return CheckFlightMapper.mapTo(de);
	}
}
