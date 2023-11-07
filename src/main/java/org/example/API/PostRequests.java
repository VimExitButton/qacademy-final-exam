package org.example.API;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import java.util.UUID;

import java.util.UUID;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRequests {
    private String username;
    private String password = "Supermen";

    String token;
    String crocodileID;

    public String RegisterUserBody(String username1, String email1){
        return "{\n"+ "\"username\": \""+username1+"\",\n"+ "\"first_name\": \"Emperor\",\n"+ "\"last_name\": \"OfMankind\",\n"+ "\"email\": \""+email1+"\",\n"+ "\"password\": \""+password+"\"\n"+ "}";
    }
    public String randomStr() {
        int desiredLength = 10;
        String random = UUID.randomUUID().toString().substring(0, desiredLength);
        return random;
    }
    public void userCreate() {
        String randomUsername = "proba8809+test"+ randomStr();
        String randomEmail = "proba8809+test1"+ randomStr() + "@gmail.com";
        Response response = given().log().all().header("Content-Type","application/json")
                .body(RegisterUserBody(randomUsername, randomEmail)).when().post("/user/register/").then().log().all()
                .extract().response();

        username = response.path("username").toString();
    }
    public void RegisterNewUser() {
        String randomUsername = "proba8809+test"+ randomStr();
        String randomEmail = "proba8809+test1"+ randomStr() + "@gmail.com";
        Response response = given().log().all().header("Content-Type","application/json")
                .body(RegisterUserBody(randomUsername, randomEmail))
                .when().post("/user/register/")
                .then().log().all()
                .assertThat().time(lessThan(2000L))
                .assertThat().statusCode(201)
                .extract().response();

        Assert.assertTrue(response.path("username") instanceof String, "Invalid username");
        Assert.assertTrue(response.path("first_name") instanceof String, "Invalid first name");
        Assert.assertTrue(response.path("last_name") instanceof String, "Invalid last name");
        Assert.assertTrue(response.path("email") instanceof String, "Invalid email");

    }
    public void LoginUser() {
        Response response = given().header("Content-Type","application/json")
                .body("{\n"+ "\"username\": \""+username+"\",\n"+ "\"password\": \""+password+"\"\n"+ "}").when().post("/auth/token/login/").then().log().all()
                .assertThat().time(lessThan(2000L)).assertThat().statusCode(200).extract().response();

        Assert.assertTrue(response.path("refresh") instanceof String, "refresh value is not a string");
        Assert.assertTrue(response.path("access") instanceof String, "access value is not a string");
    }
    public void getToken() {
        Response response = given().log().all().header("Content-Type","application/json")
                .body("{\n"+ "\"username\": \""+username+"\",\n"+ "\"password\": \""+password+"\"\n"+ "}").when().post("/auth/token/login/").then().log().all()
                .extract().response();
        token = response.path("access").toString();

    }
    public void createNewCrocodile(String name, String sex, String date) {
        Response response = given().header("Content-Type","application/json").auth().oauth2(token)
                .body("{\n"+ "\"name\": \""+name+"\",\n"+ "\"sex\": \""+sex+"\",\n"+ "\"date_of_birth\": \""+date+"\"\n"+ "}").when().post("/my/crocodiles/").then().log().all()
                .assertThat().time(lessThan(2000L)).assertThat().statusCode(201)
                .assertThat().body("name",equalTo(name), "sex", equalTo(sex), "date_of_birth", equalTo(date))
                .extract().response();

        crocodileID = response.path("id").toString();
        Assert.assertTrue(response.path("id") instanceof Integer, "Invalid ID");
        Assert.assertTrue(response.path("name") instanceof String, "Name field value must be a string");
        Assert.assertTrue(response.path("sex") instanceof String, "Sex field value must be a string");
        Assert.assertTrue(response.path("date_of_birth") instanceof String, "Invalid Date of birth");
        Assert.assertTrue(response.path("age") instanceof Integer, "Age value must be an integer");
    }
    public void Logout (String token) {
        given().auth().oauth2(token).when().post("/auth/cookie/logout/").then().log().all()
                .assertThat().time(lessThan(2000L)).assertThat().statusCode(200);
    }
}
