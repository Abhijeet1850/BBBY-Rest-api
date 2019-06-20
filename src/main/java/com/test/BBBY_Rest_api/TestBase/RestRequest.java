package com.test.BBBY_Rest_api.TestBase;

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;

import org.apache.log4j.Logger;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;

public class RestRequest {

	

	private static final String DEFAULT_BASE_URL_PROPERTY = "service.url";
	private static final String REST_CONFIG_HEADER_ACCEPT_DEFAULT = "application/json";
	private static final String REST_CONFIG_CONTENT_TYPE_DEFAULT = ContentType.JSON.withCharset("UTF-8");

	@Nullable
	private static RestRequest requestInstance = null;

	@Nullable
	private String baseUrl = null;
	@Nullable
	private RequestSpecification requestSpec = null;
	@Nullable
	private Response requestResp = null;

	private RestRequest() {
	}

	/*public static RestRequest get() {

		return get(null, null, null);
	}

	public static RestRequest get(String baseUrl) {

		return get(baseUrl, null, null);
	}

	public static RestRequest get(@CheckForNull String confHeaderAccept, @CheckForNull String confContentType) {

		return get(null, confHeaderAccept, confContentType);
	}

	public static synchronized RestRequest get(@CheckForNull String givenBaseUrl, @CheckForNull String confHeaderAccept,
			@CheckForNull String confContentType) {

		//return get(givenBaseUrl, confHeaderAccept, confContentType, "");
	}

	public static void setHeaders() {

	}*/
	
	
	private static synchronized void initInstance(@CheckForNull String givenBaseUrl)
	   {
	      if (requestInstance == null)
	      {
	         requestInstance = new RestRequest();
	      }

	      if (givenBaseUrl != null)
	      {
	         requestInstance.baseUrl = givenBaseUrl;
	      }
	      else if (requestInstance.baseUrl == null)
	         requestInstance.baseUrl = System.getProperty(DEFAULT_BASE_URL_PROPERTY);
	   }
	
	

	private Response getResponseFromPath(RequestSender respSpec, String type, String path) {
		Response requestResp = null;
		String url = requestInstance.baseUrl + path;
		requestResp = getResponseFromUrl(respSpec, type, url);
		return requestResp;
	}

	private Response getResponseFromUrl(RequestSender respSpec, String type, String url) {
		
		if (type.equals("get"))
			requestResp = respSpec.get(url);
		else if (type.equals("post"))
			requestResp = respSpec.post(url);
		else if (type.equals("delete"))
			requestResp = respSpec.delete(url);
		else if (type.equals("put"))
			requestResp = respSpec.put(url);
		else if (type.equals("patch"))
			requestResp = respSpec.patch(url);

		return requestResp;
	}

	/*
	 * public static synchronized RestRequest get(@CheckForNull String
	 * givenBaseUrl, @CheckForNull String confHeaderAccept,
	 * 
	 * @CheckForNull String confContentType, String oauthID) {
	 * initInstance(givenBaseUrl);
	 * 
	 * String newConfHeader = (confHeaderAccept == null) ?
	 * REST_CONFIG_HEADER_ACCEPT_DEFAULT : confHeaderAccept; String newConfContent =
	 * (confContentType == null) ? REST_CONFIG_CONTENT_TYPE_DEFAULT :
	 * confContentType; requestInstance.initOAuthRestConfig(newConfHeader,
	 * newConfContent, oauthID);
	 * 
	 * requestInstance.apiVersion = ApiVersion.V1;
	 * 
	 * return requestInstance; }
	 */

}
