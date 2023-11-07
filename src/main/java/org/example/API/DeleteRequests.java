package org.example.API;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteRequests {

    public void deleteCrocodile(String token, String crocodileID) {

        given().auth().oauth2(token)
                .when().delete("/my/crocodiles/" + crocodileID + "/")
                .then().log().all()
                .assertThat().time(lessThan(1000L))
                .assertThat().statusCode(204);

    }
}
