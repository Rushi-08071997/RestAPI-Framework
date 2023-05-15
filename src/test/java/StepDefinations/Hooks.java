package StepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

//creating hooks class so that we can run our test indiavidually as well

public class Hooks {
	
	
	@Before("@Deleteplace") 
	public void beforexcenario() throws IOException {
		
		StepDefination m=new StepDefination(); 
		
		//providing condition so that itwill only trigger when we dnt have the prerequisite which is required
		if(StepDefination.place_id==null) {
		
		
		m.add_place_payload_with("Rushi", "ABC", "Asia");
		m.user_calls_an_using_http_request("AddplaceAPI", "post");
		m.verify_if_place_id_generated_maps_to_using("Rushi", "GetplaceAPI");
		
		
		}
		
	}

	
	
	

}
