package com.springboot.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.springboot.app.business.flight.model.FlightDE;

@Repository
public interface FlightRepository extends JpaRepository<FlightDE, Long>, JpaSpecificationExecutor<FlightDE> {

	FlightDE findByIdFlight(int idFlight);

}