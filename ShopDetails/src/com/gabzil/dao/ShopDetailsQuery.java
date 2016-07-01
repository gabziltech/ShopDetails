package com.gabzil.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.gabzil.model.ShopUserDetails;
import com.gabzil.model.ShopsDetails;
import com.gabzil.model.UserDetails;

public class ShopDetailsQuery {
	ShopsDetails shop;
	ShopUserDetails shopuser;
	PreparedStatement ps;
	CallableStatement cs;
	RetailDB db;
	Connection con;

	public ShopDetailsQuery() {
		try {

			db = new RetailDB();
			con = db.getDBConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ShopsDetails> getData(int id) throws Exception {
		ArrayList<ShopsDetails> feedData = new ArrayList<ShopsDetails>();
		try {
			ps = con.prepareStatement(
					"SELECT ShopID,ShopName,Address,City,Pincode,PhoneNo,GeoLocLong,GeoLocLat,PointsBalance,EntryDate FROM RT_ShopsDetails where ShopID=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				shop = new ShopsDetails();
				shop.setShopID(rs.getInt("ShopID"));
				shop.setShopName(rs.getString("ShopName"));
				shop.setAddress(rs.getString("Address"));
				shop.setCity(rs.getString("City"));
				shop.setPincode(rs.getString("Pincode"));
				shop.setPhoneNo(rs.getString("PhoneNo"));
				shop.setGeoLocLong(rs.getString("GeoLocLong"));
				shop.setGeoLocLat(rs.getString("GeoLocLat"));
				shop.setPointsBalance(rs.getDouble("PointsBalance"));
				shop.setEntryDate(rs.getTimestamp("EntryDate"));
				feedData.add(shop);
			}
			return feedData;
		} catch (Exception e) {
			throw e;
		} finally {
			con.close();
		}
	}

	public boolean putData(ShopsDetails shop) throws Exception {
		try {
			if (shop.getShopID() > 0) {
				ps = con.prepareStatement(
						"update RT_ShopsDetails set ShopName=?, Address=?, City=?, Pincode=? where ShopID=?");
				ps.setString(1, shop.getShopName());
				ps.setString(2, shop.getAddress());
				ps.setString(3, shop.getCity());
				ps.setString(4, shop.getPincode());
				ps.setInt(5, shop.getShopID());
				ps.executeUpdate();
			} else {
				ps = con.prepareStatement(
						"insert into RT_ShopsDetails(ShopName, Address, City,  Pincode ) VALUES" + "(?,?,?,?)");
				ps.setString(1, shop.getShopName());
				ps.setString(2, shop.getAddress());
				ps.setString(3, shop.getCity());
				ps.setString(4, shop.getPincode());
				ps.executeUpdate();	
			}
			return true;
		} catch (Exception e) {
			throw e;
		} finally {
			con.close();
		}

	}

	public boolean putUserData(UserDetails user) throws Exception {
		try {
			if (user.getUserID() > 0) {
				ps = con.prepareStatement(
						"update RT_UsersDetails set  ShopID=?, UserName=?, MobileNo=? where UserID=?");
				ps.setInt(1, user.getShopID());
				ps.setString(2, user.getUserName());
				ps.setString(3, user.getMobileNo());
				ps.setInt(4, user.getUserID());
				ps.executeUpdate();	
			} else {
				ps = con.prepareStatement(
						"insert into RT_UsersDetails(ShopID, UserName, MobileNo) VALUES (?,?,?)");
				ps.setInt(1, user.getShopID());
				ps.setString(2, user.getUserName());
				ps.setString(3, user.getMobileNo());
				ps.executeUpdate();	
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			con.close();
		}
	}

	public ArrayList<ShopUserDetails> getShopUserData(int id) throws Exception {
		ArrayList<ShopUserDetails> feedData = new ArrayList<ShopUserDetails>();
		try {
			cs = con.prepareCall("{call GetShopUser(?)}");
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				shopuser = new ShopUserDetails();
				// shopuser.setShopUserID(rs.getInt("ShopUserID"));
				shopuser.setUserID(rs.getInt("UserID"));
				shopuser.setShopID(rs.getInt("ShopID"));
				shopuser.setMobileNo(rs.getString("MobileNo"));
				shopuser.setUserName(rs.getString("UserName"));
				shopuser.setCity(rs.getString("City"));
				shopuser.setShopName(rs.getString("ShopName"));
				shopuser.setPincode(rs.getString("Pincode"));
				shopuser.setAddress(rs.getString("Address"));
				feedData.add(shopuser);
			}
			return feedData;
		} catch (Exception e) {
			throw e;
		} finally {
			con.close();
		}
	}
}
