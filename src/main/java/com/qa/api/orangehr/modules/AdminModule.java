package com.qa.api.orangehr.modules;


import java.util.Map;
import com.qa.api.orangehr.pojo.EmploymentStatus;
import com.qa.api.orangehr.restclient.RestClient;
import com.qa.api.orangehr.util.PropertyCache;
import com.qa.api.orangehr.util.TestUtil;
import io.restassured.response.Response;

public class AdminModule extends TestUtil{
	
	TestUtil testutil = new TestUtil();
	String dataFileName = "data.properties";
	String accessToken=PropertyCache.getProperty(dataFileName, "access_token");
	String baseURI = PropertyCache.getProperty(dataFileName, "baseURI");
	String empStatusEndpoint = "api/employmentStatus";
	
	
	public Response addEmploymentStatus() {
		return RestClient.doPost("JSON", accessToken, baseURI,empStatusEndpoint, null, true,
				new EmploymentStatus(PropertyCache.getProperty(dataFileName, "employment_status1")), "POST");
	}
	
	public Response getEmploymentStatus() {
		return RestClient.doGet("JSON", accessToken, baseURI,empStatusEndpoint, null, true);
	}

	public Response updateEmploymentStatus(Map<String,String> param, Object obj) {
		return RestClient.doPost("JSON", accessToken, baseURI, empStatusEndpoint, param, true, obj, "PATCH");

	}
}
