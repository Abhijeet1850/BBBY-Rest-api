package com.test.BBBY_Rest_api.DTO.ItemDescription;

public class DeliveryOption {

	private String deliveryOptionName;

    private String deliveryOptionCode;

    private String deliveryOptionAvailable;

    public String getDeliveryOptionName ()
    {
        return deliveryOptionName;
    }

    public void setDeliveryOptionName (String deliveryOptionName)
    {
        this.deliveryOptionName = deliveryOptionName;
    }

    public String getDeliveryOptionCode ()
    {
        return deliveryOptionCode;
    }

    public void setDeliveryOptionCode (String deliveryOptionCode)
    {
        this.deliveryOptionCode = deliveryOptionCode;
    }

    public String getDeliveryOptionAvailable ()
    {
        return deliveryOptionAvailable;
    }

    public void setDeliveryOptionAvailable (String deliveryOptionAvailable)
    {
        this.deliveryOptionAvailable = deliveryOptionAvailable;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [deliveryOptionName = "+deliveryOptionName+", deliveryOptionCode = "+deliveryOptionCode+", deliveryOptionAvailable = "+deliveryOptionAvailable+"]";
    }
	
}
