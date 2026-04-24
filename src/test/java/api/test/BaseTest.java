package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;

import api.utilities.ExtentReportManager;

public class BaseTest {
	public static ExtentReports extent;

	@BeforeSuite(alwaysRun = true)
	public void initializeSuite() {
		System.out.println("Before Suite running");
		extent = ExtentReportManager.getInstance();
	}

	@AfterSuite(alwaysRun = true)
	public void endSuite() {
		extent.flush();
	}
}
