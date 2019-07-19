package com.test.BBBY_Rest_api.features_api;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.BBBY_Rest_api.DTO.DummyDTO.TestDto;

public class TestClass {

	public static void main(String[] args) {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			TestDto td = mapper.readValue("{\r\n" + 
					"        \"ID\": \"test ID\",\r\n" + 
					"		\"title\": \"book title\"\r\n" + 
					"}", TestDto.class);
			
			System.out.println(td.getID() + "    " + td.getTitle());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
}
}
