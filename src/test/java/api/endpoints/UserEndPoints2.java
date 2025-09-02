package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Created to perform Create,Read,Update,Delete requests to the user API
public class UserEndPoints2 {

	// method created for getting URL's from Properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // Load Properties file
		return routes;
	}
	public static Response createUser(User payload)
	{
		String posturl = getURL().getString("post_url");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(posturl);
		return response ;
		
	}
	
	public static Response readUser(String userName)
	{
		String geturl = getURL().getString("get_url");
		Response response = given()
			.pathParams("userName", userName)
		.when()
			.get(geturl);
		return response ;
	}
	
	
	public static Response updateUser(String userName,User payload)
	{
		String updateurl = getURL().getString("update_url");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("userName", userName)
			.body(payload)
		.when()
			.put(updateurl);
		return response ;
		
	}
	
	public static Response DeleteUser(String userName)
	{
		String deleteurl = getURL().getString("delete_url");
		Response response = given()
			.pathParam("userName", userName)
			.when()
			.delete(deleteurl);
		return response ;
		
	}
}
