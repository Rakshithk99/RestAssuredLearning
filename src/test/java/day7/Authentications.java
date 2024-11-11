package day7;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentications {
	@Test
	void testBasicAuthentication() {
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
		
		
	}
	@Test
	void testDigestAuthentication() {
		given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
		
		
	}
	@Test
	void testPreemptiveAuthentication() {
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
		
		
	}
	
	@Test
	void testBearerToken() {
		//My Github token: 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
		String bearerToken="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";
		given()
			.headers("Authorization","Bearer "+bearerToken)
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}	
	
	@Test
	void testOAuth1() {
		given()
			.auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")
		.when()
			.get("url")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	void testOAuth2Authentication() {
		given()
		.auth().oauth2("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")
	.when()
		.get("https://api.github.com/user/repos")
	.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test
	void testAPIKeyAuthentication() {
		given()
			.queryParam("appid", "sdaceva") //appid is API Key
		.when()
			.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		.then()
			.statusCode(200)
			.log().all();
		
	}
}
