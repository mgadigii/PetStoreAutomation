package api.test;

import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserTests {
	Faker faker;
	User userPayload;
	String username;
	
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
		
		
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("********** Creating user **********");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("********** user is created **********");
	}
	
	@Test(priority=2)
	public void getUserByName()
	{
		logger.info("********** Reading user info  **********");
		System.out.println(this.userPayload.getUsername());
	String username = this.userPayload.getUsername();
		
		Response response = UserEndPoints.readUser(username);
		response.then().log().all();
		//Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********** User info is displayed  **********");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("********** Updating user  **********");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********** User updated  **********");
		
		// Check response after update
		
		Response response_afterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		response_afterUpdate.then().log().all();
		//Assert.assertEquals(response_afterUpdate.getStatusCode(), 200);
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("********** Deleting user  **********");
		Response response = UserEndPoints.DeleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********** User deleted  **********");
		
	}

}
