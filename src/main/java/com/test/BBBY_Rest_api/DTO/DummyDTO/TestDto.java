package com.test.BBBY_Rest_api.DTO.DummyDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestDto {

	@JsonProperty
	private String ID;
	
	@JsonProperty("title")
	private String title;

	@JsonProperty
	public String getID() {
		return ID;
	}

	@JsonProperty
	public void setID(String ID) {
		this.ID = ID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
