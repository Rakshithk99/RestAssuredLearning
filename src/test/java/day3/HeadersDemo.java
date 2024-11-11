package day3;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {


	//@Test(priority=1)
	void testHeaders() {
		given()
		
		.when()
			.get("https://www.google.com")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and()
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws");
	}
	
	
	@Test(priority=2)
	void getHeaders() {
		Response res=given()
		
		.when()
			.get("https://www.google.com");
		
		//get single header info
		//String header_value=res.getHeader("Content-Type");
		//System.out.println("The value of Content-Type header is "+header_value);
		
		//get all Headers info
		Headers myHeaders=res.getHeaders();
		for(Header hd:myHeaders) {
			System.out.println(hd.getName()+"    "+hd.getValue());
			
			
		}
	
	
	}
	
}
