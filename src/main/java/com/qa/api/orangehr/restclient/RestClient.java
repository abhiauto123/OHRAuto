package com.qa.api.orangehr.restclient;

import java.util.HashMap;
import java.util.Map;
import com.qa.api.orangehr.util.TestUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	
	 private RestClient() {
		    throw new IllegalStateException("Utility class");
		  }

	// HTTP Methods : GET POST PUT DELETE
    /**
     * 
     * @param contentType
     * @param baseURI
     * @param basePath
     * @param token
     * @param paramsMap
     * @param log
     * @return
     */
	public static Response doGet(String contentType,String accessToken ,String baseURI, String basePath,
			Map<String, String> paramsMap, boolean log) {
		setBaseURI(baseURI);
		RequestSpecification request = createRequest(contentType,accessToken,paramsMap, log);
		return getResponse("GET", request, basePath);	 
	}
	
	/**
	 * 
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @param obj
	 * @return
	 */
	
	public static Response doPost(String contentType,String accessToken ,String baseURI, String basePath,
			Map<String, String> paramsMap, boolean log, Object obj, String postType) {

		setBaseURI(baseURI);
		RequestSpecification request = createRequest(contentType,accessToken,paramsMap, log);
		if(obj != null){
		addRequestPayload(request, obj);}
		return getResponse(postType, request, basePath);
		
	}
	
	public static void addRequestPayload(RequestSpecification request,Object obj){
		String jsonPayload = TestUtil.getSerializedJSON(obj);	
		request.body(jsonPayload);
	}

	private static void setBaseURI(String baseURI) {
		RestAssured.baseURI = baseURI;
	}
	

	private static RequestSpecification createRequest(String contentType, String accessToken,
			Map<String, String> paramsMap, boolean log) {
		RequestSpecification request;
		if (log) {
			request = RestAssured.given().log().all();
		} else {
			request = RestAssured.given();
		}

		if (accessToken!=null){
			request.header("Authorization","Bearer "+accessToken);
		}
		
		
		if (!(paramsMap == null)) {
			request.params(paramsMap);
		}

		if (contentType.equalsIgnoreCase("JSON")) {
			request.contentType(ContentType.JSON);
		}

		else if (contentType.equalsIgnoreCase("XML")) {
			request.contentType(ContentType.XML);
		}

		else if (contentType.equalsIgnoreCase("TEXT")) {
			request.contentType(ContentType.TEXT);
		}

		return request;

	}
	
	private static Response getResponse(String httpmethod, RequestSpecification request, String basePath){
		return executeAPI(httpmethod, request, basePath);	
	}
	
	private static Response executeAPI(String httpmethod, RequestSpecification request, String basePath){
		Response response= null;
		switch (httpmethod) {
		case "GET":
			response = request.get(basePath);
			break;
			
		case "POST":
			response = request.post(basePath);
			break;
		
		case "PATCH":
			response = request.patch(basePath);
			break;		
        
		case "PUT":
			response = request.put(basePath);
			break;
			
		case "DELETE":
			response = request.delete(basePath);
			break;		
		default:
			System.out.println("Please provide valid http method..!");
			break;
		}
	    return response;	
	}

}
