package com.springboot.app.business.airport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.business.airport.model.AirportDE;
import com.springboot.app.business.airport.model.AirportTO;
import com.springboot.app.repositories.AirportRepository;

@Service
public class AirportService {
	@Autowired
	private AirportRepository airportRepository;

	public List<AirportTO> getAirports() {
		List<AirportDE> des = airportRepository.findAll();
		return AirportMapper.mapTOList(des);
	}

	public AirportTO getByCode(String iata) {
		AirportDE de = airportRepository.findByIata(iata);
		return AirportMapper.mapTO(de);
	}

}
