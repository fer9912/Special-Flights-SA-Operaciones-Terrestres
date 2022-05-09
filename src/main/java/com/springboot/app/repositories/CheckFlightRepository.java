package com.springboot.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.springboot.app.business.checkFlight.model.CheckFlightDE;

@Repository
public interface CheckFlightRepository
		extends JpaRepository<CheckFlightDE, Long>, JpaSpecificationExecutor<CheckFlightDE> {

	CheckFlightDE findByIdCheckFlight(int idCheckFlight);

}