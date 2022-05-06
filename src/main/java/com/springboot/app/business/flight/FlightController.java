package com.springboot.app.business.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.business.flight.model.FlightTO;

@CrossOrigin("*")
@RestController
@RequestMapping("flight")
public class FlightController {
	@Autowired
	private FlightService service;

	@GetMapping("/get")
	public ResponseEntity<?> getPassenger(@RequestParam(value = "id") int id) {
		try {
			FlightTO flight = this.service.getFlight(id);
			return ResponseEntity.ok(flight);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invalid or empty flight id");
		}
	}

}
