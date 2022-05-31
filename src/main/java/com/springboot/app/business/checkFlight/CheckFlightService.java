package com.springboot.app.business.checkFlight;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.business.checkFlight.model.CheckFlightDE;
import com.springboot.app.business.checkFlight.model.CheckFlightTO;
import com.springboot.app.repositories.CheckFlightRepository;
import com.springboot.app.services.ApisRequests;
import com.springboot.app.services.model.Flight;

@Service
public class CheckFlightService {
	@Autowired
	private CheckFlightRepository checkFlightRepository;
	List<Flight> flightsGlobal = new ArrayList<>();

	@Autowired
	private ApisRequests apisRequests;

	public List<Flight> getFlight(Date date) {
		flightsGlobal = this.apisRequests.getFlights();
		String a = date.getDate() + "-" + date.getMonth();
		System.out.println(a);
		return flightsGlobal.stream().filter(
				v -> a.equals(v.getFechadespegueestimado().getDate() + "-" + v.getFechadespegueestimado().getMonth()))
				.collect(Collectors.toList());
	}

	public CheckFlightTO getCheckFlight(int id) {
		CheckFlightDE de = checkFlightRepository.findByIdFlight(id);
		return CheckFlightMapper.mapTo(de);
	}

	public CheckFlightTO saveCheckFlight(CheckFlightTO checkFlight) {
		CheckFlightDE de = CheckFlightMapper.mapDe(checkFlight);
		de = checkFlightRepository.save(de);
		return CheckFlightMapper.mapTo(de);
	}
}
