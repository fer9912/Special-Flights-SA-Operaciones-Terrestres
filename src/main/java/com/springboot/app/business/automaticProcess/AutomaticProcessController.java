package com.springboot.app.business.automaticProcess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("automaticProcess")
public class AutomaticProcessController {
	@Autowired
	private AutomaticProcessService service;

	@GetMapping("/generateFlights")
	public ResponseEntity<?> generateFlights() {
		try {
			this.service.generateFlights();
			return ResponseEntity.ok("Generate Flights process finished successfully");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.internalServerError().body("Error to generate flights");
		}
	}
}
