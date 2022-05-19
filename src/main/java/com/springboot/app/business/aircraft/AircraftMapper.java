package com.springboot.app.business.aircraft;

import java.util.List;
import java.util.stream.Collectors;

import com.springboot.app.business.aircraft.model.AircraftDE;
import com.springboot.app.business.aircraft.model.AircraftTO;

public class AircraftMapper {

	public static List<AircraftTO> mapTOList(List<AircraftDE> des) {
		return des.stream().map(AircraftMapper::mapTO).collect(Collectors.toList());
	}

	public static AircraftTO mapTO(AircraftDE de) {
		AircraftTO to = new AircraftTO();
		to.setId(de.getId());
		to.setModel(de.getModel());
		to.setFuelConsumption(de.getFuelConsumption());
		to.setPassengerCapacity(de.getPassengerCapacity());
		to.setWeightTolerance(de.getWeightTolerance());
		to.setLubricantConsumption(de.getLubricantConsumption());
		to.setMaxDistance(de.getMaxDistance());
		to.setCrewMembers(de.getCrewMembers());
		return to;

	}
}
