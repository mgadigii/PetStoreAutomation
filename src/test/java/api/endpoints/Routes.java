package api.endpoints;

//swagger URI : https://petstore.swagger.io/
//Create user(Post) : https://petstore.swagger.io/v2/user
//Get user (Get) : https://petstore.swagger.io/v2/user/{username}
//Update user(Put) : https://petstore.swagger.io/v2/user/{username}
//Delete user(Delete) : https://petstore.swagger.io/v2/user/{username}
public class Routes {
	public static String base_url = "https://petstore.swagger.io/v2";
	
	// User module
	public static String post_url = base_url + "/user";
	public static String get_url = base_url + "/user/{userName}";
	public static String update_url = base_url + "/user/{userName}";
	public  static String delete_url = base_url + "/user/{userName}";
	
	// Store module
	
		// here you have to create Store module url's
	
	// Pet module
	
		// here you have to create Pet module url's

}
