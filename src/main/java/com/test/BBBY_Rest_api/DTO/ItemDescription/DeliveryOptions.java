package com.test.BBBY_Rest_api.DTO.ItemDescription;

import java.util.List;

public class DeliveryOptions {

	private List<DeliveryOption> deliveryOption;

	public List<DeliveryOption> getDeliveryOption() {
		return deliveryOption;
	}

	public void setDeliveryOption(List<DeliveryOption> deliveryOption) {
		this.deliveryOption = deliveryOption;
	}

	@Override
	public String toString() {
		return "ClassPojo [deliveryOption = " + deliveryOption + "]";
	}

}
