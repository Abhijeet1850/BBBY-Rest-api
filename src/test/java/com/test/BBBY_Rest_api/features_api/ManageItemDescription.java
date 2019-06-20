package com.test.BBBY_Rest_api.features_api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.BBBY_Rest_api.DTO.ItemDescription.ItemDescription;
import com.test.BBBY_Rest_api.DTO.ItemDescription.ItemInfo;
import com.test.BBBY_Rest_api.DTO.ItemDescription.ItemInfoList;
import com.test.BBBY_Rest_api.DTO.ItemDescription.ItemListResponse;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ManageItemDescription {

	@Test
	public void hitItemApi()
	{
		RestAssured.baseURI = "https://eomdev02.bedbath.com:30001";
		
		List<Header> hlist = new ArrayList<Header>();
		hlist.add(new Header("Content-Type","application/json"));
		hlist.add(new Header("Accept","application/json"));
        hlist.add(new Header("X-CSRF-TOKEN","61dc127c-e479-4b75-9e3c-7c3292f36c4c"));
		
		
		Headers headers = new Headers(hlist);
		RequestSpecification RqSpec = RestAssured.given();
		RqSpec.formParam("_dc", "1560932775056");
		RqSpec.formParam("jsonData", "%5Bobject%20Object%5D");
		RqSpec.formParam("page", "1");
		RqSpec.formParam("start", "0");
		RqSpec.formParam("limit", "1000");
		
		
		RqSpec.headers(headers);
		RqSpec.body("{\"ItemListRequest\":{\"itemSearchCriteria\":{\"itemSearchStr\":\"belt\",\"style\":\"\",\"color\":\"\",\"size\":\"\",\"productClassId\":null,\"siteType\":\"BedBathBeyond\",\"customFields\":{}},\"sortingCriterion\":{\"sortField\":\"\",\"sortDirection\":\"\"},\"pageNavigationDetails\":{\"currentPageNumber\":0,\"rowsPerPage\":1000}}}");
		Response ResSpec = RqSpec.post("/services/olm/item/itemlist");
				
		String body = ResSpec.body().asString();
		
		
		ObjectMapper mapper = new ObjectMapper();
		/*ItemDescription itemDesc = mapper.readValue(body,ItemDescription.class);
		
		ItemListResponse ilr =  itemDesc.getItemListResponse();
		ItemInfoList iil = ilr.getItemInfoList();
		List<ItemInfo> ii = iil.getItemInfo();
		
		for(ItemInfo item : ii)
		{
			if(item.getAvailabilityStatus().equals("") || item.getAvailabilityStatus().equals(null))
			{
				//List<>
			}
		}*/
		
		
	}
	
}
