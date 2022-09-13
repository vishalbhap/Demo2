package com.cybage.controller;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.cybage.exception.CustomException;
import com.cybage.model.User;
import com.cybage.service.EmailSenderService;
import com.cybage.service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/food-delivery")
public class UserController {
	
	static Logger logger=LogManager.getLogger(UserController.class);

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailSenderService senderService;

	
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody User user){
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/allUser")
	public ResponseEntity<List<User>> getAllUser() {
		System.out.println("vishal1");
		List<User> list =userService.findAllUsers();
		System.out.println("vishal2");
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) throws CustomException{
		System.out.println("vishal3");
		User userLogin = userService.findByUserEmail(user.getUserEmail());
		if(userLogin == null) {
			System.out.println("vishalLogin6");
			logger.error("customer" +userLogin.getUserName()+"Logged in with wrong crediantials");
			return new ResponseEntity<>("Email or password is wrong", HttpStatus.NOT_FOUND);
		}else  {
			System.out.println("vishalLogin4");	
			logger.info("Customer " + userLogin.getUserName() + " logged in");
//			userLogin.setAttemptsCount(0);
//			userService.saveUser(userLogin);
			return new ResponseEntity<>(userLogin, HttpStatus.OK);			
		}			
	} 
	
	@PostMapping("/loginOtp")
	public ResponseEntity<?> loginOtp(@RequestBody User user) throws CustomException{
		User userOtp = userService.findByUserEmail(user.getUserEmail());
		if(userOtp == null) {
			System.out.println("vishalLoginOtp6");
			return new ResponseEntity<>("Email is wrong or not present", HttpStatus.NOT_FOUND);
		}else  {
			System.out.println("vishalLoginOtp4");	
			int otpCheck=senderService.sendOTP(user.getUserEmail());
			System.out.println(otpCheck);			
			return new ResponseEntity<>(otpCheck, HttpStatus.OK);			
		}			
	}
	
	@PostMapping("/increaseloginAttempts")
	public ResponseEntity<?> increaseloginAttempts(@RequestBody User user) throws CustomException{
		System.out.println("vishalLoginAttempt1");
		User userLogin = userService.findByUserEmail(user.getUserEmail());
		userLogin.setAttemptsCount(userLogin.getAttemptsCount()+1);
		userService.saveUser(userLogin);
		return new ResponseEntity<>(userLogin, HttpStatus.OK);		
			
	}
	
	@PostMapping("/setAttemptsToZero")
	public ResponseEntity<?> setAttemptsToZero(@RequestBody User user) throws CustomException{
		System.out.println("vishalSetAttempt1");
		User userLogin = userService.findByUserEmail(user.getUserEmail());
		userLogin.setAttemptsCount(0);
		userService.saveUser(userLogin);
		return new ResponseEntity<>(userLogin, HttpStatus.OK);		
			
	}
	
	@PostMapping("/unblockAccount")
	public ResponseEntity<?> unblockUser(@RequestBody User user) throws CustomException{
		System.out.println("vishalUnBlockt1");
		User userLogin = userService.findByUserEmail(user.getUserEmail());
		userLogin.setAttemptsCount(0);
		userService.saveUser(userLogin);
		return new ResponseEntity<>(userLogin, HttpStatus.OK);		
	}
	
	@PostMapping("/blockAccount")
	public ResponseEntity<?> blockUser(@RequestBody User user) throws CustomException{
		System.out.println("vishalUnBlockt1");
		User userLogin = userService.findByUserEmail(user.getUserEmail());
		userLogin.setAttemptsCount(3);
		userService.saveUser(userLogin);
		return new ResponseEntity<>(userLogin, HttpStatus.OK);		
	}
	
	

	


	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> userUpdate(@PathVariable int id, @RequestBody User user){

		return new ResponseEntity<>(userService.saveUpdatedUser(user, id), HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/updatePassword/{id}")
	public ResponseEntity<?> userUpdatePassword(@PathVariable int id, @RequestBody User user){

		return new ResponseEntity<>(userService.saveUpdatedPasswordUser(user, id), HttpStatus.ACCEPTED);
	}


	
}
