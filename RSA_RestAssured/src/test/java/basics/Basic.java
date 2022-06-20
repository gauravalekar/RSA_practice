package basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.Payload;
import files.*;

public class Basic {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		// Add API 
	    // Given: All input details   / When: submit the API   / Then : Validate the response
		
		RestAssured.baseURI = "http://rahulshettyacademy.com";
		
		/*
		 * String response =
		 * given().log().all().queryParam("key","qaclick123").header("Content-Type",
		 * "application/json")
		 * .body(Payload.Addplace1()).when().post("maps/api/place/add/json")
		 * .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		 * .header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		 */
		
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(new String (Files.readAllBytes(Paths.get("D:\\Study\\API_Testing\\RSA\\Addplace.json")))).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js=ReUsableMethods2.rawToJson2(response); //for parsing Json
		
		String placeId=js.getString("place_id");
		System.out.println(placeId);
		
		
		//update place
		
		String newAddress = "Summer Walk, Africa";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
		//Get Place
		
		String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
			.queryParam("place_id",placeId)
			.when().get("maps/api/place/get/json")
			.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		
		JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
		
		String actualAddress =js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
	
	}
}
