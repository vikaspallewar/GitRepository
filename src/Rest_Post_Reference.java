import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;

public class Rest_Post_Reference {
  public static void main(String[] args) {
	//Declare The Base URL
    RestAssured.baseURI = "https://reqres.in/";
    
    //Declare The Request String Variable
    String requestBody = "{\r\n"
    		+ "    \"name\": \"morpheus\",\r\n"
    		+ "    \"job\": \"leader\"\r\n"
    		+ "}";
    JsonPath jspRequest = new JsonPath(requestBody);
    String req_name = jspRequest.getString("name");
    String req_job = jspRequest.getString("job");
    LocalDateTime currentdate = LocalDateTime.now();
	String expecteddate = currentdate.toString().substring(0,11);
	
	//Declare Given When Then Method
	String responseBody = given().header("Content-Type","application/json").
			body(requestBody).post("api/users").then().extract().response().asString();
	
	//Create an Object of Json Path to parse the ResponseBody
	JsonPath jspResponse = new JsonPath(responseBody);
	String res_name = jspResponse.getString("name");
    String res_job = jspResponse.getString("job");
    System.out.println(res_name);
    System.out.println(res_job);
    String res_createdAt = jspResponse.getString("createdAt");
    res_createdAt = res_createdAt.substring(0,11);
    System.out.println(res_createdAt);
    
  //Validate ResponseBody
    Assert.assertEquals(res_name,req_name);
	Assert.assertEquals(res_job,req_job);
	Assert.assertEquals(res_createdAt,expecteddate);
    
    
  }
}
