package com.test.BBBY_Rest_api.features_api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;

import com.test.BBBY_Rest_api.DTO.DummyDTO.ResponseDTO.CarrierDeliverySchedule;
import com.test.BBBY_Rest_api.ServiceActions.CarrierChildServiceDelivery;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;

public class ManageCarrierChildServiceDelivery {

	CarrierChildServiceDelivery CCSD = new CarrierChildServiceDelivery();
	
	@Given("I create test Data for Carrier Schedule")
	public void i_create_test_Data_for_Carrier_Schedule(DataTable testData) {
		List<Map<String,String>> data = testData.asMaps(String.class,String.class);
		CCSD.createTestDataForCCSDelivery(data);
	}

	@Given("I fetch Facility Delivery Schedule with below Request")
	public void i_fetch_Facility_Delivery_Schedule_with_below_Request(DataTable requestData) {
		Map<String,String> data = requestData.asMap(String.class, String.class);
		CCSD.createCCSDeliveryRequest(data);
	}

	@Then("I get the below Carrier Delivery Schedule")
	public void i_get_the_below_Carrier_Delivery_Schedule(DataTable responseData) {
		List<Map<String,String>> data = responseData.asMaps(String.class, String.class);
		List<CarrierDeliverySchedule> CDSList = new ArrayList<>();
		for(Map<String,String> m : data)
		{
			CDSList.add(new CarrierDeliverySchedule(m.get("Available"), 
					m.get("Carrier"), m.get("Reason_Code"), m.get("Code_Desc"), m.get("Date"), m.get("ServiceLevel")));
		}
       Assertions.assertThat(CCSD.assertCCSDeliveryResponse(CDSList)).isTrue();
	}
}
