package basics;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class WithoutSerialized {

	public static void main(String[] args) {
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
	
	Response resp = given().log().all().queryParam("key","qaclick123")
			 .body(p).when().post("/maps/api/place/add/json")
			 .then().log().all().assertThat().statusCode(200).extract().response();
	String responsestring = resp.asString();
	System.out.println(responsestring);

}
}