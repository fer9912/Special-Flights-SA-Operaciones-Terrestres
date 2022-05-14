package com.springboot.app.business.airport;

import java.util.List;
import java.util.stream.Collectors;

import com.springboot.app.business.airport.model.AirportDE;
import com.springboot.app.business.airport.model.AirportTO;

public class AirportMapper {

	public static List<AirportTO> mapTOList(List<AirportDE> des) {
		return des.stream().map(AirportMapper::mapTO).collect(Collectors.toList());
	}

	public static AirportTO mapTO(AirportDE de) {
		AirportTO to = new AirportTO();
		to.setCity(de.getCity());
		to.setCountry(de.getCountry());
		to.setIata(de.getIata());
		to.setName(de.getName());
		to.setOaci(de.getOaci());
		to.setState(de.getState());
		to.setLat(de.getLat());
		to.setLon(de.getLon());
		return to;

	}
}
