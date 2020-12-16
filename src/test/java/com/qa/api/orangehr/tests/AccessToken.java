package com.qa.api.orangehr.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.orangehr.util.PropertyCache;
import com.qa.api.orangehr.util.TestUtil;

import io.restassured.response.Response;


public class AccessToken {
	TestUtil testutil = new TestUtil();
	
	@Test
	public void getAccessTokenTest(){
		Response response = testutil.getAccessToken();
		String accessToken = response.path("access_token").toString();
		PropertyCache.setProperty("data.properties","access_token" , accessToken);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.path("token_type"), "Bearer");
	}

}
