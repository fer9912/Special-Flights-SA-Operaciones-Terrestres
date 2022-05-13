package com.springboot.app.business.passenger;

import java.util.List;
import java.util.stream.Collectors;

import com.springboot.app.business.passenger.model.PassengerDE;
import com.springboot.app.business.passenger.model.PassengerTO;

public class PassengerMapper {

	public static List<PassengerTO> mapTOList(List<PassengerDE> des) {
		return des.stream().map(PassengerMapper::mapTo).collect(Collectors.toList());
	}

	public static PassengerTO mapTo(PassengerDE de) {
		PassengerTO to = new PassengerTO();
		to.setName(de.getName());
		to.setIdPassenger(de.getIdPassenger());
		to.setDocType(de.getDocType());
		to.setDocNumber(de.getDocNumber());
		to.setNationality(de.getNationality());
		to.setAge(de.getAge());
		to.setSex(de.getSex());
		to.setClassFlight(de.getClassFlight());
		to.setMenuType(de.getMenuType());
		to.setMedicalCondition(de.getMedicalCondition());
		to.setBaggage(de.getBaggage());
		to.setIdFlight(de.getIdFlight());
		to.setStatus(de.getStatus());
		return to;
	}

	public static PassengerDE mapDE(PassengerTO to) {
		PassengerDE de = new PassengerDE();
		de.setName(to.getName());
		de.setIdPassenger(to.getIdPassenger());
		de.setDocType(to.getDocType());
		de.setDocNumber(to.getDocNumber());
		de.setNationality(to.getNationality());
		de.setAge(to.getAge());
		de.setSex(to.getSex());
		de.setClassFlight(to.getClassFlight());
		de.setMenuType(to.getMenuType());
		de.setMedicalCondition(to.getMedicalCondition());
		de.setBaggage(to.getBaggage());
		de.setIdFlight(to.getIdFlight());
		de.setStatus(to.getStatus());
		return de;
	}

}
