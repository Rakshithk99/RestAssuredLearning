package day6;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class XmlSchemaValidation {
@Test
void xmlSchemaValidation() {
	given()
	
	.when()
		.get("http://restapi.adequateshop.com/api/Traveler")
	.then()
		.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchema.xsd"));
}
}
