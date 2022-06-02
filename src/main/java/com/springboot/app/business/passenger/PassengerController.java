package com.springboot.app.business.passenger;

import java.util.List;

import org.apache.log4j.Logger;
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
import com.springboot.app.reports.ReportController;

@CrossOrigin("*")
@RestController
@RequestMapping("passenger")
public class PassengerController {
	@Autowired
	private PassengerService service;
	static Logger log = Logger.getLogger(ReportController.class);

	@GetMapping("/get")
	public ResponseEntity<?> getPassenger(@RequestParam(value = "id") int id) {
		try {
			PassengerTO passenger = this.service.getPassenger(id);
			return ResponseEntity.ok(passenger);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invalid or empty passenger id");
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getPassengers() {
		try {
			List<PassengerTO> passengers = this.service.getAll();
			return ResponseEntity.ok(passengers);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error to get  passengers");
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody PassengerTO passenger) {
		try {
			PassengerTO response = this.service.savePassenger(passenger);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error to save passenger");
		}
	}

}
