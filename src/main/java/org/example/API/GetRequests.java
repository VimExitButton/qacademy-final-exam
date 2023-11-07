package org.example.API;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.example.API.PostRequests;
public class GetRequests {
    public void listAllPublicCrocodiles() {
        Response response = given().log().all().header("Content-Type","application/json")
                .when().get("/public/crocodiles/").then().log().all()
                .assertThat().time(lessThan(2000L)).assertThat().statusCode(200).extract().response();
        List<Map<String, Object>> items = response.jsonPath().getList("");

        for (Map<String, Object> item : items) {
            Assert.assertTrue(item.get("id") instanceof Integer, "Invalid ID");
            Assert.assertTrue(item.get("name") instanceof String, "Invalid Name");
            Assert.assertTrue(item.get("sex").equals("M") || item.get("sex").equals("F"), "Invalid sex (Must be M or F)");
            Assert.assertTrue(item.get("date_of_birth") instanceof String, "Invalid Date of birth");
            Assert.assertTrue(item.get("age") instanceof Integer, "Invalid Age");
        }

    }
    public void listOnePublicCrocodile () {
        Response response = given().log().all().header("Content-Type","application/json").when().get("/public/crocodiles/3/").then().log().all()
                .assertThat().time(lessThan(2000L)).assertThat().statusCode(200).extract().response();

        Assert.assertTrue(response.path("id") instanceof Integer, "Invalid ID");
        Assert.assertTrue(response.path("name") instanceof String, "Invalid Name");
        Assert.assertTrue(response.path("sex") instanceof String, "Invalid sex (Must be M or F)");
        Assert.assertTrue(response.path("date_of_birth") instanceof String, "Invalid Date of birth");
        Assert.assertTrue(response.path("age") instanceof Integer, "Invalid Age");
    }
    public void listMyCrocodiles(String token) {

        Response response = given().auth().oauth2(token).when().get("/my/crocodiles/").then().log().all()
                .assertThat().time(lessThan(2000L)).assertThat().statusCode(200).extract().response();

        List<Map<String, Object>> items = response.jsonPath().getList("");

        for (Map<String, Object> item : items) {
            Assert.assertTrue(item.get("id") instanceof Integer, "Invalid ID");
            Assert.assertTrue(item.get("name") instanceof String, "Invalid Name");
            Assert.assertTrue(item.get("sex").equals("M") || item.get("sex").equals("F"), "Invalid sex (Must be M or F)");
            Assert.assertTrue(item.get("date_of_birth") instanceof String, "Invalid Date of birth");
            Assert.assertTrue(item.get("age") instanceof Integer, "Invalid Age");
        }
    }
    public void getMyCrocodile(String token, String crocodileID ) {
        Response response = given().log().all().header("Content-Type","application/json")
                .auth().oauth2(token).when().get("/my/crocodiles/" + crocodileID + "/").then().log().all()
                .assertThat().time(lessThan(2000L)).assertThat().statusCode(200).extract().response();

        Assert.assertTrue(response.path("id") instanceof Integer, "Invalid ID");
        Assert.assertTrue(response.path("name") instanceof String, "Invalid Name");
        Assert.assertTrue(response.path("sex") instanceof String, "Invalid sex (Must be M or F)");
        Assert.assertTrue(response.path("date_of_birth") instanceof String, "Invalid Date of birth");
        Assert.assertTrue(response.path("age") instanceof Integer, "Invalid Age");
    }
}
