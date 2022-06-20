package basics;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://www.rahulshettyacademy.com";

		Addplace p = new Addplace();
		p.setAccuracy(50);
		p.setName("pune");
		p.setAddress("kothrud , pune");
		p.setPhone_number("+916787687");
		p.setLanguage("French-IN");
		p.setWebsite("www.google.com");
		
		Location l = new Location();
		l.setLat(20.52);
		l.setLng(6.78);
		p.setLocation(l);
 		
		List<String> mylist = new ArrayList<String>();
		mylist.add("sho part");
		mylist.add("sho part2");
		p.setTypes(mylist);
		
		 RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				 .setContentType(ContentType.JSON).build();
				  
				  
				 ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
				 RequestSpecification res=given().spec(req)
				 .body(p);

				 Response response =res.when().post("/maps/api/place/add/json").
				 then().spec(resspec).extract().response();

				 String responseString=response.asString();
				 System.out.println(responseString);
		
	}

}
