package com.springboot.app.business.checkCommissariat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.business.checkCommissariat.model.CheckCommissariatTO;

@CrossOrigin("*")
@RestController
@RequestMapping("checkCommissariat")
public class CheckCommissariatController {
	@Autowired
	private CheckCommissariatService service;

	@GetMapping("/get")
	public ResponseEntity<?> getCheckCommissariat(@RequestParam(value = "id") String id) {
		try {
			CheckCommissariatTO checkCommissariat = this.service.getCheckCommissariat(id);
			return ResponseEntity.ok(checkCommissariat);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().body("Invalid or empty flight code");
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody CheckCommissariatTO checkCommissariat) {
		try {
			CheckCommissariatTO response = this.service.saveCheckCommissariat(checkCommissariat);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error to save checkCommissariat");
		}
	}

}
