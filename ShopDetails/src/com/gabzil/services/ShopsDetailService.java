package com.gabzil.services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.*;

import com.gabzil.model.ShopUserDetails;
import com.gabzil.model.ShopsDetails;
import com.gabzil.model.UserDetails;
import com.google.gson.Gson;
import com.gabzil.dao.ShopDetailsQuery;

@Path("/WebService")
public class ShopsDetailService {
	Gson gson;
	ShopDetailsQuery shop;

	public ShopsDetailService() {
		shop = new ShopDetailsQuery();
		gson = new Gson();
	}
	

	@POST
	@Path("/GetShop")
	@Produces("application/json")
	public String GetShop(String ShopInfo) {
		String reply = null;
		try {
			ArrayList<ShopsDetails> result = null;
			//String ShopInfo="{\"ShopInfo\":{\"ShopID\":1}}";
			System.out.println(ShopInfo);
			
			JSONObject obj = new JSONObject(ShopInfo);
			int ShopID=obj.getJSONObject("ShopInfo").getInt("ShopID");
			System.out.println(ShopID);
			
			result = shop.getData(ShopID);
			System.out.println(gson.toJson(result));
			reply = gson.toJson(result);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;		
	}
	
	@POST
	@Path("/InsertShop")
	@Produces("application/json")
	public String InsertShop(String info) {
		String reply = null;
		boolean result = false;
		try {
			//String ShopInfo="{\"ShopInfo\":{\"ShopID\":1}}";
			System.out.println(info);
			
			JSONObject obj = new JSONObject(info);
			String str=obj.getJSONObject("ShopInfo").toString();
			ShopsDetails data = gson.fromJson(str, ShopsDetails.class);
			System.out.println(data);
			
			result = shop.putData(data);
			System.out.println(gson.toJson(result));
			reply = gson.toJson(result);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;		
	}
	
	
	@POST
	@Path("/InsertUser")
	@Produces("application/json")
	public String InsertUser(String userinfo) {
		String reply = null;
		boolean result = false;
		try {
			//String ShopInfo="{\"ShopInfo\":{\"ShopID\":1}}";
			System.out.println(userinfo);
			
			JSONObject obj = new JSONObject(userinfo);
			String str=obj.getJSONObject("User").toString();
			UserDetails data = gson.fromJson(str, UserDetails.class);
			System.out.println(data);
			
			result = shop.putUserData(data);
			System.out.println(gson.toJson(result));
			reply = gson.toJson(result);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;		
	}	
	
	
	@POST
	@Path("/GetShopUser")
	@Produces("application/json")
	public String GetShopUser() {
		String reply = null;
		try {
			ArrayList<ShopUserDetails> result = null;
			String ShopUserInfo="{\"ShopUserInfo\":{\"ShopID\":1}}";
			System.out.println(ShopUserInfo);
			
			JSONObject obj = new JSONObject(ShopUserInfo);
			int ShopUserID=obj.getJSONObject("ShopUserInfo").getInt("ShopID");
			System.out.println(ShopUserID);
			
			result = shop.getShopUserData(ShopUserID);
			System.out.println(gson.toJson(result));
			reply = gson.toJson(result);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;		
	}
}