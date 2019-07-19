package com.test.BBBY_Rest_api.ServiceActions;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.datastax.driver.core.Session;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.BBBY_Rest_api.DTO.DummyDTO.RequestDTO.CarrierDeliveryRequestDTO;
import com.test.BBBY_Rest_api.DTO.DummyDTO.ResponseDTO.CarrierDeliverySchedule;
import com.test.BBBY_Rest_api.DTO.DummyDTO.ResponseDTO.CarrierDeliveryScheduleList;
import com.test.BBBY_Rest_api.helper.dbUtils.CassandraConnector;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CarrierChildServiceDelivery {

	CassandraConnector client = new CassandraConnector();
	public Session session;
	ObjectMapper mapper = new ObjectMapper();
	List<Header> hlist;
	RequestSpecification RqSpec;
	Response ResSpec;

	String CCSTable = "getcarrierdeliveryschedule";
	String delQuery = "DELETE FROM %s WHERE carrier='%s' and non_working >='%s' and  non_working <='%s'";
	String insQuery = "insert into %s(carrier, non_working, service_level, reason) values ('%s','%s','%s','%s')";
	String CCSDeliveryURL = "/CarrierDeliverySchedule";

	public void setBaseURI() {
		RestAssured.baseURI = "http://d01-edd.bbbytest.com";
	}

	public void setHeader() {
		hlist = new ArrayList<Header>();
		hlist.add(new Header("Content-Type", "application/json"));
		hlist.add(new Header("Accept", "application/json"));
	}

	public void sendCCSDeliveryRequest(Map<String, String> data) {

		RqSpec = RestAssured.given();
		setBaseURI();
		setHeader();
		RqSpec.headers(new Headers(hlist));
		RqSpec.body(createCCSDeliveryRequest(data));
		ResSpec = RqSpec.post(CCSDeliveryURL);
	}

	public String createCCSDeliveryRequest(Map<String, String> data) {
		CarrierDeliveryRequestDTO cdr = new CarrierDeliveryRequestDTO(data.get("Carrier"), data.get("Date"),
				Integer.parseInt(data.get("NumberofDays")), data.get("ServiceLevel"));
		try {
			String CCSDelRequest = mapper.writeValueAsString(cdr);
			return CCSDelRequest;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}

	public CarrierDeliveryScheduleList getCCSDelResponseObject() {
		try {
			CarrierDeliveryScheduleList cdsList = mapper.readValue(getCCSDeliveryResponse(), CarrierDeliveryScheduleList.class);
			return cdsList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getCCSDeliveryResponse() {
		return ResSpec.asString();
	}

	public int getCCSDeliveryResponseStatusCode() {
		return ResSpec.statusCode();
	}

	public void createTestDataForCCSDelivery(List<Map<String, String>> data) {
		for (Map<String, String> m : data) {
			deleteExistingTestData(m.get("Carrier"), m.get("StartDate"), m.get("EndDate"));
		}
		for (Map<String, String> m : data) {
			insertTestData(m.get("Carrier"), m.get("nonWorking"), m.get("Service"), m.get("Reason"));
		}
	}

	public void deleteExistingTestData(String carrier, String startDate, String endDate) {
		client.connect("10.160.112.80", 9042);
		session = client.getSession();
		session.execute(String.format(delQuery, CCSTable, carrier, startDate, endDate));
	}

	public void insertTestData(String carrier, String nonWorking, String service, String Reason) {
		client.connect("10.160.112.80", 9042);
		session = client.getSession();
		session.execute(String.format(insQuery, CCSTable, carrier, nonWorking, service, Reason));
	}

	 public boolean assertCCSDeliveryResponse(List<CarrierDeliverySchedule> data)
	 {
		 CarrierDeliveryScheduleList cdsList =  getCCSDelResponseObject();
		  List<CarrierDeliverySchedule> CDS =   cdsList.getCarrierDeliverySchedules();
		  if(CDS.equals(data))
			  return true;
		  return false;
	 }
}
