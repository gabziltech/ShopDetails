package com.gabzil.model;

import java.sql.Timestamp;

public class CodeDetails {
	private int CodeID;
	private int ShopID;
	private int UserID;
	private int Code;
	private Timestamp EntryDate;
	public int getCodeID() {
		return CodeID;
	}
	public void setCodeID(int codeID) {
		CodeID = codeID;
	}
	public int getShopID() {
		return ShopID;
	}
	public void setShopID(int shopID) {
		ShopID = shopID;
	}
	public int getCode() {
		return Code;
	}
	public void setCode(int code) {
		Code = code;
	}
	public Timestamp getEntryDate() {
		return EntryDate;
	}
	public void setEntryDate(Timestamp entryDate) {
		EntryDate = entryDate;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
}
