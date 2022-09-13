package com.cybage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.service.ExportService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ExportService exportService;

	@GetMapping("/export-user-details")
	public ResponseEntity<?> exportUserDetails() {
		exportService.printUser();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/export-foodItem-details")
	public ResponseEntity<?> exportFoodItemDetails() {
		exportService.printFoodItemData();
		;
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/export-restaurant-details")
	public ResponseEntity<?> exportRestaurantDetails() {
		exportService.printRestaurantData();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
