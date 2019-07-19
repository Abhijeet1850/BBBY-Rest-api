package com.test.BBBY_Rest_api.DTO.DummyDTO.ResponseDTO;

import java.util.List;

public class CarrierDeliveryScheduleList {

	 private List<CarrierDeliverySchedule> CarrierDeliverySchedules;

	    public void setCarrierDeliverySchedules(List<CarrierDeliverySchedule> CarrierDeliverySchedules){
	        this.CarrierDeliverySchedules = CarrierDeliverySchedules;
	    }
	    public List<CarrierDeliverySchedule> getCarrierDeliverySchedules(){
	        return this.CarrierDeliverySchedules;
	    }
}
