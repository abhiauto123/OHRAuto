package com.qa.api.orangehr.util;

import java.util.Properties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestUtil {
	
	/**
	 * This method is used to convert POJO object to a String
	 * @param obj
	 * @return
	 */

	public static String getSerializedJSON(Object obj){
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try{
			jsonString = mapper.writeValueAsString(obj); 
			System.out.println("JSON body payload.." + jsonString);
		}catch(JsonProcessingException e){
		    e.printStackTrace();	
		}  
		
		return jsonString;
	}
	
	
	public Response getAccessToken() {

		Properties p = PropertyCache.propertyFileReader("data.properties");
		return RestAssured.given().log().all().header("Content-Type", "application/x-www-form-urlencoded")
				.formParam("client_id", p.getProperty("client_id"))
				.formParam("client_secret", p.getProperty("client_secret"))
				.formParam("grant_type", p.getProperty("grant_type")).formParam("username", p.getProperty("username"))
				.formParam("password", p.getProperty("password"))
				.post(p.getProperty("baseURI") + p.getProperty("authEndPoint"));

	}

}
