package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;

public class LoginTests {
    /*
        1. Make request (POST) to https://reqres.in/api/login
            with body {"email": email": "eve.holt@reqres.in, "password": "cityslicka"}
        2. Get response {"token": "QpwL5tke4Pnpja7X4"}
        3. Check "token" is "QpwL5tke4Pnpja7X4" and status code 200
     */

    @Test
    void successfullLoginTest(){
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
        given()
                .body(authData)
                .contentType(JSON)
         .when()
                    .log().uri()
                .post("https://reqres.in/api/login")
         .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void unsuccessfullLogin415Test(){
        given()
                .log().uri()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }
}
