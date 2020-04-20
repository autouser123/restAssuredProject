package com.employee.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import com.employee.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Listeners(com.employee.Utilities.Listeners.class)
public class TC_001_GET_ALL_Employee extends TestBase {

	public static Logger logger;
	
	//@BeforeClass
	public void testSetup() {
			
		logger = LogManager.getLogger(TC_001_GET_ALL_Employee.class);
		//logger = Logger.getLogger("devpinoyLogger");
		//logger = Logger.getLogger("EmployeeRestAPI");
		//PropertyConfigurator.configure("Log4j.properties");
		//logger.setLevel(Level.DEBUG);
		logger.warn("dummy");
		logger.error("error");
		logger.fatal("fatal");
		
	}
	
	@BeforeTest
	void getAllEmployees() {
		logger = LogManager.getLogger(TC_001_GET_ALL_Employee.class);
		logger.info("******** Started TC_001_GET_ALL_Employee ********");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		//Request object
		httpRequest = RestAssured.given();
		
		//Response object
		response = httpRequest.request(Method.GET,"/employees");
			
	}
	@Test
	void checkResponse() {
		logger.info("******** Checking response body ********");
		String responseBody = response.getBody().asString();
		logger.info("Response body -->"+ responseBody);
		Assert.assertTrue(responseBody!=null);
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
	void checkContentLenght() {
		logger.info("******** Checking content length ********");
		String contentLength = response.header("Content-Length");
		logger.info("contentLength -->"+contentLength );
		if(Integer.parseInt(contentLength)<100)
			logger.warn("Content length is less than 100");
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	}
	
	@AfterClass
	void tearDown() {
		logger.info("******** Finished TC_001_GET_ALL_Employee ********");
	}
	
}
