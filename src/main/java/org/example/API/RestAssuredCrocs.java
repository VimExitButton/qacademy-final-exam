package org.example.API;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class RestAssuredCrocs {

    GetRequests getRequest;
    PostRequests postRequest;
    PutRequests putRequest;
    DeleteRequests deleteRequest;
    PatchRequests patchRequest;

    @BeforeTest
    void setup () {
        baseURI = "https://test-api.k6.io/";
        deleteRequest = new DeleteRequests();
        getRequest = new GetRequests();
        patchRequest = new PatchRequests();
        postRequest = new PostRequests();
        putRequest = new PutRequests();
    }
    @BeforeClass
    public void createUser () {
        postRequest.userCreate();
    }
    @BeforeMethod
    public void bearerToken () {
        postRequest.getToken();
    }
    @Test (description = "List One Public Crocodile test")
    public void ListOnePublicCrocodile() {
        getRequest.listOnePublicCrocodile();

    }
    @Test (description = "List all Public Crocodiles test")
    public void ListPublicCrocodiles() {
        getRequest.listAllPublicCrocodiles();
    }
    @Test (description = "Register new user test")
    public void RegisterNewUser() {
        postRequest.RegisterNewUser();
    }
    @Test (description = "Login test")
    public void LoginUser() {
        postRequest.LoginUser();
    }
    @Test (description = "Create new Crocodile test")
    public void CreateNewCrocodile() {
        postRequest.createNewCrocodile("Mileta", "M", "2003-12-19");
    }
    @Test (description = "List all private crocodiles test")
    public void ListMyCrocodiles() {
        postRequest.createNewCrocodile("Designerica", "M", "1989-05-12");
        postRequest.createNewCrocodile("Geralt", "M", "1995-11-16");
        postRequest.createNewCrocodile("Triss", "F", "2009-10-10");
        postRequest.createNewCrocodile("Jelisaveta", "F", "2000-12-14");
        getRequest.listMyCrocodiles(postRequest.token);
    }
    @Test (description = "List one private Crocodile by ID test")
    public void ListCrocodileByID() {
        postRequest.createNewCrocodile("Bozo", "M", "1389-11-26");
        getRequest.getMyCrocodile(postRequest.token, postRequest.crocodileID);
    }
    @Test (description = "Update private Crocodile")
    public void UpdateCrocodile() {
        postRequest.createNewCrocodile("Masa", "F", "1996-09-19");
        putRequest.updateCrocodile(postRequest.token, postRequest.crocodileID);
    }
    @Test (description = "Update private Crocodile (one parameter) test")
    public void UpdateCrocoOneParameter() {
        postRequest.createNewCrocodile("Dragan", "M", "1981-05-01");
        patchRequest.updateCrocodileInfo(postRequest.token, postRequest.crocodileID);
    }
    @Test (description = "Delete crocodile test")
    public void deleteCrocodile () {
        postRequest.createNewCrocodile("Smeagol", "M", "1123-08-04");
        deleteRequest.deleteCrocodile(postRequest.token, postRequest.crocodileID);
    }
    @Test (description = "Logout test")
    public void postUserLogout() {
        postRequest.Logout(postRequest.token);
    }
}
