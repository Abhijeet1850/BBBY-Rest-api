package com.test.BBBY_Rest_api.DTO.DummyDTO.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CarrierDeliverySchedule {

	private String Available;
	private String Carrier;
	private String Reason_Code;
	private String Code_Desc;
	private String Date;
	private String ServiceLevel;

	public CarrierDeliverySchedule(String available, String carrier, String reason_Code, String code_Desc, String date,
			String serviceLevel) {
		super();
		Available = available;
		Carrier = carrier;
		Reason_Code = reason_Code;
		Code_Desc = code_Desc;
		Date = date;
		ServiceLevel = serviceLevel;
	}

	public String getServiceLevel() {
		return ServiceLevel;
	}

	public void setServiceLevel(String serviceLevel) {
		ServiceLevel = serviceLevel;
	}

	public String getAvailable() {
		return Available;
	}

	public void setAvailable(String available) {
		Available = available;
	}

	public String getCarrier() {
		return Carrier;
	}

	public void setCarrier(String carrier) {
		Carrier = carrier;
	}

	public String getReason_Code() {
		return Reason_Code;
	}

	public void setReason_Code(String reason_Code) {
		Reason_Code = reason_Code;
	}

	public String getCode_Desc() {
		return Code_Desc;
	}

	public void setCode_Desc(String code_Desc) {
		Code_Desc = code_Desc;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

}
