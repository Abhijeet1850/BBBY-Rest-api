package com.test.BBBY_Rest_api.DTO.ItemDescription;

import java.util.List;

public class ItemInfoList {

	private List<ItemInfo> itemInfo;

    public List<ItemInfo> getItemInfo ()
    {
        return itemInfo;
    }

    public void setItemInfo (List<ItemInfo> itemInfo)
    {
        this.itemInfo = itemInfo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ItemInfo = "+itemInfo+"]";
    }
	
}
