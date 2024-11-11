package day7;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class FakerDataGenerator {

	@Test
	void testGenerateDummyData() {
		Faker faker = new Faker();
		String name = faker.name().fullName();
		String firstName = faker.name().firstName();
		
		String username = faker.name().username();
		String password = faker.internet().password();
		
		
		String phoneNumber = faker.phoneNumber().cellPhone();
		String email = faker.internet().safeEmailAddress();
		
		String ccNumber = faker.business().creditCardNumber();
		
		System.out.println("First Name: "+ firstName);
		System.out.println("name: "+ name);
		System.out.println("username: "+ username);
		System.out.println("password: "+ password);
		System.out.println("phoneNumber: "+ phoneNumber);
		System.out.println("email: "+ email);
		System.out.println("ccNumber: "+ ccNumber);
		
	}
}
