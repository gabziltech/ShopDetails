package com.gabzil.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class SyncDetails {
	private int ShopID;
	private String ShopName;
	private String Address;
	private String City;
	private String Pincode;
	private String PhoneNo;
	private String GeoLocLong;
	private String GeoLocLat;
	private Double PointsBalance;
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
	public String getPhoneNo() {
		return PhoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}
	public String getGeoLocLong() {
		return GeoLocLong;
	}
	public void setGeoLocLong(String geoLocLong) {
		GeoLocLong = geoLocLong;
	}
	public String getGeoLocLat() {
		return GeoLocLat;
	}
	public void setGeoLocLat(String geoLocLat) {
		GeoLocLat = geoLocLat;
	}
	public Double getPointsBalance() {
		return PointsBalance;
	}
	public void setPointsBalance(Double pointsBalance) {
		PointsBalance = pointsBalance;
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
	
}
