package api.endpoints;


/*
 * 
 * Base URL: https://petstore.swagger.io/v2
 * Create user(post) - https://petstore.swagger.io/v2/user/
   Get user (get)- https://petstore.swagger.io/v2/user/{username}
   Update user (put)- https://petstore.swagger.io/v2/user/{username}
   Delete user(delete) -https://petstore.swagger.io/v2/user/{username}
 */


public class Routes {
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	//User model
	public static String user_post_url = base_url + "/user/";
	public static String user_get_url = base_url + "/user/{username}";
	public static String user_update_url = base_url + "/user/{username}";
	public static String user_delete_url = base_url + "/user/{username}";


}
