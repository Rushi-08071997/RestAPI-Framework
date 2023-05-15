Feature: Verifyplace Api call

@Addplace @Regression
Scenario Outline: verify if place is being succesfully added using addplace Api
Given Add place payload with "<name>" "<address>" "<language>"
When User calls an "AddplaceAPI" using "post" Http request
Then Api call got success with status code 200
And "status" in the response body is "OK"
And "scope" in the response body is "APP" 
And verify if place_id generated maps to "<name>" using "GetplaceAPI"


Examples:
	|name	   |address         |language|
	|RABhouse|West Line center|English |
	|XYZhouse|East Line center|french  |
	
@Deleteplace @                  
Scenario:Delete Place
Given Delete place payload
When User calls an "DeleteplaceAPI" using "post" Http request
Then Api call got success with status code 200
And "status" in the response body is "OK"



