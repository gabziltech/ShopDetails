package com.gabzil.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ShopsDetails {
	private int ShopID;
	private String ShopName;
	private String Address;
	private String City;
	private String Pincode;
	private int UserID;
	private Timestamp EntryDate;
	private ArrayList<UserDetails> AllUser;
	private ArrayList<CustomerDetails> AllCustomer;
	
	public int getShopID() {
		return ShopID;
	}
	public void setShopID(int shopID) {
		ShopID = shopID;
	}
	public String getShopName() {
		return ShopName;
	}
	public void setShopName(String shopName) {
		ShopName = shopName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getPincode() {
		return Pincode;
	}
	public void setPincode(String pincode) {
		Pincode = pincode;
	}
	public Timestamp getEntryDate() {
		return EntryDate;
	}
	public void setEntryDate(Timestamp entryDate) {
		EntryDate = entryDate;
	}
	public ArrayList<UserDetails> getAllUser() {
		return AllUser;
	}
	public void setAllUser(ArrayList<UserDetails> allUser) {
		AllUser = allUser;
	}
	public ArrayList<CustomerDetails> getAllCustomer() {
		return AllCustomer;
	}
	public void setAllCustomer(ArrayList<CustomerDetails> allCustomer) {
		AllCustomer = allCustomer;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
}
