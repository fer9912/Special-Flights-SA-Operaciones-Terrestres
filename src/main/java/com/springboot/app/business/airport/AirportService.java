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
		System.out.println(":)");
		List<AirportDE> des = airportRepository.findAll();
		System.out.println(des.toString());
		return AirportMapper.mapTOList(des);
	}

}
