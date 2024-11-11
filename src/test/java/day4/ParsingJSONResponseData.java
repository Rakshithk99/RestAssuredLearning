package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJSONResponseData {
	@Test(priority=1)
	void testJsonResponse() {
		//Approach1
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("data[5].last_name", equalTo("Howell")); //get path using JsonPathFinder.com
	}
	
	@Test
	void testJsonResponse2() {
		//approach2
		Response res = given()
			.contentType("Content-Type.JSON")
		.when()
		.get("https://reqres.in/api/users?page=2");
	
		
		Assert.assertEquals(res.getStatusCode(),200);  //validation 1
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
		String lastname = res.jsonPath().get("data[5].last_name").toString();
		Assert.assertEquals(lastname, "Howell");
	}
	
	@Test
	void testJsonResponseBodyData() {
		Response res = given()
				.contentType(ContentType.JSON)
			.when()
			.get("https://reqres.in/api/users?page=2");
		 
		//JSONObject class
		JSONObject jo = new JSONObject(res.asString());  //converting response to json object type
		/*----------------------------------------------------------
		//For xml response:
		//XmlPath xmlobj = new XmlPath(res.asString());
		//List<String> travelers = xmlobj.getList("TravellerInformationResponse.travelers.TravelerInformation");
		//Assert.assetEquals(travelers.size(),10); 
		//Verify traveler name is present in the response
		//List<String> traveler_names = xmlobj.getList("TravellerInformationResponse.travelers.TravelerInformation.name");
		//boolean status=false;
		//for(String travelername:traveler_names){
			//if(travelername.equals("Vijay"){
		//status=true;
		//break;
		//}
		
	//}
		//Assert.assertEquals(status,true);
		--------------------------------------------------------------*/
		
		/*for(int i=0;i<jo.getJSONArray("data").length();i++) {
			String lastname = jo.getJSONArray("data").getJSONObject(i).get("last_name").toString();
			System.out.println(lastname);
			
		}*/
		boolean status = false;
		for(int i=0;i<jo.getJSONArray("data").length();i++) {
			String lastname = jo.getJSONArray("data").getJSONObject(i).get("last_name").toString();//If the response is in JSON Object
			/*
			 * If the response is in JSON Array
			 * JSONArray jarr = new JSONArray(res.asString());
			 * jarr.getJSONObject(i).get("last_name")
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
			System.out.println(lastname);
			if(lastname.equals("Ferguson")) {
				status=true;
				break;
			}
			
		}
		Assert.assertEquals(status, true);
		
		//validate total sum of id
		double totalId=0;
		for(int i=0;i<jo.getJSONArray("data").length();i++) {
			String id = jo.getJSONArray("data").getJSONObject(i).get("id").toString();
			totalId = totalId+Double.parseDouble(id);
		
			
			
		}
		System.out.println("Sum of id is: "+totalId);
		Assert.assertEquals(totalId, 57.0);
	}
}
