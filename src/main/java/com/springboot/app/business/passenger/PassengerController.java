package com.springboot.app.business.passenger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.business.passenger.model.PassengerTO;

@CrossOrigin("*")
@RestController
@RequestMapping("passenger")
public class PassengerController {
	@Autowired
	private PassengerService service;

	@GetMapping("/get")
	public ResponseEntity<?> getPassenger(@RequestParam(value = "id") int id) {
		try {
			PassengerTO passenger = this.service.getPassenger(id);
			return ResponseEntity.ok(passenger);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invalid or empty passenger id");
		}
	}

	@GetMapping("/get")
	public ResponseEntity<?> getPassengersByIdFlight(@RequestParam(value = "idFlight") int idFlight) {
		try {
			List<PassengerTO> passengers = this.service.getPassengersByIdFlight(idFlight);
			return ResponseEntity.ok(passengers);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error to get passengers by flight");
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody PassengerTO checkFlight) {
		try {
			PassengerTO response = this.service.savePassenger(checkFlight);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error to save passenger");
		}
	}

}
