package com.test.BBBY_Rest_api.DTO.DummyDTO.RequestDTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CarrierDeliveryRequestDTO {

	public String carrier;
	public String date;
	public int numberofDays;
	public String serviceLevel;
	
	@JsonCreator
	public CarrierDeliveryRequestDTO(@JsonProperty("carrier") String carrier, @JsonProperty("date") String date,
			@JsonProperty("numberofDays") int numberofDays, @JsonProperty("serviceLevel") String serviceLevel) {
		this.carrier = carrier;
		this.date = date;
		this.numberofDays = numberofDays;
		this.serviceLevel = serviceLevel;
	}
	
	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNumberofDays() {
		return numberofDays;
	}

	public void setNumberofDays(int numberofDays) {
		this.numberofDays = numberofDays;
	}

	public String getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

}
