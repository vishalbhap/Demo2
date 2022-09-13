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

import com.cybage.exception.CustomException;
import com.cybage.model.Restaurant;
import com.cybage.service.EmailSenderService;
import com.cybage.service.FoodMenusService;
import com.cybage.service.RestaurantService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/restaurant")
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private FoodMenusService foodMenuService;

	@Autowired
	private EmailSenderService senderService;

	@PostMapping("/addRestaurant")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) throws CustomException {
		Restaurant newRestaurant = null;
		System.out.println("Restaurant det " + restaurant);
		try {
			int distance = (int) (Math.floor(Math.random() * 5) * 5);
			restaurant.setDistance(Integer.toString(distance));
			System.out.println("Restaurant after setting distance " + restaurant);
			newRestaurant = restaurantService.save(restaurant);
			senderService.sendEmail(newRestaurant.getRestaurantEmail(), newRestaurant.getRestaurantPassword());
		} catch (Exception e) {
			throw new CustomException("Restaurant Not added");
		}
		return new ResponseEntity<Restaurant>(newRestaurant, HttpStatus.OK);
	}

	@PutMapping("/updateRestaurant/{restaurantId}")
	public ResponseEntity<?> updateRestaurant(@PathVariable int restaurantId,
			@RequestBody Restaurant updatedrestaurant) {
		Restaurant restaurant = restaurantService.findByRestaurantId(restaurantId);
		restaurant.setRestaurantName(updatedrestaurant.getRestaurantName());
		restaurant.setRestaurantUserName(updatedrestaurant.getRestaurantUserName());
		return new ResponseEntity<>(restaurantService.updateRestaurant(restaurant), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteRestaurant/{restaurantId}")
	public ResponseEntity<String> deleteRestaurant(@PathVariable int restaurantId) throws CustomException {
		restaurantService.deleteByRestaurantId(restaurantId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/allRestaurant")
	public ResponseEntity<List<Restaurant>> getAllRestaurant() {
		return new ResponseEntity<List<Restaurant>>(restaurantService.findAllRestaurants(), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> restaurantLogin(@RequestBody Restaurant restaurant) throws CustomException {
		System.out.println("vishalRestLogin1");
		Restaurant loginRestaurant = restaurantService.findByRestaurantEmailAndRestaurantPassword(
				restaurant.getRestaurantEmail(), restaurant.getRestaurantPassword());
		if (loginRestaurant != null) {

			return new ResponseEntity<>(loginRestaurant, HttpStatus.OK);
		}else {
			Restaurant dummy = new Restaurant();
			dummy.setRestaurantPassword("147852693");
			return new ResponseEntity<>(dummy, HttpStatus.OK);
		}
//		throw new CustomException("Email or password is wrong");
	}
}
