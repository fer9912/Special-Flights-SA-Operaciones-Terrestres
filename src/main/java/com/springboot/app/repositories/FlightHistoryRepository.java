package com.springboot.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.springboot.app.business.flightroutemaster.model.FlightHistoryDE;

@Repository
public interface FlightHistoryRepository
		extends JpaRepository<FlightHistoryDE, Long>, JpaSpecificationExecutor<FlightHistoryDE> {

	List<FlightHistoryDE> findByStartAndEnd(String start, String end);
}