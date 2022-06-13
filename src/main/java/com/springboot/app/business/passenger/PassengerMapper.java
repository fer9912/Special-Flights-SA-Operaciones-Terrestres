package com.springboot.app.business.passenger;

import java.util.List;
import java.util.stream.Collectors;

import com.springboot.app.business.passenger.model.PassengerDE;
import com.springboot.app.business.passenger.model.PassengerTO;

public class PassengerMapper {

	public static PassengerTO mapTo(PassengerDE de) {
		PassengerTO to = new PassengerTO();
		to.setName(de.getName());
		to.setIdPassenger(de.getIdPassenger());
		to.setDocType(de.getDocType());
		to.setDocNumber(de.getDocNumber());
		to.setNationality(de.getNationality());
		return to;
	}

	public static PassengerDE mapDE(PassengerTO to) {
		PassengerDE de = new PassengerDE();
		de.setName(to.getName());
		de.setIdPassenger(to.getIdPassenger());
		de.setDocType(to.getDocType());
		de.setDocNumber(to.getDocNumber());
		de.setNationality(to.getNationality());
		return de;
	}

	public static List<PassengerTO> mapTOList(List<PassengerDE> des) {
		return des.stream().map(d -> mapTo(d)).collect(Collectors.toList());
	}

}
