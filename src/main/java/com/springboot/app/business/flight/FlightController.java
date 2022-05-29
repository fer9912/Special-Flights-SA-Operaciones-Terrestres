package com.springboot.app.business.flight;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	public ResponseEntity<?> getFlight(@RequestParam(value = "code") String code) {
		try {
			FlightTO flight = this.service.getFlight(code);
			return ResponseEntity.ok(flight);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invalid or empty flight code");
		}
	}

	@GetMapping("/getByDate")
	public ResponseEntity<?> getFlightByDate(@RequestParam(value = "date") String fecha) {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			Date date = formato.parse(fecha);
			List<FlightTO> flights = this.service.getFlightByDate(date);
			return ResponseEntity.ok(flights);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body("Invalid date - " + e.getMessage());
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getFlights() {
		try {
			List<FlightTO> flights = this.service.getFlights();
			return ResponseEntity.ok(flights);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error to get flights");
		}
	}

}
