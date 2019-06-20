package com.test.BBBY_Rest_api.features_api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.BBBY_Rest_api.DTO.DummyDTO.BookDto;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;


public class DummyRest {

	@Test
	public void getDummyData() throws JsonParseException, JsonMappingException, IOException
	{
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";
		List<Header> hlist = new ArrayList<Header>();
		hlist.add(new Header("Content-Type","application/json"));
		hlist.add(new Header("Accept","application/json"));
		
		
		Headers headers = new Headers(hlist);
		RequestSpecification RqSpec = RestAssured.given();
		RqSpec.headers(headers);
		
		Response ResSpec = RqSpec.get("/api/Books");
		String body = ResSpec.body().asString();
		
		
		ObjectMapper mapper = new ObjectMapper();
		List<BookDto> books = mapper.readValue(body, new TypeReference<List<BookDto>>() {
		});
		
		System.out.println(books.size());
		System.out.println(books.get(1).getDescription());
		
		
	}
	
	
}
