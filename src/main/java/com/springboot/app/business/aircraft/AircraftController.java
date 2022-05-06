package com.springboot.app.business.aircraft;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.business.aircraft.model.AircraftTO;

@CrossOrigin("*")
@RestController
@RequestMapping("aircraft")
public class AircraftController {
	@Autowired
	private AircraftService service;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAircrafts() {
		try {
			List<AircraftTO> aircrafts = this.service.getAircrafts();
			return ResponseEntity.ok(aircrafts);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error to get all aircrafts");
		}
	}
}
