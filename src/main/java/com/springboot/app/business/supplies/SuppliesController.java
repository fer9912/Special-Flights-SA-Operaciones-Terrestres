package com.springboot.app.business.supplies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.business.supplies.model.SuppliesTO;

@CrossOrigin("*")
@RestController
@RequestMapping("supplies")
public class SuppliesController {
	@Autowired
	private SuppliesService service;

	@GetMapping("/get")
	public ResponseEntity<?> getCheckFlight(@RequestParam(value = "code") String code) {
		try {
			SuppliesTO supplies = this.service.getSupplies(code);
			return ResponseEntity.ok(supplies);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().body("Invalid or empty flight code");
		}
	}

}
