package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class UpdateUser {
	@Test
	void testUpdateUser(ITestContext context) {
Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		data.put("name",faker.name().fullName());
		data.put("gender", "Male");
		data.put("email",faker.internet().emailAddress());
		data.put("status", "active");
		String bearerToken = "7fac29e0e554c3e7f9861d64bd07b4b1c1e41c8f15cf662bef5c434cfb75ea0a";
		//int id=(int) context.getAttribute("user_id");
		int id=(int) context.getSuite().getAttribute("user_id");
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("id", id)
			.contentType("application/json")
			.body(data.toString())
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
			
	}
}
