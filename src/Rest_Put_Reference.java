import java.time.LocalDateTime;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Rest_Put_Reference {
  public static void main(String[] args){
	  //Declare the Base Url
	  RestAssured.baseURI = "https://reqres.in/";
	  
	 //Declare the String Variable
	  String requestBody = "{\r\n"
	  		+ "    \"name\": \"morpheus\",\r\n"
	  		+ "    \"job\": \"zion resident\"\r\n"
	  		+ "}";
	  
	  JsonPath jspRequest = new JsonPath(requestBody);
	  String req_name = jspRequest.getString("name");
	  String req_job = jspRequest.getString("job");
	  LocalDateTime currentdate = LocalDateTime.now();
	  String expecteddate = currentdate.toString().substring(0,11);
	  
	  //Declare Given When Then Method
	  String responseBody = given().header("Content-Type","Application/json").
	  body(requestBody).when().put("api/users/2").
	  then().extract().response().asString();
	  
	  //Create an Object of Json Path to parse the ResponseBody
	  JsonPath jspResponse = new JsonPath(responseBody);
	  String res_name = jspResponse.getString("name");
	  String res_job = jspResponse.getString("job");
	  System.out.println(res_name);
	  System.out.println(res_job);
	  String res_updatedAt = jspResponse.getString("updatedAt");
	  res_updatedAt = res_updatedAt.substring(0,11);
	  
	  //Validate ResponseBody
	  Assert.assertEquals(res_name,req_name);
	  Assert.assertEquals(res_job,req_job);
	  Assert.assertEquals(res_updatedAt,expecteddate);
	  
  }
}
