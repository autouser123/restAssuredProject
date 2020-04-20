package com.employee.base;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.BeforeClass;
import org.testng.log4testng.Logger;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID="12";
	public static Logger logger;
	
	
	/*@BeforeClass
	public void testSetup() {
	
		logger = (Logger) LogManager.getLogger("EmployeeRestAPI");
				//Logger.getLogger("devpinoyLogger");
		//logger = Logger.getLogger("EmployeeRestAPI");
		//PropertyConfigurator.configure("Log4j.properties");
		//logger.setLevel(Level.DEBUG);

		
	}*/
	

}
