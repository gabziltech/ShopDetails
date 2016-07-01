package com.gabzil.model;

import java.sql.Timestamp;

public class UserDetails {
    private int UserID;
	private int ShopID;
	private String UserName;
	private String MobileNo;
	private boolean ISActive;
	private Timestamp EntryDate;
	
	@Override
	public String toString() {
		return "UserDetails [UserID=" + UserID + ", ShopID=" + ShopID + ", UserName=" + UserName + ", MobileNo="
				+ MobileNo + ", ISActive=" + ISActive + ", EntryDate=" + EntryDate + "]";
	}
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
	public String getMobileNo() {
		return MobileNo;
	}
	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}
	public boolean isISActive() {
		return ISActive;
	}
	public void setISActive(boolean iSActive) {
		ISActive = iSActive;
	}
	public Timestamp getEntryDate() {
		return EntryDate;
	}
	public void setEntryDate(Timestamp entryDate) {
		EntryDate = entryDate;
	}
}
