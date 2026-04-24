package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;

import api.endpoints.UserEndpointFromPropertiesFile;
import api.payload.User;
import api.utilities.ExtentReportManager;
import io.restassured.response.Response;

public class UserTestsWithUrlsFromPropertiesFile extends BaseTest{
		
Faker fk ;
User userPayload;


  protected Logger logger = LogManager.getLogger(this.getClass());



 @BeforeClass
  public void setupData() {
	

	 
	
	 fk = new Faker();
	 userPayload = new User();
	
	 userPayload.setId(fk.idNumber().hashCode());
	 userPayload.setUsername(fk.name().name());
	 userPayload.setFirstName(fk.name().firstName());
	 userPayload.setLastName(fk.name().lastName());
	 userPayload.setEmail(fk.internet().safeEmailAddress());
	 userPayload.setPassword(fk.internet().password(5,10));
	 userPayload.setPhone(fk.phoneNumber().cellPhone());
	 //userPayload.setUserStatus(0);
	 

  }

	
	@Test(priority=1 , groups= {"functional"})
	void testCreateUser() {
		
		ExtentTest test = extent.createTest("Create User Test ");
		logger.info("Create user test started");
		Response response= UserEndpointFromPropertiesFile.createUser(userPayload);
		response.then().log().all();
		
		try {
		Assert.assertEquals(response.getStatusCode(), 200);
		test.pass("Test Passed");
		}
		catch(AssertionError e) {
		test.fail("Test Failed");
		throw e;
		}
		
		logger.info("Creating user test end.");

	}

	@Test(priority=2 , groups= {"functional"})
	void testGetUserByName() {
		ExtentTest test = extent.createTest("Get User Test");
		logger.info("Get user test started");


		String username = this.userPayload.getUsername();
		Response response = UserEndpointFromPropertiesFile.readUser(username);
		
		response.then().log().all();
		
		try {
		Assert.assertEquals(response.getStatusCode(), 200);
		String userInResponse = response.jsonPath().getString("username");
		Assert.assertEquals(userInResponse, username);
		
		test.pass("Test Passed");
		
    	}
	   catch(AssertionError e) {
		   test.fail("Test Failed");
		   throw e;

	    }
		logger.info("Get user test End");

	}
	
	@Test(priority=3 , groups= {"functional"})
	void testUpdateUser() {
		
		ExtentTest test = extent.createTest("Update User Test");

		logger.info("Update user test started");

		 userPayload.setFirstName(fk.name().firstName());
		 userPayload.setLastName(fk.name().lastName());
		 userPayload.setEmail(fk.internet().safeEmailAddress());
		
		 String username = userPayload.getUsername();
		 Response updateresponse = UserEndpointFromPropertiesFile.updateUser(username,userPayload);
		 updateresponse.then().log().all();
		 Assert.assertEquals(updateresponse.getStatusCode(), 200);
		 Assert.assertEquals(Integer.parseInt(updateresponse.jsonPath().getString("message")), this.userPayload.getId());
		
         //Validate User after update using readUser
		 System.out.println("Validate User after update using readUser:");
			Response getresponse = UserEndpointFromPropertiesFile.readUser(username);
			
			getresponse.then().log().all();
			
			try {
			Assert.assertEquals(getresponse.getStatusCode(), 200);
			
			Assert.assertEquals(getresponse.jsonPath().getString("username"), username);
			Assert.assertEquals(getresponse.jsonPath().getString("firstName"), this.userPayload.getFirstName());
			Assert.assertEquals(getresponse.jsonPath().getString("lastName"), this.userPayload.getLastName());
			Assert.assertEquals(getresponse.jsonPath().getString("email"), this.userPayload.getEmail());
			test.pass("Test Passed");
			
			}
			catch(AssertionError e) {
				test.pass("Test Failed");
				throw e;

			}
			logger.info("Update user test end");

	}
	
	@Test(priority=4 , groups= {"functional"})
	void testDeleteUserByName() {
		ExtentTest test = extent.createTest("Delete User Test");
		logger.info("Delete user test started");

		String username = this.userPayload.getUsername();
		Response response = UserEndpointFromPropertiesFile.deleteUser(username);
		
		response.then().log().all();
		try {
			Assert.assertEquals(response.getStatusCode(), 200);
			String userInResponse = response.jsonPath().getString("message");
			Assert.assertEquals(userInResponse, username);
			test.pass("Test Passed");
		} catch (AssertionError e) {
			test.fail("Test Failed: " + e.getMessage());
			throw e; // rethrow to let TestNG mark the test as failed
		}
		logger.info("Delete user test end");

	}



}
