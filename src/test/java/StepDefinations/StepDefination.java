package StepDefinations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


import POJO.Addplace;
import POJO.Location;
import Resources.APIResource;
import Resources.TestBuilddata;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

//inheriting utils class so wont need to create object to call any method
public class StepDefination extends Utils {
	//declaring var on global level as we need to use it in diff method
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	
	TestBuilddata build=new TestBuilddata(); //to use method from the class
	
	static String  place_id; //declaring it globally as we need to use it in differentnscenario & also making it static so that its value will not get null
	
	
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res=given().spec(ReqBodyspecification())
				   .body(build.AddplacePayload(name,address,language));
	}
	
	 @Given("^Delete place payload$")
	    public void delete_place_payload() throws Throwable {
		 
		 res=given().spec(ReqBodyspecification())
				   .body(build.DeleteplacePayload(place_id)); 
	    }

	
	@When("User calls an {string} using {string} Http request")
	public void user_calls_an_using_http_request(String Resource, String Method) {
		
		//here we are using value of method so we do not need to hardcode the values & use any value passed in feature file
		APIResource resourceAPI=APIResource.valueOf(Resource);
		System.out.println(resourceAPI.GetResource());
		
		if(Method.equalsIgnoreCase("post")) {
			resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			 response =res.when().post(resourceAPI.GetResource());
		}else if(Method.equalsIgnoreCase("get")) {
			resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			 response =res.when().get(resourceAPI.GetResource());
		}else if(Method.equalsIgnoreCase("delete")) {
			resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			 response =res.when().delete(resourceAPI.GetResource());
		}
		
				
		 
	}
	@Then("Api call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer Statuscode) {
		
	   assertEquals(response.statusCode(),200);
	    
	}
	@Then("{string} in the response body is {string}")
	public void in_the_response_body_is(String key, String value) {
	     
	    assertEquals(Getjson(response,key),value);
	    
	}
	
	@Then("verify if place_id generated maps to {string} using {string}")
	public void verify_if_place_id_generated_maps_to_using(String name, String Resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		place_id=Getjson(response,"place_id");
		res=given().spec(ReqBodyspecification()).queryParam("place_id",place_id);  //here we have added 
		user_calls_an_using_http_request(Resource, "get"); //calling this method as we need to post get call
		assertEquals(Getjson(response,"name"),name);
		
		
		
	}
	
	
	

	
}

