package com.springboot.app.business.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.business.passenger.model.PassengerDE;
import com.springboot.app.business.passenger.model.PassengerTO;
import com.springboot.app.repositories.PassengerRepository;

@Service
public class PassengerService {
	@Autowired
	private PassengerRepository passengerRepository;

	public PassengerTO getPassenger(int idPassenger) {
		PassengerDE de = passengerRepository.findByIdPassenger(idPassenger);
		return PassengerMapper.mapTo(de);
	}

	public PassengerTO savePassenger(PassengerTO passenger) {
		PassengerDE de = PassengerMapper.mapDe(passenger);
		de = passengerRepository.save(de);
		return PassengerMapper.mapTo(de);
	}

}
