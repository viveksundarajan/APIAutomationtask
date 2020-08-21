package resources;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	public List<Integer> id;
	public List<String> albumlist;
	public List<String> emails;
	Response comments_response;
	
	
	public RequestSpecification requestSpecification() throws IOException
	{
		if (req==null)
		{
		PrintStream log=new PrintStream(new File("Logging.txt"));
		
		
		req=new RequestSpecBuilder().setBaseUri(getBaseURI("BaseURI")).setContentType(ContentType.JSON).
		addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		
		return req;
		
	}
		else 
			return req;
	}
	
	public static String getBaseURI(String key) throws IOException
	{
		FileInputStream file= new FileInputStream(System.getProperty("user.dir")+"//src\\test\\java\\resources\\global.properties");
		Properties property =new Properties();
		property.load(file);
		
		return property.getProperty(key);
		
		
	}
	
	public static String getJSONPath(String users, String username)
	{
		 String ID = null;
		JsonPath js=new JsonPath(users);
		int user_counts= js.getInt("users.size()");
		for(int i=0; i<user_counts; i++)
		{
			if(js.getString("["+i+"].username").equalsIgnoreCase(username))
{
			ID=js.getString("["+i+"].id");
				break;			
				
}
		}
		
		return ID;
	}
	
	public  List<Integer> getPostID(String posts)
	{
		id = new ArrayList<Integer>();
		 JsonPath js=new JsonPath(posts);
		 
		int number_of_posts= js.getInt("posts.size()");
		
	        for(int i=0; i<number_of_posts; i++)
			{
	        	int str=js.getInt("["+i+"].id");
	            id.add(str);
	        	}
	       
	        return id;
	}
	
	
	public List<String> getEmails(String key,String commentresource) throws IOException
	{
		APIResources comment_resource=APIResources.valueOf(commentresource);
		emails =new ArrayList<String>();
		int arrayindex=0;
		 
		
		System.out.println(id.size());
		 
			 for(int i=0; i<id.size();i++)
			 {
				 
				  comments_response= given().spec(requestSpecification()).queryParam("id", id.get(i)).when().get(comment_resource.getResource());
				  JsonPath js=new JsonPath(comments_response.asString());
				  String email= js.get("["+arrayindex+"]."+key+"".toString());
				  emails.add(email);
			 }
				 
				
				
				 
				 return emails;
	}
	
	public  List<String> getAlbums(String albums)
	{
		albumlist = new ArrayList<String>();
		
		 JsonPath js=new JsonPath(albums);
		 
		int number_of_albums= js.getInt("albums.size()");
		
	        for(int i=0; i<number_of_albums; i++)
			{
	        	String str=js.get("["+i+"].title".toString());
	        	System.out.println(str);
	        	albumlist.add(str);
	        	}
	       
	        return albumlist;
	}
}
