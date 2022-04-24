package com.springboot.app.business.passenger;

import com.springboot.app.business.passenger.model.PassengerDE;
import com.springboot.app.business.passenger.model.PassengerTO;

public class PassengerMapper {

	public static PassengerTO mapTo(PassengerDE de) {
		PassengerTO to = new PassengerTO();
		to.setName(de.getName());
		to.setIdPassenger(de.getIdPassenger());
		return to;
	}

}
