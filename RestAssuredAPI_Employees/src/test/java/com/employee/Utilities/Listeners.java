package com.employee.Utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter{

	public ExtentSparkReporter htmlReporter;  //look and feel of report
	public ExtentReports extent;				//report header
	public ExtentTest test;						//create new entry in test


	public void onStart(ITestContext testContext)					
	{		
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/myReport.html"); //specify the loaction
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Rest API testing Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Host Name", "Local host");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "manish");
		
	}		
	
	public void onTestSuccess(ITestResult result) 					
	{		
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "Test case pass is " +result.getName());
	}
	
	public void onTestFailure(ITestResult result) 					
	{		
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case failed is " +result.getName());
		test.log(Status.FAIL, "Test case failed is " +result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result) 					
	{		
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "Test skipped is " +result.getName());
	}
	
	public void onFinish(ITestContext testContext) 					
	{		
		extent.flush();      		
	}		





}
