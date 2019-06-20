package com.test.BBBY_Rest_api.features_api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.BBBY_Rest_api.DTO.DummyDTO.BookDto;
import com.test.BBBY_Rest_api.utils.excelReader.ExcelReader;
import com.test.BBBY_Rest_api.utils.resource.ResourceHelper;

import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ManageCreateOrder {

	ObjectMapper mapper = new ObjectMapper();
	List<Header> hlist;
	String OrderNo;
	String orderCapturedDate;
	String lastUpdatedDTTM;
	JsonPath jp1;
	List<String> orders;
	
	public void setBaseURI()
	{
		RestAssured.baseURI = "https://eomdev02.bedbath.com:30001";
	}
	
	
	
	public String generateToken()
	{
		String token ="";
		setHeader();
		hlist.add(new Header("X-Requested-With", "XMLHttpRequest"));
		RequestSpecification RqSpec = RestAssured.given();
		RqSpec.queryParam("_dc","1560936653892");
		
		Response ResSpec = RqSpec.get("/csrf/token.jsp");
		
		if(ResSpec.statusCode() ==200)
		{
			System.out.println("token generation successfull");
			token = ResSpec.asString().replaceAll("X-CSRF-TOKEN=", "");
		}
		
		return token;
		
	}
	
	
	public void setHeader()
	{
		hlist = new ArrayList<Header>();
		hlist.add(new Header("Content-Type","application/json"));
		hlist.add(new Header("Accept","application/json"));        
	}
	
	
	
	
	public void createOrder()
	{
		setBaseURI();
		setHeader();
		RequestSpecification RqSpec = RestAssured.given();
		//hlist.add(new Header("X-CSRF-TOKEN",generateToken()));
		hlist.add(new Header("X-CSRF-TOKEN","bf022a5e-2eeb-4893-bc5d-7da9423f6ea8"));	                                     
		RqSpec.headers(new Headers(hlist));

		RqSpec.queryParam("_dc","1560936653892");
		RqSpec.queryParam("responseType","FullCustomerOrder");
		RqSpec.body(new File(ResourceHelper.getResourcePath("\\src\\main\\resources\\jsonFiles\\createOrder\\GenerateOrder.json")));
		Response ResSpec = RqSpec.post("/services/olm/customerorder/createOrUpdate");
		
		if(ResSpec.statusCode() ==200)
		{
			System.out.println("Create order successfull");
			jp1 = new JsonPath(ResSpec.asString());
			OrderNo =jp1.getString("customerOrder.orderNumber");
			orderCapturedDate = jp1.getString("customerOrder.orderCreatedDate");
			lastUpdatedDTTM = jp1.getString("customerOrder.lastUpdatedDTTM");
		}
		
		
		System.out.println("Order no generate" + OrderNo);
	}
	
	
	public void updateOrderData()
	{
		String oldOlderJsonData;
		setBaseURI();
		setHeader();
		RequestSpecification RqSpec = RestAssured.given();
		//hlist.add(new Header("X-CSRF-TOKEN",generateToken()));
		hlist.add(new Header("X-CSRF-TOKEN","e562189b-b429-4c56-be54-df52f3aa70f4"));
		RqSpec.headers(new Headers(hlist));
		
		RqSpec.queryParam("_dc","1560936653892");
		RqSpec.queryParam("responseType","FullCustomerOrder");
		
		try {
			oldOlderJsonData = new String(Files.readAllBytes(Paths.get(ResourceHelper.getResourcePath("\\src\\main\\resources\\jsonFiles\\createOrder\\UpdateOrder.json"))));
			oldOlderJsonData = oldOlderJsonData.replaceAll("BBD4000025175", OrderNo).replaceAll("6/19/19 06:42 EDT", orderCapturedDate).replaceAll("2019-06-19 06:43:53.778", lastUpdatedDTTM);
			RqSpec.body(oldOlderJsonData);
			
			Response ResSpec = RqSpec.post("/services/olm/customerorder/createOrUpdate");
			if(ResSpec.statusCode() ==200)
			{
				System.out.println("Order: " + OrderNo + " created");
				orders.add(OrderNo);
			}								
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Given("Create {string} new orders")
	public void create_new_orders(String number) {
      for(int i=0; i< Integer.parseInt(number); i++)
      {
    	  createOrder();
  		   updateOrderData();
      }  		
		
		for(String S : orders)
		{
			System.out.println(S);
		}
	}
	
}
