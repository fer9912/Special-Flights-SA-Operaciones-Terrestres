package com.springboot.app.business.parameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.business.parameters.model.ParametersTO;

@CrossOrigin("*")
@RestController
@RequestMapping("parameters")
public class ParametersController {
	@Autowired
	private ParametersService service;

	@GetMapping("/get")
	public ResponseEntity<?> getParameters() {
		try {
			ParametersTO parameters = this.service.getParameters();
			return ResponseEntity.ok(parameters);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error to get parameters");
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ParametersTO parameters) {
		try {
			ParametersTO response = this.service.saveParameters(parameters);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error to save parameters");
		}
	}

}
