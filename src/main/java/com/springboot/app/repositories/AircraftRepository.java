package com.springboot.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.springboot.app.business.aircraft.model.AircraftDE;

@Repository
public interface AircraftRepository extends JpaRepository<AircraftDE, Long>, JpaSpecificationExecutor<AircraftDE> {

	AircraftDE findById(int id);

	AircraftDE findByModel(String model);
}