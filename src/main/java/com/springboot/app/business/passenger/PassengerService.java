package com.springboot.app.business.passenger;

import java.util.List;

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

	public List<PassengerTO> getPassengersByIdFlight(int idFlight) {
		List<PassengerDE> des = passengerRepository.findByIdFlight(idFlight);
		return PassengerMapper.mapTOList(des);
	}

	public PassengerTO savePassenger(PassengerTO passenger) {
		PassengerDE de = PassengerMapper.mapDE(passenger);
		de = passengerRepository.save(de);
		return PassengerMapper.mapTo(de);
	}

}
