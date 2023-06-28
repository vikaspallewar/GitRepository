import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class Rest_Get_Reference {
  public static void main(String[] args) {
	  RestAssured.baseURI = "https://reqres.in/";
	  
	  //Declare Response Body
	  String responseBody = given().header("Content-Type","Application/json").
				body("responseBody").when().get("api/users?page=2").
				then().extract().response().asString();
	  System.out.println(responseBody);
	  
  }
}
