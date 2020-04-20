package com.employee.testcases;

import org.apache.logging.log4j.LogManager;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.employee.base.TestBase;
import com.employee.Utilities.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Listeners(com.employee.Utilities.Listeners.class)
public class TC_003_POST_Employee_Record extends TestBase {

	public static org.apache.logging.log4j.Logger logger;
	RequestSpecification httpRequest;
	Response response;
	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empAge = RestUtils.empAge();
	
	@BeforeClass
	public void testSetup() throws InterruptedException {
		logger = LogManager.getLogger(TC_003_POST_Employee_Record.class);
		logger.info("******** Started TC_003_POST_Employee_Record ********");
		
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		JSONObject requestParam = new JSONObject();
		requestParam.put("name", empName);
		requestParam.put("salary", empSalary);
		requestParam.put("age", empAge);
		
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(requestParam.toJSONString());  //attached data to the request

		//Response object
		Response response = httpRequest.request(Method.POST,"/create");
		Thread.sleep(10000);
	}
	
	@Test
	void checkResponse() {
		logger.info("******** Checking response body ********");
		String responseBody = response.getBody().asString();
		logger.info("Response body -->"+ responseBody);
		Assert.assertEquals(true,responseBody.contains(empName));
		Assert.assertEquals(true,responseBody.contains(empSalary));
		Assert.assertEquals(true,responseBody.contains(empAge));
	}
	
	@Test
	void checkStatusCode() {
		logger.info("******** Checking status code ********");
		int statusCode = response.getStatusCode();
		logger.info("Status code -->"+ statusCode);
		Assert.assertEquals("200", statusCode);
	}
	
	@Test
	void checkResponseTime() {
		logger.info("******** Checking response time ********");
		long responseTime = response.getTime();
		logger.info("Response time -->"+ responseTime);
		if(responseTime>=2000)
			logger.info("Response time is greater than 2000");
		Assert.assertTrue(responseTime<=2000);
	}

	@Test
	void checkStatusLine() {
		logger.info("******** Checking status line ********");
		String statusLine = response.getStatusLine();
		logger.info("statusLine -->"+statusLine );		
		Assert.assertEquals("HTTP/1.1 200 OK",statusLine);
	}
	
	//@Test
	void checkContentType() {
		logger.info("******** Checking Content Type********");
		String contentType = response.header("Content-Type");
		logger.info("ContentType -->"+contentType);		
		Assert.assertEquals("application/json;charset=utf-8",contentType);
	}
	
	@AfterClass
	void tearDown() {
		logger.info("******** Finished TC_003_POST_Employee_Record ********");
	}
	
}
