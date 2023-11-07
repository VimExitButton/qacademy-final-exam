package org.example.API;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import io.restassured.response.Response;

public class PatchRequests {
    public void updateCrocodileInfo(String token, String ID) {

        Response response = given().header("Content-Type","application/json").auth().oauth2(token).body("{\n"+ "\"date_of_birth\": \"1930-1-13\"\n"+ "}")
                .when().patch("/my/crocodiles/" + ID +"/").then().log().all()
                .assertThat().time(lessThan(1000L)).assertThat().statusCode(200).extract().response();

        Assert.assertTrue(response.path("id") instanceof Integer, "Invalid ID");
        Assert.assertTrue(response.path("name") instanceof String, "Invalid Name");
        Assert.assertTrue(response.path("sex") instanceof String, "Invalid sex (Must be M or F)");
        Assert.assertTrue(response.path("date_of_birth") instanceof String, "Invalid Date of birth");
        Assert.assertTrue(response.path("age") instanceof Integer, "Invalid Age");
    }
}
