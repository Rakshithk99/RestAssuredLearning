package day5;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
public class FileUpload {
	@Test
	void singleFileUpload() {
		File myFile = new File("C:\\AutomationProjects\\Test1.txt");
		
		given()
			.multiPart("file",myFile)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadFile")
		.then()
			.statusCode(200)
	.body("fileName", equalTo("Test1.txt"))
	.log().all();
	}

	@Test
	void multipleFilesUpload() {
		File myFile1 = new File("C:\\AutomationProjects\\Test1.txt");
		File myFile2 = new File("C:\\AutomationProjects\\Test2.txt");
		given()
			.multiPart("files",myFile1)
			.multiPart("files",myFile2)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadmultipleFiles")
		.then()
			.statusCode(200)
	.body("[0].fileName", equalTo("Test1.txt"))
	.body("[1].fileName", equalTo("Test2.txt"))
	.log().all();
	}
	
	@Test
	void multipleFilesUpload2() { //wont work for all kinds of API
		File myFile1 = new File("C:\\AutomationProjects\\Test1.txt");
		File myFile2 = new File("C:\\AutomationProjects\\Test2.txt");
		
		File fileArray[] = {myFile1,myFile2};
		given()
			.multiPart("files",fileArray)
			
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadmultipleFiles")
		.then()
			.statusCode(200)
	.body("[0].fileName", equalTo("Test1.txt"))
	.body("[1].fileName", equalTo("Test2.txt"))
	.log().all();
	}
	
	@Test
	void fileDOwnload() {
		given()
		
		.when()
			.get("http://localhost:8080/downloadFile/Test1.txt")
		.then()
			.statusCode(200)
			.log().body();
		
	}
}
