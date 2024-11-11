package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//POJO --serialize--> JSON Object--De-serialize--> POJO

public class SerializationDeserialization {
	@Test
	void convertPojo2Json() throws JsonProcessingException {
		//converted java object using pojo class
		Student stuPojo = new Student();   //POJO 
		stuPojo.setName("Rak");
		stuPojo.setJob("BCD");
		String courseArr[] = {"ABC", "XYZ"};
		stuPojo.setCourses(courseArr);
		
		//convert java object --> json object (serialization)
		ObjectMapper objMapper = new ObjectMapper();
		String jsondata = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stuPojo);
		System.out.println(jsondata);
	}
	
	@Test
	void convertJson2Pojo() throws JsonProcessingException {
		String jsondata = "{\r\n"
				+ "  \"name\" : \"Rak\",\r\n"
				+ "  \"job\" : \"BCD\",\r\n"
				+ "  \"courses\" : [ \"ABC\", \"XYZ\" ]\r\n"
				+ "}";
		
		//convert json data ---> Pojo object
		ObjectMapper objMapper = new ObjectMapper();
		Student stupojo = objMapper.readValue(jsondata, Student.class);
		System.out.println(stupojo.getName());
		System.out.println(stupojo.getJob());
		System.out.println("Course 1: "+stupojo.getCourses()[0]);
		System.out.println("Course 2: "+stupojo.getCourses()[1]);
	}
	
}
