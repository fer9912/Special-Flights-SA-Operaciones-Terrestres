package com.springboot.app.business.airport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.business.airport.model.AirportTO;

@CrossOrigin("*")
@RestController
@RequestMapping("airport")
public class AirportController {
	@Autowired
	private AirportService service;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAirports() {
		try {
			List<AirportTO> airports = this.service.getAirports();
			return ResponseEntity.ok(airports);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error to get all airports");
		}
	}
}
