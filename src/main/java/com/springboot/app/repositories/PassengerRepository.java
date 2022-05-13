package com.springboot.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.springboot.app.business.passenger.model.PassengerDE;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerDE, Long>, JpaSpecificationExecutor<PassengerDE> {

	PassengerDE findByIdPassenger(int idPassenger);

	List<PassengerDE> findPassengerByIdFlight(int idFlight);

}