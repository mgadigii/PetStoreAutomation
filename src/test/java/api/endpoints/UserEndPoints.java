package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Created to perform Create,Read,Update,Delete requests to the user API
public class UserEndPoints {

	public static Response createUser(User payload)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
		return response ;
		
	}
	
	public static Response readUser(String userName)
	{
		Response response = given()
			.pathParams("userName", userName)
		.when()
			.get(Routes.get_url);
		return response ;
	}
	
	
	public static Response updateUser(String userName,User payload)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("userName", userName)
			.body(payload)
		.when()
			.put(Routes.update_url);
		return response ;
		
	}
	
	public static Response DeleteUser(String userName)
	{
		Response response = given()
			.pathParam("userName", userName)
			.when()
			.delete(Routes.update_url);
		return response ;
		
	}
}
