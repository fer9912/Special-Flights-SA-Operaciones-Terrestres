package com.springboot.app.business.checkFlight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.business.checkFlight.model.CheckFlightTO;

@CrossOrigin("*")
@RestController
@RequestMapping("checkFlight")
public class CheckFlightController {
	@Autowired
	private CheckFlightService service;

	@GetMapping("/get")
	public ResponseEntity<?> getCheckFlight(@RequestParam(value = "id") int id) {
		try {
			CheckFlightTO checkFlight = this.service.getCheckFlight(id);
			return ResponseEntity.ok(checkFlight);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invalid or empty checkFlight id");
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody CheckFlightTO checkFlight) {
		try {
			CheckFlightTO response = this.service.saveCheckFlight(checkFlight);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error to save checkFlight");
		}
	}
}
