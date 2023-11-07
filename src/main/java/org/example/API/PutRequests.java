package org.example.API;

import org.testng.Assert;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

public class PutRequests {
    public void updateCrocodile(String token, String id) {
        Response response = given().log().all().header("Content-Type","application/json").auth().oauth2(token)
                .body("{\n"+ "\"name\": \"Mrs. All-Sunday\",\n"+ "\"sex\": \"F\",\n"+ "\"date_of_birth\": \"1700-11-13\"\n"+ "}")
                .when().put("/my/crocodiles/" + id + "/").then().log().all()
                .assertThat().time(lessThan(1000L)).assertThat().statusCode(200).extract().response();

        Assert.assertTrue(response.path("id") instanceof Integer, "ID value must be a number");
        Assert.assertTrue(response.path("name") instanceof String, "Name field value must be a string");
        Assert.assertTrue(response.path("sex") instanceof String, "Sex field value must be a string");
        Assert.assertTrue(response.path("date_of_birth") instanceof String, "Invalid Date of birth");
        Assert.assertTrue(response.path("age") instanceof Integer, "Age value must be an integer");
    }
}
