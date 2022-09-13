package com.cybage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.model.FoodMenus;
import com.cybage.service.FoodMenusService;
import com.cybage.service.RestaurantService;

@RestController
@RequestMapping("/food-menu")
@CrossOrigin("http://localhost:4200")
public class FoodMenuController {
	@Autowired
	private FoodMenusService foodMenusService;

	@Autowired
	private RestaurantService restaurantService;

	@PostMapping("/addFoodMenus")
	public ResponseEntity<?> addFoodMenus(@RequestBody FoodMenus foodMenus) {
		FoodMenus newFoodMenu = null;
		try {
			newFoodMenu = foodMenusService.save(foodMenus);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Food Item Not Added!!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(newFoodMenu, HttpStatus.ACCEPTED);
	}

	@PutMapping("/updateFoodMenu/{foodId}")
	public ResponseEntity<?> updateFoodMenu(@PathVariable int foodId, @RequestBody FoodMenus foodMenusRequestDTO) {

		FoodMenus foodItem = foodMenusService.findByFoodId(foodId);
		foodItem.setFoodName(foodMenusRequestDTO.getFoodName());
		foodItem.setFoodCategory(foodMenusRequestDTO.getFoodCategory());
		foodItem.setPrice(foodMenusRequestDTO.getPrice());

		return new ResponseEntity<>(foodMenusService.updateFoodMenu(foodItem, foodId), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{foodId}")
	public ResponseEntity<String> deleteFoodMenuById(@PathVariable int foodId) {
		try {
			foodMenusService.deleteFoodMenu(foodId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Enter Valid Food Menu", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/allFoodMenus")
	public ResponseEntity<List<FoodMenus>> getAllFoodMenus() {
		return new ResponseEntity<List<FoodMenus>>(foodMenusService.findAllFoodMenus(), HttpStatus.OK);
	}

	@GetMapping("/getFoodByRestaurant/{restuarant_id}")
	public ResponseEntity<?> getAllFoodByRestaurant(@PathVariable int restuarant_id) {
		List<FoodMenus> foodItems = foodMenusService.getByRestaurantId(restuarant_id);
		return new ResponseEntity<>(foodItems, HttpStatus.OK);
	}

}
