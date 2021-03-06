package com.qa.api.orangehr.modules;


import java.util.Map;
import com.qa.api.orangehr.pojo.EmploymentStatus;
import com.qa.api.orangehr.pojo.JobCategory;
import com.qa.api.orangehr.restclient.RestClient;
import com.qa.api.orangehr.util.PropertyCache;
import com.qa.api.orangehr.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AdminModule extends TestUtil{
	
	TestUtil testutil = new TestUtil();
	String dataFileName = "data.properties";
	String accessToken=PropertyCache.getProperty(dataFileName, "access_token");
	String baseURI = PropertyCache.getProperty(dataFileName, "baseURI");
	String empStatusEndpoint = "api/employmentStatus";
	String jobCategoriesEndpoint ="api/jobCategories";
	String jobTitles = "api/jobTitles";
	
	
	public Response addEmploymentStatus() {
		return RestClient.doPost("JSON", accessToken, baseURI, empStatusEndpoint, null, true,
				new EmploymentStatus(PropertyCache.getProperty(dataFileName, "employment_status1")), "POST");
	}

	public Response getEmploymentStatus() {
		return RestClient.doGet("JSON", accessToken, baseURI, empStatusEndpoint, null, true);
	}

	public Response updateEmploymentStatus(Map<String, String> param, Object obj) {
		return RestClient.doPost("JSON", accessToken, baseURI, empStatusEndpoint, param, true, obj, "PATCH");

	}
	
	public Response deleteEmploymentStatus(Object obj){
		return RestClient.doPost("JSON", accessToken, baseURI, empStatusEndpoint, null, true, obj, "DELETE");
	}

	public Response addJobCategory(String jobCategory) {
		return RestClient.doPost("JSON", accessToken, baseURI, jobCategoriesEndpoint, null, true,
				new JobCategory(jobCategory), "POST");

	}

	public Response getJobCategory() {
		return RestClient.doGet("JSON", accessToken, baseURI, jobCategoriesEndpoint, null, true);
	}

	public Response updateJobCategory(Map<String, String> paramsMap, Object obj) {
		return RestClient.doPost("JSON", accessToken, baseURI, jobCategoriesEndpoint, paramsMap, true, obj, "PATCH");

	}
	
	public Response deleteJobCategory(Object obj){
		return RestClient.doPost("JSON", accessToken, baseURI, jobCategoriesEndpoint, null, true, obj, "DELETE");
	}
	
	public Response addJobTitle(Object obj){
		return RestClient.doPost("JSON", accessToken, baseURI, jobTitles, null, true, obj, "POST");
		
	}
	
	public Response getJobTitles(){
		return RestClient.doGet("JSON", accessToken, baseURI, jobTitles, null, true);
	}
	
	public Response updateJobTitles(Map<String,String> paramsMap,Object obj){
		return RestClient.doPost("JSON", accessToken, baseURI, jobTitles, paramsMap, true, obj,"PATCH");
		
	}
	
	public Response deleteJobTitles(Object obj){
		return RestClient.doPost("JSON", accessToken, baseURI, jobTitles, null, true, obj, "DELETE");	
	}
}
 