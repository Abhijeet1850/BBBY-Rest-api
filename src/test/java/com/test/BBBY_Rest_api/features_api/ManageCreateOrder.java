package com.test.BBBY_Rest_api.features_api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.BBBY_Rest_api.DTO.DummyDTO.BookDto;
import com.test.BBBY_Rest_api.utils.excelReader.ExcelReader;
import com.test.BBBY_Rest_api.utils.resource.ResourceHelper;

import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ManageCreateOrder {

	ObjectMapper mapper = new ObjectMapper();
	List<Header> hlist;
	String OrderNo;
	String orderCapturedDate;
	String lastUpdatedDTTM;
	JsonPath jp1;
	List<String> orders;
	String SAMLRequest;
	String EOM_JSESSIONID;
	String MIP_JSESSIONID;
	String _idp_authn_lc_key;
	String thirdReqURL;
	String fourthReqURL;
	String fifthReqURL;
	String seventhReqURL;
	String csrf_token;
	String idp_Session;
	String eigthReqURL;
	String ninthReqURL;
	String tenthReqURL;
	
	SessionFilter sessionFilter = new SessionFilter();
	CookieFilter cookieFilter = new CookieFilter();
	
	public void setBaseURI()
	{
		RestAssured.baseURI = "https://eomdev02.bedbath.com:30001";
	}
	
	
	
	public void firstRequest()
	{
		System.out.println("first request");
		
		//setHeader();
		hlist = new ArrayList<>();
		RequestSpecification RqSpec = RestAssured.given();
		//hlist.add(new Header("X-CSRF-TOKEN",generateToken()));
		hlist.add(new Header("Accept","text/html"));	                                     
		RqSpec.headers(new Headers(hlist));
		
        Response ResSpec = RqSpec.get("/index.jsp");
        RqSpec.filter(sessionFilter).filter(cookieFilter);
        
        String res = ResSpec.asString();
        
        Document doc = Jsoup.parse(res);
        Elements elements = doc.select("input");
        for(Element e : elements)
        {
        	if(e.attr("type").equals("hidden"))
        	{
        		SAMLRequest = e.attr("value");
        	}
        }
        EOM_JSESSIONID = ResSpec.cookie("EOM_JSESSIONID");   
        List<Header> li = ResSpec.headers().asList();
        
        for(Header h : li)
        {
        	System.out.println(h.getName() + " -->  " + h.getValue());
        }
	}
	
	
	public void secondRequest()
	{
		
		System.out.println("second request");
		hlist = new ArrayList<>();
		hlist.add(new Header("Content-Type","application/x-www-form-urlencoded"));	 
		hlist.add(new Header("Accept","text/html"));
		RequestSpecification RqSpec = RestAssured.given();
	    RqSpec.headers(new Headers(hlist));
	    RqSpec.cookie("EOM_JSESSIONID",EOM_JSESSIONID);
	    RqSpec.formParam("SAMLRequest", SAMLRequest);
	    
	    
	    RqSpec.filter(sessionFilter).filter(cookieFilter);
	    
        Response ResSpec = RqSpec.post("https://eomdev02.bedbath.com:13001/profile/SAML2/POST/SSO");
        MIP_JSESSIONID = ResSpec.cookie("MIP_JSESSIONID");   
        _idp_authn_lc_key = ResSpec.cookie("_idp_authn_lc_key");   
        
        
List<Header> li = ResSpec.headers().asList();
        
        for(Header h : li)
        {
        	System.out.println(h.getName() + " -->  " + h.getValue());
        }
        
        thirdReqURL = ResSpec.header("Location");
        System.out.println(thirdReqURL);
	}
	
	public void thirdRequest()
	{
		
		System.out.println("third request");
		hlist = new ArrayList<>();
		RestAssured.baseURI = "";
		RequestSpecification RqSpec = RestAssured.given();
		//headers
		hlist.add(new Header("Content-Type","application/x-www-form-urlencoded"));	
		hlist.add(new Header("Referer","https://eomdev02.bedbath.com:30001/manh/index.html"));
		hlist.add(new Header("Host","eomdev02.bedbath.com:13001"));
		hlist.add(new Header("Upgrade-Insecure-Requests","1"));
		hlist.add(new Header("Cache-Control","max-age=0"));
		hlist.add(new Header("Connection","keep-alive"));
		hlist.add(new Header("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36"));
		hlist.add(new Header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3"));
		RqSpec.headers(new Headers(hlist));
		
		System.out.println(EOM_JSESSIONID + "     "  +  MIP_JSESSIONID + "    "  +  _idp_authn_lc_key);
		//cookies
		RqSpec.cookie("EOM_JSESSIONID",EOM_JSESSIONID);
		RqSpec.cookie("MIP_JSESSIONID",MIP_JSESSIONID);
		RqSpec.cookie("_idp_authn_lc_key",_idp_authn_lc_key);
		
		 RqSpec.filter(sessionFilter).filter(cookieFilter);
		
		
		
		
        Response ResSpec = RqSpec.get(thirdReqURL);
        System.out.println(ResSpec.statusCode());
        
List<Header> li = ResSpec.headers().asList();
        
        for(Header h : li)
        {
        	System.out.println(h.getName() + " -->  " + h.getValue());
        }
        
        
        //fourthReqURL = ResSpec.getHeader("Location");
	}
	
	public void fourthRequest()
	{
		
		System.out.println("fourth request");
		
		hlist = new ArrayList<>();
		RequestSpecification RqSpec = RestAssured.given();
		//headers
		hlist.add(new Header("Accept","text/html"));
		RqSpec.headers(new Headers(hlist));
		
		//cookies
		RqSpec.cookie("EOM_JSESSIONID",EOM_JSESSIONID);
		RqSpec.cookie("MIP_JSESSIONID",MIP_JSESSIONID);
		RqSpec.cookie("_idp_authn_lc_key",_idp_authn_lc_key);
		
		RqSpec.queryParam("loginContextKey", _idp_authn_lc_key);
		
		 RqSpec.filter(sessionFilter).filter(cookieFilter);
        Response ResSpec = RqSpec.get("https://eomdev02.bedbath.com:13001/Authn/RemoteUser");
        System.out.println(ResSpec.statusCode());
        
List<Header> li = ResSpec.headers().asList();
        
        for(Header h : li)
        {
        	System.out.println(h.getName() + " -->  " + h.getValue());
        }
        
        //fifthReqURL = ResSpec.getHeader("Location");
	}
	
	
	public void fifthRequest()
	{
		
		System.out.println("fifth request");
		
		hlist = new ArrayList<>();
		RequestSpecification RqSpec = RestAssured.given();
		//headers
		hlist.add(new Header("Accept","text/html"));
		RqSpec.headers(new Headers(hlist));
		
		//cookies
		RqSpec.cookie("EOM_JSESSIONID",EOM_JSESSIONID);
		RqSpec.cookie("MIP_JSESSIONID",MIP_JSESSIONID);
		RqSpec.cookie("_idp_authn_lc_key",_idp_authn_lc_key);
		
		 RqSpec.filter(sessionFilter).filter(cookieFilter);
	    
        Response ResSpec = RqSpec.get("https://eomdev02.bedbath.com:13001/login.jsp");
        System.out.println(ResSpec.statusCode());
        String res = ResSpec.asString();
        Document doc = Jsoup.parse(res);
        Elements elements = doc.select("meta");
        for(Element e : elements)
        {
        	if(e.attr("name").equals("_csrf"))
        	{
        		csrf_token = e.attr("content");
        	}
        }
        
        System.out.println(csrf_token);
        
List<Header> li = ResSpec.headers().asList();
        
        for(Header h : li)
        {
        	System.out.println(h.getName() + " -->  " + h.getValue());
        }
	}
	
	
	public void sixthRequest()
	{
		
		
		System.out.println("sixth request");
		
		hlist = new ArrayList<>();
		RestAssured.baseURI = "";
		RequestSpecification RqSpec = RestAssured.given();
		//headers
		hlist.add(new Header("Accept","text/html"));
		RqSpec.headers(new Headers(hlist));
		
		//cookies
		RqSpec.cookie("EOM_JSESSIONID",EOM_JSESSIONID);
		RqSpec.cookie("MIP_JSESSIONID",MIP_JSESSIONID);
		RqSpec.cookie("_idp_authn_lc_key",_idp_authn_lc_key);
		
		RqSpec.param("j_username", "ssaikia");
		RqSpec.param("j_password", "ssaikia");
		RqSpec.param("_csrf", csrf_token);
		
		 RqSpec.filter(sessionFilter).filter(cookieFilter);
        Response ResSpec = RqSpec.post("https://eomdev02.bedbath.com:13001/j_spring_security_check");
        
        System.out.println(ResSpec.statusCode());
        System.out.println("old MIP_JSESSIONID ---> :" + MIP_JSESSIONID);
        MIP_JSESSIONID = ResSpec.cookie("MIP_JSESSIONID");
        System.out.println("new MIP_JSESSIONID ---> :" + MIP_JSESSIONID);
        seventhReqURL = ResSpec.getHeader("Location");
        System.out.println(seventhReqURL);
        
        
List<Header> li = ResSpec.headers().asList();
        
        for(Header h : li)
        {
        	System.out.println(h.getName() + " -->  " + h.getValue());
        }
        
	}
	
	
	 public void seventhRequest()
	{
		
		//Authn/RemoteUser
		System.out.println("seventh request");
		hlist = new ArrayList<>();
		RequestSpecification RqSpec = RestAssured.given();
		//headers
		//hlist.add(new Header("Accept","text/html"));
		hlist.add(new Header("Referer","https://eomdev02.bedbath.com:13001/login.jsp"));
		hlist.add(new Header("Host","eomdev02.bedbath.com:13001"));
		hlist.add(new Header("Upgrade-Insecure-Requests","1"));
		hlist.add(new Header("Cache-Control","max-age=0"));
		hlist.add(new Header("Connection","keep-alive"));
		hlist.add(new Header("Accept-Encoding","gzip, deflate, br"));
		hlist.add(new Header("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36"));
		hlist.add(new Header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3"));	
		
		
		RqSpec.headers(new Headers(hlist));
		
		//cookies
		RqSpec.cookie("EOM_JSESSIONID",EOM_JSESSIONID);
		RqSpec.cookie("MIP_JSESSIONID",MIP_JSESSIONID);
		RqSpec.cookie("_idp_authn_lc_key",_idp_authn_lc_key);
		
		//RqSpec.queryParam("loginContextKey",_idp_authn_lc_key);
		
		
		RqSpec.filter(sessionFilter).filter(cookieFilter);
        Response ResSpec = RqSpec.get("https://eomdev02.bedbath.com:13001/Authn/RemoteUser?loginContextKey="+ _idp_authn_lc_key);
        System.out.println(ResSpec.getSessionId());
        System.out.println(ResSpec.statusCode());
        idp_Session = ResSpec.cookie("_idp_session");
        System.out.println(idp_Session);
        eigthReqURL = ResSpec.getHeader("Location");
        System.out.println(eigthReqURL);
	} 
	
	
	public void eightRequest()
	{
		
		
		System.out.println("eigth request");
		//SAML2/POST/SSO
		RestAssured.baseURI = "";
		hlist = new ArrayList<>();
		RequestSpecification RqSpec = RestAssured.given();
		//headers
		hlist.add(new Header("Accept","text/html"));
		RqSpec.headers(new Headers(hlist));
		
		//cookies
		RqSpec.cookie("EOM_JSESSIONID",EOM_JSESSIONID);
		RqSpec.cookie("MIP_JSESSIONID",MIP_JSESSIONID);
		RqSpec.cookie("_idp_authn_lc_key",_idp_authn_lc_key);
		//RqSpec.cookie("_idp_session",idp_Session);
		 RqSpec.filter(sessionFilter).filter(cookieFilter);
		//Response ResSpec = RqSpec.get(eigthReqURL);
        Response ResSpec = RqSpec.get("https://eomdev02.bedbath.com:13001/profile/SAML2/POST/SSO");
        
        
        System.out.println(ResSpec.statusCode());
        
List<Header> li = ResSpec.headers().asList();
        
        for(Header h : li)
        {
        	System.out.println(h.getName() + " -->  " + h.getValue());
        }
        

        
        ninthReqURL = ResSpec.getHeader("Location");
	}
	
	public void ninthRequest()
	{
		
		//SAML/SSO/EOM
		hlist = new ArrayList<>();
		RequestSpecification RqSpec = RestAssured.given();
		//headers
		hlist.add(new Header("Accept","text/html"));
		RqSpec.headers(new Headers(hlist));
		
		//cookies
		RqSpec.cookie("EOM_JSESSIONID",EOM_JSESSIONID);
		RqSpec.cookie("MIP_JSESSIONID",MIP_JSESSIONID);
		RqSpec.cookie("_idp_authn_lc_key",_idp_authn_lc_key);
		RqSpec.cookie("_idp_session",idp_Session);
		
		 RqSpec.filter(sessionFilter).filter(cookieFilter);
        Response ResSpec = RqSpec.get(ninthReqURL);
        EOM_JSESSIONID = ResSpec.cookie("EOM_JSESSIONID");
        tenthReqURL = ResSpec.getHeader("Location");
	}
	
	
	public void tenthRequest()
	{
		hlist = new ArrayList<>();
		RequestSpecification RqSpec = RestAssured.given();
		//headers
		hlist.add(new Header("Accept","text/html"));
		RqSpec.headers(new Headers(hlist));
		
		//cookies
		RqSpec.cookie("EOM_JSESSIONID",EOM_JSESSIONID);
		RqSpec.cookie("MIP_JSESSIONID",MIP_JSESSIONID);
		RqSpec.cookie("_idp_authn_lc_key",_idp_authn_lc_key);
		RqSpec.cookie("_idp_session",idp_Session);
		 RqSpec.filter(sessionFilter).filter(cookieFilter);
		
        Response ResSpec = RqSpec.get(tenthReqURL);
	}
	
	
	
	
	public String generateToken()
	{
		
		firstRequest();
		secondRequest();
		thirdRequest();
		fourthRequest();
		fifthRequest();
		sixthRequest();
		seventhRequest();
		eightRequest();
		ninthRequest();
		tenthRequest();
		long epochTime;
		String token ="";
		setHeader();
		hlist.add(new Header("X-Requested-With", "XMLHttpRequest"));
		RequestSpecification RqSpec = RestAssured.given();
		
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat crunchifyFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
		try
		{
		String currentTime = crunchifyFormat.format(today);
        Date date = crunchifyFormat.parse(currentTime);
		epochTime = date.getTime();
        RqSpec.queryParam("_dc",String.valueOf(epochTime));
        setBaseURI();
		Response ResSpec = RqSpec.get("/csrf/token.jsp");
		
		if(ResSpec.statusCode() ==200)
		{
			System.out.println("token generation successfull");
			token = ResSpec.asString().replaceAll("X-CSRF-TOKEN=", "");
		}
		
		return token;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";	
	}
	
	
	public void setHeader()
	{
		hlist = new ArrayList<Header>();
		hlist.add(new Header("Content-Type","application/json"));
		hlist.add(new Header("Accept","application/json"));        
	}
	
	
	
	
	public void createOrder()
	{
		setBaseURI();
		setHeader();
		RequestSpecification RqSpec = RestAssured.given();
		//hlist.add(new Header("X-CSRF-TOKEN",generateToken()));
		hlist.add(new Header("X-CSRF-TOKEN",generateToken()));	                                     
		RqSpec.headers(new Headers(hlist));

		RqSpec.queryParam("_dc","1560936653892");
		RqSpec.queryParam("responseType","FullCustomerOrder");
		RqSpec.body(new File(ResourceHelper.getResourcePath("\\src\\main\\resources\\jsonFiles\\createOrder\\GenerateOrder.json")));
		Response ResSpec = RqSpec.post("/services/olm/customerorder/createOrUpdate");
		
		if(ResSpec.statusCode() ==200)
		{
			System.out.println("Create order successfull");
			jp1 = new JsonPath(ResSpec.asString());
			OrderNo =jp1.getString("customerOrder.orderNumber");
			orderCapturedDate = jp1.getString("customerOrder.orderCreatedDate");
			lastUpdatedDTTM = jp1.getString("customerOrder.lastUpdatedDTTM");
		}
		
		
		System.out.println("Order no generate" + OrderNo);
	}
	
	
	public void updateOrderData()
	{
		String oldOlderJsonData;
		setBaseURI();
		setHeader();
		RequestSpecification RqSpec = RestAssured.given();
		//hlist.add(new Header("X-CSRF-TOKEN",generateToken()));
		hlist.add(new Header("X-CSRF-TOKEN",generateToken()));
		RqSpec.headers(new Headers(hlist));
		
		RqSpec.queryParam("_dc","1560936653892");
		RqSpec.queryParam("responseType","FullCustomerOrder");
		
		try {
			oldOlderJsonData = new String(Files.readAllBytes(Paths.get(ResourceHelper.getResourcePath("\\src\\main\\resources\\jsonFiles\\createOrder\\UpdateOrder.json"))));
			oldOlderJsonData = oldOlderJsonData.replaceAll("BBD4000025175", OrderNo).replaceAll("6/19/19 06:42 EDT", orderCapturedDate).replaceAll("2019-06-19 06:43:53.778", lastUpdatedDTTM);
			RqSpec.body(oldOlderJsonData);
			
			Response ResSpec = RqSpec.post("/services/olm/customerorder/createOrUpdate");
			if(ResSpec.statusCode() ==200)
			{
				System.out.println("Order: " + OrderNo + " created");
				orders.add(OrderNo);
			}								
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Given("Create {string} new orders")
	public void create_new_orders(String number) {
      for(int i=0; i< Integer.parseInt(number); i++)
      {
    	  createOrder();
  		   updateOrderData();
      }  		
		
		for(String S : orders)
		{
			System.out.println(S);
		}
	}
	
}
