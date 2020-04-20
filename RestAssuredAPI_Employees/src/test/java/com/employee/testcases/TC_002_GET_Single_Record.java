package com.employee.testcases;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.employee.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Listeners(com.employee.Utilities.Listeners.class)
public class TC_002_GET_Single_Record extends TestBase {

	public static org.apache.logging.log4j.Logger logger;
	
	@BeforeClass
	public void testSetup() {
			
		
				//Logger.getLogger("devpinoyLogger");
		//logger = Logger.getLogger("EmployeeRestAPI");
		//PropertyConfigurator.configure("Log4j.properties");
		//logger.setLevel(Level.DEBUG);

		
	}
	
	@BeforeTest
	void getAllEmployees() {
		logger = LogManager.getLogger(TC_002_GET_Single_Record.class);
		logger.info("******** Started TC_002_GET_Single_Record ********");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		//Request object
		httpRequest = RestAssured.given();
		
		//Response object
		response = httpRequest.request(Method.GET,"/employee/"+empID);
			
	}
	@Test
	void checkResponse() {
		logger.info("******** Checking response body ********");
		String responseBody = response.getBody().asString();
		logger.info("Response body -->"+ responseBody);
		Assert.assertEquals(true,responseBody.contains(empID));
	}
	
	@Test
	void checkStatusCode() {
		logger.info("******** Checking status code ********");
		int statusCode = response.getStatusCode();
		logger.info("Status code -->"+ statusCode);
		Assert.assertEquals(200, statusCode);
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
	
	@Test
	void checkContentType() {
		logger.info("******** Checking Content Type********");
		String contentType = response.header("Content-Type");
		logger.info("ContentType -->"+contentType);		
		Assert.assertEquals("application/json;charset=utf-8",contentType);
	}
	
	@AfterClass
	void tearDown() {
		logger.info("******** Finished TC_002_GET_Single_Record ********");
	}
	
}
