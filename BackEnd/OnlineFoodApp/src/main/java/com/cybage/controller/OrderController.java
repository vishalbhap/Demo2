package com.cybage.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.dto.OrderContentDTO;
import com.cybage.exception.CustomException;
import com.cybage.model.Restaurant;
import com.cybage.model.UserOrder;
import com.cybage.service.RestaurantService;
import com.cybage.service.UserOrderService;
import com.cybage.service.UserService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/order")
public class OrderController {

	@Autowired
	UserOrderService userOrderService;

	@Autowired
	UserService userService;

	@Autowired
	RestaurantService restaurantService;

	@GetMapping("/getAllOrdersForRestaurant/{restaurantId}")
	public ResponseEntity<?> getAllOrdersForRestaurant(@PathVariable int restaurantId) {
		Restaurant restaurant = restaurantService.findByRestaurantId(restaurantId);
		List<UserOrder> orders = userOrderService.getAllOrders(restaurant);
		System.out.println(orders);
		if (orders != null) {
			return new ResponseEntity<>(orders, HttpStatus.OK);
		}
		System.out.println("Vishal4");
		return new ResponseEntity<>("OrderList is Empty", HttpStatus.NOT_ACCEPTABLE);
	}

	@GetMapping("/getAllOrdersForUser/{userId}")
	public ResponseEntity<?> getAllOrdersForUser(@PathVariable int userId) throws CustomException {

		List<UserOrder> orders = userService.findByUser(userId);

		if (orders.size() == 0) {
			throw new CustomException("no order found");
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@PostMapping("/place-order")
	public ResponseEntity<?> placeOrder(@RequestBody OrderContentDTO orderContent) {
		UserOrder userOrder = new UserOrder();
		UserOrder order = userOrderService.save(userOrder, orderContent.getTotal(),
				orderContent.getFoodMenus().get(0).getRestaurant(), orderContent.getUser());
//		smsService.sendSMS(orderContent.getUser().getUserMobileNo()); //sending sms
		// on user mobile number
//		for (FoodMenus foodItem : orderContent.getFoodMenus()) {
//			orderService.saveFoodItems(foodItem, order, foodItem.getQuantity());
//		}
//		logger.info("Customer "+orderContent.getUser().getUserName()+"Placed Order");
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@PostMapping("/place-orderSimple")
	public ResponseEntity<?> placeOrderSimple(@RequestBody UserOrder userOrderSimple) {
		System.out.println("VishalOrderSimple1");
		UserOrder order = userOrderService.saveOrderSimple(userOrderSimple);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@PutMapping("/updateOrderStatusAccept/{orderId}")
	public ResponseEntity<?> acceptOrder(@PathVariable int orderId) {
		UserOrder userOrder = userOrderService.findUserOrderByOrderId(orderId);
		userOrder.setOrderStatus("Accepted");
		userOrderService.save(userOrder);
		return new ResponseEntity<>("updated", HttpStatus.OK);
	}

	@PutMapping("/updateOrderStatusReject/{orderId}")
	public ResponseEntity<?> rejectOrder(@PathVariable int orderId) {
		UserOrder userOrder = userOrderService.findUserOrderByOrderId(orderId);
		userOrder.setOrderStatus("Rejected");
		userOrderService.save(userOrder);
		return new ResponseEntity<>("updated", HttpStatus.OK);
	}

	@PutMapping("/updateOrderStatusCancelByUser/{orderId}")
	public ResponseEntity<?> cancelOrderByUser(@PathVariable int orderId) {
		UserOrder userOrder = userOrderService.findUserOrderByOrderId(orderId);
		userOrder.setOrderStatus("Cancelled");
		userOrderService.save(userOrder);
		return new ResponseEntity<>("updated", HttpStatus.OK);
	}

	@GetMapping("/check-order-status-api/{orderId}")
	public ResponseEntity<?> checkOrderStatusAPI(@PathVariable int orderId) {
		UserOrder order = userOrderService.findUserOrderByOrderId(orderId);
		boolean flag = false;
		LocalDateTime now = LocalDateTime.now();
		LocalTime orderCancelTime = order.getOrderTime().toLocalTime().plusMinutes(15);
		if (order.getOrderTime().toLocalDate().compareTo(now.toLocalDate()) == 0) {
			if (now.toLocalTime().compareTo(orderCancelTime) <= 0) {
				flag = true;
			}
		}
		System.out.println(flag);
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}

}
