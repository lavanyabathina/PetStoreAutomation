package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import api.endpoints.UserEndpointFromPropertiesFile;
import api.payload.User;
import api.utilities.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;

public class DataDrivenUserTests extends BaseTest {

	  protected Logger logger = LogManager.getLogger(this.getClass());

	@Test(priority=1 , dataProvider="Data" , dataProviderClass=api.utilities.DataProviders.class , groups = {"datadriven"})

	public void testUserCreateDatadriven(String userId, String userName, String FirstName , String LastName, String Email, String Password, String Phone) {
		
		System.out.println("In testUserCreateDatadriven");
		System.out.println("userId is" + userId);

		ExtentTest test = extent.createTest("Create User Test - " + userName);
		
		logger.info("Create user data driven test started");
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);
		
		Response response = UserEndpointFromPropertiesFile.createUser(userPayload);
		response.then().log().all();
		
		if(response.getStatusCode() == 200) {
			test.pass("Test Passed");
		} else {
			test.fail("Test Failed - Status code: " + response.getStatusCode());
		}
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Create user data driven test end");

		
		
	}

  @Test(priority=2, dataProvider="Usernames", dataProviderClass=api.utilities.DataProviders.class )
  public void testGetUserDataDriven(String username) {
		ExtentTest test = extent.createTest("Get User Test - " + username);
		logger.info("Get user data driven test started");

		Response response = UserEndpointFromPropertiesFile.readUser(username);
		response.then().log().all();
		if(response.getStatusCode() == 200) {
			test.pass("Test Passed");
		} else {
			test.fail("Test Failed - Status code: " + response.getStatusCode());
		}
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Get user data driven test end");

}
  
  @Test(priority=3, dataProvider="Usernames", dataProviderClass=api.utilities.DataProviders.class  )
  public void testDeleteUserDataDriven(String username) {
		ExtentTest test = extent.createTest("Delete User Test - " + username);
		logger.info("Delete user data driven test started");

		Response response = UserEndpointFromPropertiesFile.readUser(username);
		response.then().log().all();
		if(response.getStatusCode() == 200) {
			test.pass("Test Passed");
		} else {
			test.fail("Test Failed - Status code: " + response.getStatusCode());
		}
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Delete user data driven test end");

}
  

  
}



