package Resources;

public enum APIResource {
	
	//in enum we can declare single line method like this directly separated by comma & end up[ using semicolumn;
	AddplaceAPI("/maps/api/place/add/json"),
	GetplaceAPI("/maps/api/place/get/json"),
	DeleteplaceAPI("/maps/api/place/delete/json");
	
	//declaring this variable & assigning value to it using constructor as variable of constructor having scope limited to constructor
	public String Resource;

	APIResource(String Resource) {
		// TODO Auto-generated constructor stub
		
		this.Resource=Resource;
		
	}
	
	//declaring method to return resource value
	public String GetResource() {
		
		return Resource;
	}

}
