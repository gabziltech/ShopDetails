package com.gabzil.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import com.gabzil.dao.ShopDetailsQuery;
import com.gabzil.model.ShopsDetails;
import com.google.gson.Gson;

@Path("/WebService")
public class ShopsDetailService {
	Gson gson;
	ShopDetailsQuery shop;

	public ShopsDetailService() {
		shop = new ShopDetailsQuery();
		gson = new Gson();
	}
	

	
	@POST
	@Path("/InsertShop")
	@Produces("application/json")
	public String InsertShop(String info) {
		String reply = null;
		ShopsDetails result;
		try {
			//String ShopInfo="{\"ShopInfo\":{\"ShopID\":1}}";
			//String info="{\"Address\":\"pppppp\",\"AllUser\":[{\"MobileNo\":\"8087423008\",\"UserName\":\"poiu\",\"UserType\":\"staff\",\"UserID\":8,\"ShopID\":7}],\"City\":\"vardha\",\"Pincode\":\"467864589\",\"ShopName\":\"Tuo7\",\"ShopID\":7}";
			//String out = new String(info.getBytes("UTF-8"));
			//String info="{\"Address\":\"ABC\",\"AllUser\":[{\"UserType\":\"Owner\",\"MobileNo\":\"0123456789\",\"UserName\":\"Prashant\",\"UserID\":0,\"ShopID\":7,\"IsActive\":true}],\"City\":\"Pune\",\"Pincode\":\"123456\",\"ShopName\":\"Yogi\",\"ShopID\":7}";
			System.out.println(info);
			
			ShopsDetails data = gson.fromJson(info, ShopsDetails.class);
			
			System.out.println(data);
			
			result = shop.putShopData(data);
			System.out.println(gson.toJson(result));
			reply = gson.toJson(result);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;		
	}
	
	
	@POST
	@Path("/DeleteUser")
	@Produces("application/json")
	public String DeleteUser(String info) {
		String reply = null;
		int result;
		try {
			//String ShopInfo="{\"ShopInfo\":{\"ShopID\":1}}";
			//String info="{\"Address\":\"pppppp\",\"AllUser\":[{\"MobileNo\":\"8087423008\",\"UserName\":\"poiu\",\"UserType\":\"staff\",\"UserID\":8,\"ShopID\":7}],\"City\":\"vardha\",\"Pincode\":\"467864589\",\"ShopName\":\"Tuo7\",\"ShopID\":7}";
			//String out = new String(info.getBytes("UTF-8"));
			//String info="{\"Address\":\"ABC\",\"AllUser\":[{\"UserType\":\"Owner\",\"MobileNo\":\"0123456789\",\"UserName\":\"Prashant\",\"UserID\":13,\"ShopID\":9,\"IsActive\":true}],\"City\":\"Pune\",\"Pincode\":\"123456\",\"ShopName\":\"Yogi\",\"ShopID\":9}";
			System.out.println(info);
			
			ShopsDetails data = gson.fromJson(info, ShopsDetails.class);
			
			System.out.println(data);
			
			result = shop.deleteUser(data);
			System.out.println(gson.toJson(result));
			reply = gson.toJson(result);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;		
	}
	
	@POST
	@Path("/Sync")
	@Produces("application/json")
	public String GetShopSync(String ShopUserInfo) {
		String reply = null;
		try {
			ShopsDetails result = null;
			//String ShopUserInfo="{\"ShopUserInfo\":{\"ShopID\":1}}";
			System.out.println(ShopUserInfo);
			
			JSONObject obj = new JSONObject(ShopUserInfo);
			int ShopID=obj.getJSONObject("ShopUserInfo").getInt("ShopID");
			System.out.println(ShopID);
			
			result = shop.getShopSync(ShopID);
			System.out.println(gson.toJson(result));
			reply = gson.toJson(result);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;		
	}
	

//	@POST
//	@Path("/GetShop")
//	@Produces("application/json")
//	public String GetShop(String ShopInfo) {
//		String reply = null;
//		try {
//			ShopsDetails result = null;
//			//String ShopInfo="{\"ShopInfo\":{\"ShopID\":1}}";
//			System.out.println(ShopInfo);
//			
//			JSONObject obj = new JSONObject(ShopInfo);
//			int ShopID=obj.getJSONObject("ShopInfo").getInt("ShopID");
//			System.out.println(ShopID);
//			
//			result = shop.getData(ShopID);
//			System.out.println(gson.toJson(result));
//			reply = gson.toJson(result);
//						
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return reply;		
//	}
	
//	@POST
//	@Path("/InsertUser")
//	@Produces("application/json")
//	public String InsertUser(String userinfo) {
//		String reply = null;
//		boolean result = false;
//		try {
//			//String ShopInfo="{\"ShopInfo\":{\"ShopID\":1}}";
//			System.out.println(userinfo);
//			
//			JSONObject obj = new JSONObject(userinfo);
//			String str=obj.getJSONObject("User").toString();
//			UserDetails data = gson.fromJson(str, UserDetails.class);
//			System.out.println(data);
//			
//			result = shop.putUserData(data);
//			System.out.println(gson.toJson(result));
//			reply = gson.toJson(result);
//						
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return reply;		
//	}	
	
//	
//	@POST
//	@Path("/GetShopUser")
//	@Produces("application/json")
//	public String GetShopUser() {
//		String reply = null;
//		try {
//			ArrayList<ShopUserDetails> result = null;
//			String ShopUserInfo="{\"ShopUserInfo\":{\"ShopID\":1}}";
//			System.out.println(ShopUserInfo);
//			
//			JSONObject obj = new JSONObject(ShopUserInfo);
//			int ShopUserID=obj.getJSONObject("ShopUserInfo").getInt("ShopID");
//			System.out.println(ShopUserID);
//			
//			result = shop.getShopUserData(ShopUserID);
//			System.out.println(gson.toJson(result));
//			reply = gson.toJson(result);
//						
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return reply;		
//	}
//	
	
}