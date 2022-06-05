package com.springboot.app.business.checkFlight;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.springboot.app.services.model.Flight;

@CrossOrigin("*")
@RestController
@RequestMapping("checkFlight")
public class CheckFlightController {
	@Autowired
	private CheckFlightService service;

	@GetMapping("/get")
	public ResponseEntity<?> getCheckFlight(@RequestParam(value = "id") String id) {
		try {
			CheckFlightTO checkFlight = this.service.getCheckFlight(id);
			return ResponseEntity.ok(checkFlight);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().body("Invalid or empty Flight id");
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

	@GetMapping("/getFlightByDate")
	public ResponseEntity<?> getFlightByDate(@RequestParam(value = "date") String fecha) {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			Date date = formato.parse(fecha);
			List<Flight> flights = this.service.getFlight(date);

			return ResponseEntity.ok(flights);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body("Invalid date - " + e.getMessage());
		}
	}

}
