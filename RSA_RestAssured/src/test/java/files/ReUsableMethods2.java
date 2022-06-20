package files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods2 {

	public static JsonPath rawToJson2(String response)
	{
		JsonPath js =new JsonPath(response);
		return js;
	}

}
