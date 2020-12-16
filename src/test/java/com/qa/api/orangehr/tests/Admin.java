package com.qa.api.orangehr.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.orangehr.modules.AdminModule;
import com.qa.api.orangehr.pojo.EmploymentStatus;
import com.qa.api.orangehr.util.PropertyCache;



public class Admin {

	AdminModule adminModule = new AdminModule();

	@Test
	public void employmentStatusCheck() {
		Assert.assertEquals(adminModule.addEmploymentStatus().getStatusCode(), 201);
	}

	@Test
	public void getemploymentStatusCheck() {
		Assert.assertEquals(adminModule.getEmploymentStatus().path("data[0].name"),
				PropertyCache.getProperty("data.properties", "employment_status1"));
	}

	@Test
	public void updateEmployeeStatusUpdate() {
		Map<String, String> param = new HashMap<>();
		param.put("id", adminModule.getEmploymentStatus().path("data[0].id").toString());
		Assert.assertEquals(
				adminModule.updateEmploymentStatus(param, new EmploymentStatus("UpdatedEmpStatus")).getStatusCode(),
				200);
	}

}
