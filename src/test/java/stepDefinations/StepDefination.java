package stepDefinations;

import static io.restassured.RestAssured.given;

import files.Payload;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.Assert;


public class StepDefination extends Utils{
	
	RequestSpecification res;
	
	Response response;
	String users;
	 String userid;
	 Response comments_response;
	 Response albums_response;
	
	 
	


	 @Given("^get the list of users with \"([^\"]*)\"$")
    public void add_place_payload(String resource) throws Throwable {
    	
    	
    	APIResources user_resource=	APIResources.valueOf(resource);

  response= given().spec(requestSpecification()).when().get(user_resource.getResource());
	
    }

	 
	 @When("^retreive userID \"([^\"]*)\"$")
	    public void retreive_user_something(String username) throws Throwable {
		 System.out.println(username);
		 users= response.asString();
	 userid= getJSONPath(users,username);
	
		 
	        
	    }


	 @And("^get the posts from the user with \"([^\"]*)\"$")
	    public void get_the_posts_from_the_user_with(String resource) throws Throwable {
		 
		 APIResources post_resource=APIResources.valueOf(resource);
		 Response Posts_response= given().spec(requestSpecification()).queryParam("userId", userid).when().get(post_resource.getResource());
	        String posts=Posts_response.asString();
	id= getPostID(posts);
	     
	        

	        
	        
	    }

	 @And("^retreive the \"([^\"]*)\" from comments using \"([^\"]*)\"$")
	    public void retreive_the_email_from_comments_using_something(String key,String resource) throws Throwable {
			 
			
		 emails= getEmails(key,resource);
			 System.out.println(emails);
		 }
	
		 
	    
	    
	    @Then("^email validation is success$")
	    public void email_validation_is_success() throws Throwable {
	       
	       
	    	boolean flag=emails.stream().allMatch(s-> s.contains("@") && s.contains("."));
	    	
	    	Assert.assertTrue(flag);
	    	
	    	
	    }

	    @Then("^verify status code in response is (\\d+)$")
	    public void verify_the_status_code_in_response_is_something(int expectecvalue) throws Throwable {
	    	System.out.println(expectecvalue);
	      response.then().statusCode(expectecvalue);
	    }
	    
	    @And("^get the albums from the user with \"([^\"]*)\"$")
	    public void get_the_albums_from_the_user_with_something(String resource) throws Throwable {
	    	 APIResources album_resource=APIResources.valueOf(resource);
			
	    	 albums_response= given().spec(requestSpecification()).queryParam("userId", userid).when().get(album_resource.getResource());
	    	  String albums=albums_response.asString();
	    	  albumlist= getAlbums(albums);
	    		System.out.println(albumlist);
	    }
	    
	    @Then("^verify the title \"([^\"]*)\" is present$")
	    public void verify_the_title_something_is_present(String expectedvalue) throws Throwable {
	    	
	    	boolean flag=albumlist.stream().anyMatch(s->s.contains(expectedvalue));
	    	Assert.assertTrue(flag);
	       
}
	   
	    
	    @And("^\"([^\"]*)\" name in response header is \"([^\"]*)\"$")
	    public void something_name_in_response_is_something(String servername, String value) throws Throwable {
	    	
	    	
	    	
	    	albums_response.then().assertThat().header(servername, value);
	    	
	       
	    }


		

}
