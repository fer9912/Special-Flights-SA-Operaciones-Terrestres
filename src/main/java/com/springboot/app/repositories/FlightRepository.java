package com.springboot.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.app.business.flight.model.FlightDE;

@Repository
public interface FlightRepository extends JpaRepository<FlightDE, Long>, JpaSpecificationExecutor<FlightDE> {

	FlightDE findByCode(String code);

	@Query("select distinct f FROM FlightDE f where f.status not in ('CANCELADO','FINALIZADO')")
	List<FlightDE> findByFlightStatus();

}