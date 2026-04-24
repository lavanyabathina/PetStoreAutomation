package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*Created to perform user Create, Read, Update and Delete operations
on User API

*/
public class UserEndpoint {
	
	public static Response createUser(User payload) {
		
		System.out.println("User Payload in CreateUser is:" + payload.toString());
		Response response = given().contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when().post(Routes.user_post_url);
		return response;
		
	}

	public static Response readUser(String username) {
		
		
		Response response = given().pathParam("username",username)
				.when().get(Routes.user_get_url);
		return response;
		
	}
	
     public static Response updateUser( String username,User payload) {
 		System.out.println("User Payload in UpdateUser is:" + payload.toString());

		
		Response response = given().contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", username)
		.body(payload)
		.when().put(Routes.user_update_url);
		return response;
		
	}
     
     public static Response deleteUser(String username) {
 		
 	
 		Response response = given().pathParam("username",username)
 				.when().delete(Routes.user_delete_url);
 		return response;
 		
 	}

}
