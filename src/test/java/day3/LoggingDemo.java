package day3;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;
public class LoggingDemo {
@Test(priority=1)
void testLogs() {
	given()
	
	.when()
		.get("https://reqres.in/api/users?page=2")
	.then()
		//.log().body()
		//.log().cookies()
		.log().headers();
}

}
