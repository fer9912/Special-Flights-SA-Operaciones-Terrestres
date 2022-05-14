package com.springboot.app.business.aircraft;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.business.aircraft.model.AircraftDE;
import com.springboot.app.business.aircraft.model.AircraftTO;
import com.springboot.app.repositories.AircraftRepository;

@Service
public class AircraftService {
	@Autowired
	private AircraftRepository aircraftRepository;

	public List<AircraftTO> getAircrafts() {
		List<AircraftDE> des = aircraftRepository.findAll();
		return AircraftMapper.mapTOList(des);
	}

	public AircraftTO getAircraft(int id) {
		AircraftDE de = this.aircraftRepository.findById(id);
		return AircraftMapper.mapTO(de);
	}

}
