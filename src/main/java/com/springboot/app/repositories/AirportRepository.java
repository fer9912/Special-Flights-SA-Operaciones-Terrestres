package com.springboot.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.springboot.app.business.airport.model.AirportDE;

@Repository
public interface AirportRepository extends JpaRepository<AirportDE, Long>, JpaSpecificationExecutor<AirportDE> {

	AirportDE findByIata(String iata);
}