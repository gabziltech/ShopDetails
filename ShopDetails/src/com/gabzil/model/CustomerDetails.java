package com.gabzil.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CustomerDetails {
	private int ShopID;
	private int CustomerID;
	private String ShopName;
	private String CustomerName;
	private boolean IsActive;
	private String Address;
	private String Building;
	private String Area;
	private String City;
	private String Amount;
	private String CreditDays;
	private String OutStanding;
	private Timestamp EntryDate;
	private ArrayList<ContactDetails> AllContact; 
	
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public int getShopID() {
		return ShopID;
	}
	public void setShopID(int shopID) {
		ShopID = shopID;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getBuilding() {
		return Building;
	}
	public void setBuilding(String building) {
		Building = building;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	
	public ArrayList<ContactDetails> getAllContact() {
		return AllContact;
	}
	public void setAllContact(ArrayList<ContactDetails> allContact) {
		AllContact = allContact;
	}
	public Timestamp getEntryDate() {
		return EntryDate;
	}
	public void setEntryDate(Timestamp entryDate) {
		EntryDate = entryDate;
	}
	public String getShopName() {
		return ShopName;
	}
	public void setShopName(String shopName) {
		ShopName = shopName;
	}
	public String getCreditDays() {
		return CreditDays;
	}
	public void setCreditDays(String creditDays) {
		CreditDays = creditDays;
	}
	public boolean getIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
	public String getOutStanding() {
		return OutStanding;
	}
	public void setOutStanding(String outStanding) {
		OutStanding = outStanding;
	}
	
}
