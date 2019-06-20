package com.test.BBBY_Rest_api.DTO.ItemDescription;

public class ItemListResponse {

	private String totalNoOfRecords;

    private ItemInfoList ItemInfoList;

    private String responseStatus;

    public String getTotalNoOfRecords ()
    {
        return totalNoOfRecords;
    }

    public void setTotalNoOfRecords (String totalNoOfRecords)
    {
        this.totalNoOfRecords = totalNoOfRecords;
    }

    public ItemInfoList getItemInfoList ()
    {
        return ItemInfoList;
    }

    public void setItemInfoList (ItemInfoList ItemInfoList)
    {
        this.ItemInfoList = ItemInfoList;
    }

    public String getResponseStatus ()
    {
        return responseStatus;
    }

    public void setResponseStatus (String responseStatus)
    {
        this.responseStatus = responseStatus;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [totalNoOfRecords = "+totalNoOfRecords+", ItemInfoList = "+ItemInfoList+", responseStatus = "+responseStatus+"]";
    }
}
