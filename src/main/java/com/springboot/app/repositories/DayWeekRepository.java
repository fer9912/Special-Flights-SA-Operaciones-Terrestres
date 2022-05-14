package com.springboot.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.springboot.app.business.flightroutemaster.model.DayWeekDE;

@Repository
public interface DayWeekRepository extends JpaRepository<DayWeekDE, Long>, JpaSpecificationExecutor<DayWeekDE> {

}