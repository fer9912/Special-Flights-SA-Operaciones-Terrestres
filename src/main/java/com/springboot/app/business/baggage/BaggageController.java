package com.springboot.app.business.baggage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.business.baggage.model.BaggageRequest;
import com.springboot.app.business.baggage.model.BaggageResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("BaggageInfo")
public class BaggageController {

	@Autowired
	private BaggageService service;

	@PostMapping("/get")
	public ResponseEntity<?> getBaggage(@RequestBody BaggageRequest request) {
		try {
			List<BaggageResponse> response = this.service.getBaggages(request);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.internalServerError().body("Error getting Baggages: \n" + e.getMessage());
		}
	}

}
