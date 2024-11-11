package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
/*
 * Different ways to create POST request body
 * 1) Post Request body using HashMap
 * 2) Post Request body using org.json
 * 3) Post Request body using POJO class
 * 4) Post Request using external json file data
 */

public class DifferentWaysToCreatePostRequestBody {
	
	@Test(priority=1)
	void testPostusingHashMap() {
		HashMap data = new HashMap();
		data.put("name", "Rakshith");
		data.put("job", "STE");
		
		/*
		 * In case, if the value for a key is having multiple values. Like "courses":["C","C++"]
		 * then we have to create a java array first and then assign the array as a value in the data.put
		 * 
		 * Eg.: String courseArr[]={"C", "C++"};
		 * 		data.put("courses",courseArr);
		 */
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			
		.then()
			.statusCode(201)
			.body("name",equalTo("Rakshith"))
			.body("job",equalTo("STE"))
			//.body("courses[0]",equalTo("C"))
			//.body("courses[1]",equalTo("C++"))
			.header("Content-Type", "application/json; charset=utf-8") //equalTo also works here
			
			.log().all();
			
	}
	//@Test
	void testPostRequestusingOrgJson() {
		JSONObject data=new JSONObject();
		data.put("name", "Raksh");
		data.put("job", "XYZ");
		
		
		/*
		 * In case, if the value for a key is having multiple values. Like "courses":["C","C++"]
		 * then we have to create a java array first and then assign the array as a value in the data.put
		 * 
		 * Eg.: String courseArr[]={"C", "C++"};
		 * 		data.put("courses",courseArr);
		 */
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://reqres.in/api/users")
			
		.then()
			.statusCode(201)
			.body("name",equalTo("Raksh"))
			.body("job",equalTo("XYZ"))
			//.body("courses[0]",equalTo("C"))
			//.body("courses[1]",equalTo("C++"))
			.header("Content-Type", "application/json; charset=utf-8")
			
			.log().all();
	}
	 
	//@Test
	void testPostRequestusingPOJO() {
		Pojo_PostRequest data=new Pojo_PostRequest();
		data.setName("Raksh");
		data.setJob("XYZ");
		//String coursesArr[]= {"C","C++"};
	//	data.setCourses(coursesArr);
		
		/*
		 * In case, if the value for a key is having multiple values. Like "courses":["C","C++"]
		 * then we have to create a java array first and then assign the array as a value in the data.put
		 * 
		 * Eg.: String courseArr[]={"C", "C++"};
		 * 		data.put("courses",courseArr);
		 */
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			
		.then()
			.statusCode(201)
			.body("name",equalTo("Raksh"))
			.body("job",equalTo("XYZ"))
			//.body("courses[0]",equalTo("C"))
			//.body("courses[1]",equalTo("C++"))
			.header("Content-Type", "application/json; charset=utf-8")
			
			.log().all();
	}
	@Test
	void testPostRequestusingExternalJSON() throws FileNotFoundException {
		File f=new File(".\\body.json");
		FileReader fr=new FileReader(f);
		JSONTokener jt=new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		
		
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://reqres.in/api/users")
			
		.then()
			.statusCode(201)
			.body("name",equalTo("Raksh"))
			.body("job",equalTo("XYZ"))
			//.body("courses[0]",equalTo("C"))
			//.body("courses[1]",equalTo("C++"))
			.header("Content-Type", "application/json; charset=utf-8")
			
			.log().all();
	}
}
