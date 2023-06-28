import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class Rest_Delete_Reference {
	  public static void main(String[] args) {
		  RestAssured.baseURI = "https://reqres.in/";
		  
		  //Declare Response Body
		  String responseBody = given().header("Content-Type","Application/json").
					body("responseBody").when().delete("api/users/2").
					then().extract().response().asString();
		  System.out.println(responseBody);
		  
	  }
	}


