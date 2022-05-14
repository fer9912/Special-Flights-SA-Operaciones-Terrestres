package com.springboot.app.business.flightroutemaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.business.flightroutemaster.model.FlightRouteRequestTO;
import com.springboot.app.business.flightroutemaster.model.FlightRouteResponseTO;
import com.springboot.app.services.ApisRequests;

@CrossOrigin("*")
@RestController
@RequestMapping("flightRouteMaster")
public class FlightRouteMasterController {
	@Autowired
	private FlightRouteMasterService service;

	@Autowired
	private ApisRequests apisRequests;

	@PostMapping("/generateFlightRoute")
	public ResponseEntity<?> generateFlightRoute(@RequestBody FlightRouteRequestTO request) {
		try {
			FlightRouteResponseTO response = this.service.generateFlightRoute(request);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.internalServerError().body("Error to generate flight route: \n" + e.getMessage());
		}
	}

	@GetMapping("")
	public ResponseEntity<?> get() {
		try {
			return ResponseEntity.ok(this.apisRequests.getFlights());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error to generate flight route");
		}
	}

}
