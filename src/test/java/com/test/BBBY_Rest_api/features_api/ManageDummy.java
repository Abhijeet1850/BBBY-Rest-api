package com.test.BBBY_Rest_api.features_api;


import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;


import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.BBBY_Rest_api.DTO.DummyDTO.BookDto;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ManageDummy {

	
	String body;
	ObjectMapper mapper = new ObjectMapper();
	List<BookDto> books;
	
	@When("I fetch the books")
	public void i_fetch_the_books() {
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";
		List<Header> hlist = new ArrayList<Header>();
		hlist.add(new Header("Content-Type","application/json"));
		hlist.add(new Header("Accept","application/json"));
		
		
		Headers headers = new Headers(hlist);
		RequestSpecification RqSpec = RestAssured.given();
		RqSpec.headers(headers);
		
		Response ResSpec = RqSpec.get("/api/Books");
		
		body = ResSpec.body().asString();
		
		
	}

	@Then("the total number of books should be {string}")
	public void the_total_number_of_books_should_be(String size) {
		
		
		try {
			books = mapper.readValue(body, new TypeReference<List<BookDto>>() {
			});
			
			Assertions.assertThat(books.size()).isEqualTo(Integer.parseInt(size));
			
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		
	}

	@Then("the second book name is {string}")
	public void the_second_book_name_is(String text) {
	    
		try {
			books = mapper.readValue(body, new TypeReference<List<BookDto>>() {
			});
			
			System.out.println(books.get(1).getDescription());
			
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	
}
