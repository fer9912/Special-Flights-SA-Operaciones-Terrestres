package com.springboot.app.business.airport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.business.airport.model.AirportDE;
import com.springboot.app.business.airport.model.AirportTO;
import com.springboot.app.repositories.AirportRepository;
import com.springboot.app.utils.Util;

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

	public List<AirportTO> getAirportsNear(String o) {
		AirportTO origin = this.getByCode(o);
		List<AirportDE> des = airportRepository.findAll();
		List<AirportTO> airports = AirportMapper.mapTOList(des);
		List<AirportTO> ret = new ArrayList<>();
		for (AirportTO destination : airports) {
			if (this.getDistance(origin, destination) < 301 && !destination.getIata().equals(o)) {
				ret.add(destination);
			}
		}
		return ret;
	}

	private int getDistance(AirportTO start, AirportTO end) {
		return Util.getDistance(start, end);
	}

	public List<AirportTO> getAirportsToUnmmaned() {
		List<AirportDE> des = airportRepository.findAll();
		List<AirportTO> airports = AirportMapper.mapTOList(des);
		List<AirportTO> ret = new ArrayList<>();
		for (AirportTO destination : airports) {
			List<AirportTO> aeropuertos = this.getAirportsNear(destination.getIata());
			if (aeropuertos != null && !aeropuertos.isEmpty()) {
				ret.add(destination);
			}
		}
		return ret;
	}
}
