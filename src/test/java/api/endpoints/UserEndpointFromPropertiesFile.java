package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*Created to perform user Create, Read, Update and Delete operations
on User API

*/
public class UserEndpointFromPropertiesFile {
	
	static ResourceBundle getUrl() {
		
		ResourceBundle bundle = ResourceBundle.getBundle("routes");
		return bundle;
	}
	
	public static Response createUser(User payload) {
		
		String user_post_url=getUrl().getString("user_post_url");
		System.out.println("User Payload in CreateUser is:" + payload.toString());
		Response response = given().contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when().post(user_post_url);
		return response;
		
	}

	public static Response readUser(String username) {
		
		String user_get_url=getUrl().getString("user_get_url");

		Response response = given().pathParam("username",username)
				.when().get(user_get_url);
		return response;
		
	}
	
     public static Response updateUser( String username,User payload) {
 		System.out.println("User Payload in UpdateUser is:" + payload.toString());

		String user_update_url=getUrl().getString("user_put_url");

		Response response = given().contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", username)
		.body(payload)
		.when().put(user_update_url);
		return response;
		
	}
     
     public static Response deleteUser(String username) {
 		
 		String user_delete_url=getUrl().getString("user_delete_url");

 		Response response = given().pathParam("username",username)
 				.when().delete(user_delete_url);
 		return response;
 		
 	}

}
