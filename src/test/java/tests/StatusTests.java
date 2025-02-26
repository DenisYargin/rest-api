package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class StatusTests {
    /*
      1. Make request to https://selenoid.autotests.cloud/status
      2. Get response {total: 20}...
      3. Check total is 20
     */

    @Test
    void checkTotal20(){
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithResponseLogs(){
        get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithLogs(){
        given()
                .log().all()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithSomeLogs(){
        given()
                .log().uri()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().body()
                .statusCode(200)
                .body("total", is(20));
    }
}
