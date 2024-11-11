package day8;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;


public class CreateUser {

	@Test
	void test_createUser(ITestContext context) {
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		data.put("name",faker.name().fullName());
		data.put("gender", "Male");
		data.put("email",faker.internet().emailAddress());
		data.put("status", "inactive");
		String bearerToken = "7fac29e0e554c3e7f9861d64bd07b4b1c1e41c8f15cf662bef5c434cfb75ea0a";
		int res =given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		System.out.println(res);	
		//context.setAttribute("user_id", res);
		context.getSuite().setAttribute("user_id", res);
		
		
	}
	
}
