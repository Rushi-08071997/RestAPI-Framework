package Resources;

import java.util.ArrayList;
import java.util.List;

import POJO.Addplace;
import POJO.Location;

public class TestBuilddata {

	//creating method for Addplace payload
	public Addplace AddplacePayload(String name,String address,String language) {

		Addplace p = new Addplace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(name);
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		return p;
	}
	
	//creating method for deleteplace payload
	public String DeleteplacePayload(String place_id) {
		
		return "{\"place_id\":\""+place_id+"\"}";
	}

}
