package com.springboot.app.business.checkFlight;

import java.util.ArrayList;
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

import com.springboot.app.business.checkFlight.model.CheckFlightTO;
import com.springboot.app.services.ApisRequests;
import com.springboot.app.services.model.Flight;

@CrossOrigin("*")
@RestController
@RequestMapping("checkFlight")
public class CheckFlightController {
	@Autowired
	private CheckFlightService service;
	private ApisRequests apisRequests;
	List<Flight> flightsGlobal = new ArrayList<>();

	@GetMapping("/get")
	public ResponseEntity<?> getCheckFlight(@RequestParam(value = "code") String code) {
		try {
			CheckFlightTO checkFlight = this.service.getCheckFlight(code);
			return ResponseEntity.ok(checkFlight);
		} catch (Exception e) {
			System.out.println(e);
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
