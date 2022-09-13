package com.cybage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender mailSender;

	public int sendOTP(String email) {

		int otp = 0;
		
		try {
			SimpleMailMessage message=new SimpleMailMessage();
			message.setFrom("Trng2@evolvingsols.com");
			message.setTo(email);
			otp=(int)(Math.floor(Math.random()*1000000));
			message.setText("Online Food Delivery App \n"
					+ "Your OTP is : "+otp);
			message.setSubject("OTP for login");			
			mailSender.send(message);
			System.out.println("OTP send through mail");
		
		} catch (MailException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return otp;
	}
	
	public void sendEmail(String email,String password) {
		try {
			SimpleMailMessage message=new SimpleMailMessage();
			message.setFrom("Trng2@evolvingsols.com");
			message.setTo(email);
			message.setText("Congrats... Your restaurant is live on our website!!!\n"
					+ "Your login details are as here: \n"
					+ "Email:" +email+"\n"+ "Password:" +password);
			message.setSubject("Restaurant Owner Crediantials");
			mailSender.send(message);
		
		} catch (MailException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}	
	
	

}
