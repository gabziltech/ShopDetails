package com.gabzil.model;

import java.sql.Timestamp;

public class UserDetails {
	private int ShopID;
	private int UserID;
	private String UserName;
	private String UserType;
	private String MobileNo;
	private boolean IsActive;
	private Timestamp EntryDate;
	
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public int getShopID() {
		return ShopID;
	}
	public void setShopID(int shopID) {
		ShopID = shopID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserType() {
		return UserType;
	}
	public void setUserType(String userType) {
		UserType = userType;
	}
	public String getMobileNo() {
		return MobileNo;
	}
	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}
	
	public Timestamp getEntryDate() {
		return EntryDate;
	}
	public void setEntryDate(Timestamp entryDate) {
		EntryDate = entryDate;
	}
	public boolean getIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
}
