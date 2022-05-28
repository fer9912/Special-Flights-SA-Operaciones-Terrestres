package com.springboot.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.springboot.app.business.automaticProcess.model.RouteDE;

@Repository
public interface RouteRepository extends JpaRepository<RouteDE, Long>, JpaSpecificationExecutor<RouteDE> {

	List<RouteDE> findByDay(String dayW);
}