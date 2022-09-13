package com.cybage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address {
	@Id
	@GeneratedValue
	private int addressId;
	private String area;
	private String street;
	private String pincode;
	@OneToOne(mappedBy = "address")
	private User user;

	public Address() {
		
	}
	public Address(int addressId, String area, String street, String pincode) {
		this.addressId = addressId;
		this.area = area;
		this.street = street;
		this.pincode = pincode;
	}
	
	public Address(String area, String street, String pincode) {
		this.area = area;
		this.street = street;
		this.pincode = pincode;
		
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
}
