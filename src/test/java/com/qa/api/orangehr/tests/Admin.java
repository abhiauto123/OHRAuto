package com.qa.api.orangehr.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.api.orangehr.modules.AdminModule;
import com.qa.api.orangehr.pojo.DeleteJobCategory;
import com.qa.api.orangehr.pojo.EmploymentStatus;
import com.qa.api.orangehr.pojo.EmploymentStatusDelete;
import com.qa.api.orangehr.pojo.JobCategory;
import com.qa.api.orangehr.pojo.JobSpecification;
import com.qa.api.orangehr.pojo.JobTitle;
import com.qa.api.orangehr.pojo.JobTitleDelete;
import com.qa.api.orangehr.restclient.RestClient;
import com.qa.api.orangehr.util.PropertyCache;
import com.qa.api.orangehr.util.TestUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class Admin {

	AdminModule adminModule = new AdminModule();

	@Test(priority=1)
	public void employmentStatusCheck() {
		Assert.assertEquals(adminModule.addEmploymentStatus().getStatusCode(), 201);
	}

	@Test(priority=2)
	public void getemploymentStatusCheck() {
		Assert.assertEquals(adminModule.getEmploymentStatus().path("data[0].name"),
				PropertyCache.getProperty("data.properties", "employment_status1"));
	}

	@Test(priority=3)
	public void updateEmployeeStatusCheck() {
		Map<String, String> param = new HashMap<>();
		param.put("id", adminModule.getEmploymentStatus().path("data[0].id").toString());
		Assert.assertEquals(
				adminModule.updateEmploymentStatus(param, new EmploymentStatus("UpdatedEmpStatus")).getStatusCode(),
				200);
	}
	
	@Test(priority=4)
	public void deleteEmployeeStatusCheck() {
		List<String> data = new ArrayList<>();
		JsonPath jsonPath = TestUtil.getJsonPathObjectfromResponse(adminModule.getEmploymentStatus());
		for (int i=0;i<=jsonPath.getInt("data.size")-1;i++){
			data.add(jsonPath.getString("data["+i+"].id"));
		}
		Assert.assertEquals(adminModule.deleteEmploymentStatus(new EmploymentStatusDelete(data)).getStatusCode(), 204);
		Response response = adminModule.getEmploymentStatus();
		JsonPath jpath = TestUtil.getJsonPathObjectfromResponse(response);
		Assert.assertEquals(jpath.getInt("data.size"), 0);
	}

	@Test(priority = 5)
	public void addJobCategoryCheck() {
		Response response = adminModule.addJobCategory(PropertyCache.getProperty("data.properties", "job_category"));
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertEquals(response.path("data.name").toString(),
				PropertyCache.getProperty("data.properties", "job_category"));
	}
	
	@Test(priority = 6)
	public void getJobCategoryCheck() {
		Response response = adminModule.getJobCategory();
		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath jsonpath = TestUtil.getJsonPathObjectfromResponse(response);
		boolean flag = false;
		for (int i = 0; i <= jsonpath.getInt("data.size"); i++) {
			if (jsonpath.getString("data[" + i + "].name")
					.equalsIgnoreCase(PropertyCache.getProperty("data.properties", "job_category"))) {
				flag = true;
				break;
			}
		}
		Assert.assertEquals(flag, true);
	}

	@Test(priority = 7)
	public void updateJobCategoryCheck() {
		Map<String, String> paramsMap = new HashMap<>();
		paramsMap.put("id", adminModule.addJobCategory(PropertyCache.getProperty("data.properties", "job_category2"))
				.path("data.id").toString());
		Response response = adminModule.updateJobCategory(paramsMap, new JobCategory("jobCategoryUpdate"));
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.path("messages.success").toString(), "Successfully Saved");
	}
	
	@Test(priority = 8)
	public void deleteJobCategoryCheck() {
		JsonPath jasonpath = TestUtil.getJsonPathObjectfromResponse(adminModule.getJobCategory());
		List<String> id = new ArrayList<>();
		for (int i = 0; i <= jasonpath.getInt("data.size") - 1; i++) {
			id.add(jasonpath.getString("data[" + i + "].id"));
		}
		Assert.assertEquals(adminModule.deleteJobCategory(new DeleteJobCategory(id)).getStatusCode(), 204);
		Assert.assertEquals(TestUtil.getJsonPathObjectfromResponse(adminModule.getJobCategory()).getInt("data.size"),
				0);
	}
	
	@Test(priority = 9)
	public void addJobTitlesCheck() {
		String fileName = "data.properties";
		Response response = adminModule.addJobTitle(new JobTitle(PropertyCache.getProperty(fileName, "jobTitleName"),
				PropertyCache.getProperty(fileName, "jobDescription"), PropertyCache.getProperty(fileName, "note"),
				PropertyCache.getProperty(fileName, "currentJobSpecification"),
				new JobSpecification(PropertyCache.getProperty(fileName, "base64"),
						PropertyCache.getProperty(fileName, "filename"),
						PropertyCache.getProperty(fileName, "filesize"),
						PropertyCache.getProperty(fileName, "filetype"))));
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertEquals(TestUtil.getJsonPathObjectfromResponse(response).getString("data.jobTitleName"),
				PropertyCache.getProperty(fileName, "jobTitleName"));
	}
	
	@Test(priority = 10)
	public void getJobTitlesCheck() {
		Response response = adminModule.getJobTitles();
		boolean flag = false;
		JsonPath jsonpath = TestUtil.getJsonPathObjectfromResponse(response);
		for (int i = 0; i <= jsonpath.getInt("data.size"); i++) {
			if (jsonpath.getString("data[" + i + "].jobTitleName")
					.equalsIgnoreCase(PropertyCache.getProperty("data.properties", "jobTitleName"))) {
				flag = true;
				break;
			}
		}
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 11)
	public void updateJobTitlesCheck(){
		String fileName = "data.properties";
		Map<String,String> paramsMap= new HashMap<>();
		Response response1 = adminModule.getJobTitles();
		JsonPath jsonpath = TestUtil.getJsonPathObjectfromResponse(response1);
		for(int i =0;i<=jsonpath.getInt("data.jobTitleName.size")-1;i++){
			if(jsonpath.getString("data["+i+"].jobTitleName").equalsIgnoreCase(PropertyCache.getProperty("data.properties","jobTitleName"))){
				paramsMap.put("id", jsonpath.getString("data["+i+"].id"));
			}
		}
		
		Response response = adminModule.updateJobTitles(paramsMap,
				new JobTitle(PropertyCache.getProperty(fileName, "jobTitleName1"),
						PropertyCache.getProperty(fileName, "jobDescriptionUpdated"),
						PropertyCache.getProperty(fileName, "note"),
						PropertyCache.getProperty(fileName, "currentJobSpecification"),
						new JobSpecification(PropertyCache.getProperty(fileName, "base64"),
								PropertyCache.getProperty(fileName, "filename"),
								PropertyCache.getProperty(fileName, "filesize"),
								PropertyCache.getProperty(fileName, "filetype"))));
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.path("messages.success").toString(), "Successfully Saved");
	}
	
	@Test(priority = 12)
	public void deleteJobTitlesCheck() {
		List<String> id = new ArrayList<>();
		JsonPath jsonPath = TestUtil.getJsonPathObjectfromResponse(adminModule.getJobTitles());
		for (int i = 0; i <= jsonPath.getInt("data.size") - 1; i++) {
			id.add(jsonPath.getString("data[" + i + "].id"));
		}
		Assert.assertEquals(adminModule.deleteJobTitles(new JobTitleDelete(id)).getStatusCode(), 204);
		Assert.assertEquals(TestUtil.getJsonPathObjectfromResponse(adminModule.getJobTitles()).getInt("data.size"), 0);
	}
}
