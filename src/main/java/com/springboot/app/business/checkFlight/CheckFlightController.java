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

	@GetMapping("/getCheckFlightById")
	public ResponseEntity<?> getFlightByCode(@RequestParam("code") String code,
			@RequestParam(value = "fechadespegueestimado") String fechadespegueestimado,
			@RequestParam(value = "horadespegueestimado") String horadespegueestimado) {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			Date date = formato.parse(fechadespegueestimado);
			CheckFlightTO flight = this.service.getCheckFlightById(code, date, horadespegueestimado);
			return ResponseEntity.ok(flight);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return ResponseEntity.badRequest().body("Invalid code - " + e.getMessage());
		}
	}

}
