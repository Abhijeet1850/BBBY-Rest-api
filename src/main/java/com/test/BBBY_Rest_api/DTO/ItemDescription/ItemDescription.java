package com.test.BBBY_Rest_api.DTO.ItemDescription;

public class ItemDescription {

	private ItemListResponse ItemListResponse;

    public ItemListResponse getItemListResponse ()
    {
        return ItemListResponse;
    }

    public void setItemListResponse (ItemListResponse ItemListResponse)
    {
        this.ItemListResponse = ItemListResponse;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ItemListResponse = "+ItemListResponse+"]";
    }
	
}
