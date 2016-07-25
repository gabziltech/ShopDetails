package com.gabzil.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.gabzil.model.ContactDetails;
import com.gabzil.model.CustomerDetails;
import com.gabzil.model.ShopUserDetails;
import com.gabzil.model.ShopsDetails;
import com.gabzil.model.SyncDetails;
import com.gabzil.model.UserDetails;
import com.gabzil.sms.SMSSender;

public class ShopDetailsQuery {
	ShopsDetails shop;
	ShopUserDetails shopuser;
	PreparedStatement ps, ps1;
	ResultSet rs, rs1;
	CallableStatement cs;
	RetailDB db;
	Connection con;
	int newid;
	String to = null;
	String from = "2565705836";
	String msg = null;
	SMSSender smsobj;
	String shopName;

	public ShopDetailsQuery() {
		try {

			db = new RetailDB();
			con = db.getDBConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ShopsDetails putShopData(ShopsDetails shop) throws Exception {
		ShopsDetails dbshop=null;
		try {
			if (shop.getShopID() > 0) {
				newid = shop.getShopID();
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
			dbshop = getDBShop(shop);
			return dbshop;
		} catch (Exception e) {
			throw e;
		} finally {
			con.close();
		}

	}

	private ShopsDetails getDBShop(ShopsDetails shop) throws Exception {
		ShopsDetails dbshop=new ShopsDetails();
		try {
			ps = con.prepareStatement(
					"SELECT ShopID, ShopName, Address, City, Pincode FROM RT_ShopsDetails where ShopName=? AND Address=? AND City=? AND Pincode=? ORDER BY EntryDate DESC LIMIT 1 ");
			ps.setString(1, shop.getShopName());
			ps.setString(2, shop.getAddress());
			ps.setString(3, shop.getCity());
			ps.setString(4, shop.getPincode());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dbshop.setShopID(rs.getInt("ShopID"));
				dbshop.setShopName(rs.getString("ShopName"));
				dbshop.setAddress(rs.getString("Address"));
				dbshop.setCity(rs.getString("City"));
				dbshop.setPincode(rs.getString("Pincode"));
				shop.setShopID(dbshop.getShopID());
				putUserData(shop);
				setAllUser(dbshop);
			}
			return dbshop;
		} catch (Exception e) {
			throw e;
		}
	}

	private boolean putUserData(ShopsDetails shop) throws Exception {
		UserDetails shoper;
		try {
			for (int i = 0; i < shop.getAllUser().size(); i++) {
				int j = i;
				 shoper=shop.getAllUser().get(i);
				if (shoper.getUserID() > 0) {
					ps = con.prepareStatement(
							"update RT_UsersDetails set  UserName=?, UserType=?, MobileNo=?, IsActive=? where UserID=? and ShopID=? ");
					ps.setString(1, shoper.getUserName());
					ps.setString(2, shoper.getUserType());
					ps.setString(3, shoper.getMobileNo());
					ps.setBoolean(4, shoper.getIsActive());
					ps.setInt(5, shoper.getUserID());
					ps.setInt(6, shop.getShopID());
					ps.executeUpdate();
					new Thread(new Runnable() {
						public void run() {
							to = shop.getAllUser().get(j).getMobileNo();
							msg = "From:" + shopName + '\n' + "Dear:" + shop.getAllUser().get(j).getUserName() + '\n'
									+ "are you successully updated, your record with our shop.";
							smsobj = new SMSSender(to, from, msg);
							smsobj.sender();
						}
					}).start();
				} else {

					ps = con.prepareStatement(
							"insert into RT_UsersDetails(ShopID, UserName, UserType, MobileNo, IsActive) VALUES (?,?,?,?,?)");
					ps.setInt(1, shop.getShopID());
					ps.setString(2, shoper.getUserName());
					ps.setString(3, shoper.getUserType());
					ps.setString(4, shoper.getMobileNo());
					ps.setBoolean(5, shoper.getIsActive());
					ps.executeUpdate();

					new Thread(new Runnable() {
						public void run() {
							to = shop.getAllUser().get(j).getMobileNo();
							msg = "From:" + shopName + '\n' + "Dear:" + shop.getAllUser().get(j).getUserName() + '\n'
									+ "are you successully register with our shop.";
							smsobj = new SMSSender(to, from, msg);
							smsobj.sender();
						}
					}).start();
				}

			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int deleteUser(ShopsDetails shop){
		UserDetails user;
		int result=0;
		try{
			for(int i=0;i<shop.getAllUser().size();i++){
			int	j=i;
			 user=shop.getAllUser().get(i);
			ps=con.prepareStatement("delete from RT_UsersDetails  where UserID=? AND ShopID=?");
			ps.setInt(1, user.getUserID());
			ps.setInt(2, shop.getShopID());
			result=ps.executeUpdate();	
			new Thread(new Runnable() {
				public void run() {
					to = shop.getAllUser().get(j).getMobileNo();
					msg = "From:" + shopName + '\n' + "Dear:" + shop.getAllUser().get(j).getUserName() + '\n'
							+ "are you successully deleted with our shop.";
					smsobj = new SMSSender(to, from, msg);
					smsobj.sender();
				}
			}).start();
			}
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
	}
	
	
	public ShopsDetails getShopSync(int shopid) throws Exception {
		ShopsDetails syncData = new ShopsDetails();
		try {
			ps = con.prepareStatement(
					"SELECT ShopID,ShopName,Address,City,Pincode,EntryDate FROM RT_ShopsDetails where ShopID=?");
			ps.setInt(1, shopid);
			rs = ps.executeQuery();
			while (rs.next()) {
				syncData.setShopID(rs.getInt("ShopID"));
				syncData.setShopName(rs.getString("ShopName"));
				syncData.setAddress(rs.getString("Address"));
				syncData.setCity(rs.getString("City"));
				syncData.setPincode(rs.getString("Pincode"));
				syncData.setEntryDate(rs.getTimestamp("EntryDate"));
				setAllUser(syncData);
				setAllCustomer(syncData);
			}
			return syncData;
		} catch (Exception e) {
			throw e;
		}finally {
			con.close();
		}
	}

	private void setAllUser(ShopsDetails syncData) throws Exception {
		ArrayList<UserDetails> userData = new ArrayList<UserDetails>();
		try {
			ps = con.prepareStatement("SELECT UserID, ShopID, UserType, UserName, MobileNo, IsActive FROM RT_UsersDetails where ShopID=?");
			ps.setInt(1, syncData.getShopID());
			rs = ps.executeQuery();
			while (rs.next()) {
				UserDetails user = new UserDetails();
				user.setUserID(rs.getInt("UserID"));
				user.setShopID(rs.getInt("ShopID"));
				user.setUserType(rs.getString("UserType"));
				user.setUserName(rs.getString("UserName"));
				user.setMobileNo(rs.getString("MobileNo"));
				user.setIsActive(rs.getBoolean("IsActive"));
				userData.add(user);
			}
			syncData.setAllUser(userData);

		} catch (Exception e) {
			throw e;
		}
	}

	private void setAllCustomer(ShopsDetails syncData) throws Exception {
		ArrayList<CustomerDetails> customerData = new ArrayList<CustomerDetails>();
		try {
			ps = con.prepareStatement(
					"SELECT CustomerID,ShopID,CustomerName,Address,Building,Area,City,Amount,CreditDays,EntryDate FROM RT_CustomerDetails where ShopID=?");
			ps.setInt(1, syncData.getShopID());
			rs = ps.executeQuery();
			while (rs.next()) {
				CustomerDetails customer = new CustomerDetails();
				customer.setCustomerID(rs.getInt("CustomerID"));
				customer.setShopID(rs.getInt("ShopID"));
				customer.setCustomerName(rs.getString("CustomerName"));
				customer.setAddress(rs.getString("Address"));
				customer.setBuilding(rs.getString("Building"));
				customer.setArea(rs.getString("Area"));
				customer.setCity(rs.getString("City"));
				customer.setAmount(rs.getString("Amount"));
				customer.setCreditDays(rs.getString("CreditDays"));
				customer.setEntryDate(rs.getTimestamp("EntryDate"));
				setAllContact(customer);
				customerData.add(customer);

			}
			syncData.setAllCustomer(customerData);

		} catch (Exception e) {
			throw e;
		} 
	}

	private void setAllContact(CustomerDetails customer) {
		ArrayList<ContactDetails> contact = new ArrayList<ContactDetails>();
		try {
			ps1 = con.prepareStatement(
					"select ShopID, CustomerID, ContactID, MobileNo from RT_ContactDetails where ShopID=? AND CustomerID=?");
			ps1.setInt(1, customer.getShopID());
			ps1.setInt(2, customer.getCustomerID());
			rs1 = ps1.executeQuery();
			while (rs1.next()) {
				ContactDetails cont = new ContactDetails();
				cont.setShopID(rs1.getInt("ShopID"));
				cont.setCustomerID(rs1.getInt("customerID"));
				cont.setContactID(rs1.getInt("ContactID"));
				cont.setMobileNo(rs1.getString("MobileNo"));
				contact.add(cont);
			}
			customer.setAllContact(contact);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	public ShopsDetails getData(int id) throws Exception {
		ShopsDetails shop = new ShopsDetails();
		try {
			ps = con.prepareStatement(
					"SELECT ShopID,ShopName,Address,City,Pincode,PhoneNo,GeoLocLong,GeoLocLat,PointsBalance,EntryDate FROM RT_ShopsDetails where ShopID=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
			}

		} catch (Exception e) {
			throw e;
		}

		ArrayList<UserDetails> userData = new ArrayList<UserDetails>();
		try {
			ps = con.prepareStatement("SELECT ShopID,UserName,UserType,MobileNo FROM RT_UsersDetails where ShopID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserDetails user = new UserDetails();
				user.setShopID(rs.getInt("ShopID"));
				user.setUserName(rs.getString("UserName"));
				user.setUserType(rs.getString("UserType"));
				user.setMobileNo(rs.getString("MobileNo"));
				userData.add(user);
			}
			shop.setAllUser(userData);
			return shop;
		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList<ShopUserDetails> getShopUserData(int id) throws Exception {
		ArrayList<ShopUserDetails> data = new ArrayList<ShopUserDetails>();
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
				shopuser.setUserType(rs.getString("UserType"));
				shopuser.setCity(rs.getString("City"));
				shopuser.setShopName(rs.getString("ShopName"));
				shopuser.setPincode(rs.getString("Pincode"));
				shopuser.setAddress(rs.getString("Address"));
				data.add(shopuser);
			}
			return data;
		} catch (Exception e) {
			throw e;
		} finally {
			con.close();
		}
	}

	//
	// public ArrayList<ShopsDetails> getShopCodeSync(int code)throws Exception
	// {
	// ps = con.prepareStatement(
	// "SELECT new table
	// ShopID,ShopName,Address,City,Pincode,PhoneNo,GeoLocLong,GeoLocLat,PointsBalance,EntryDate
	// FROM RT_ShopsDetails where ShopID=?");
	// ps.setInt(1, id);
	// rs = ps.executeQuery();
	// while (rs.next()) {
	//
	//
	// }
	//
	// return getShopSync(shopid);
	// }

}
