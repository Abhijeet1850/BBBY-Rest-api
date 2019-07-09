package com.test.BBBY_Rest_api.features_api;

import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GenerateToken {

	public void setBaseURI()
	{
		RestAssured.baseURI = "https://eomdev02.bedbath.com:30001";
	}
	
	
	
	public String generateToken()
	{
		String token ="";
		//setHeader();
		//hlist.add(new Header("X-Requested-With", "XMLHttpRequest"));
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
	
	@When("I hit the login request")
	public void i_hit_the_login_request() {
	    
	}
	
}
