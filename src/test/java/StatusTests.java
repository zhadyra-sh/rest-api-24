import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class StatusTests {

    //make request to https://selenoid.autotests.cloud/status

    @Test
    @DisplayName("проверка значении что в теле 20 браузера с логом")
    void checkTotalWithLogs() {
        given()
                .log().uri()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20))
                .body("browsers.chrome", hasKey("100.0"))
                .body("browsers.firefox", hasKey("122.0"));


    }

    @Test
    @DisplayName("Negative, проверка значении что в теле 20 браузера")
    void checkTotalTwenty() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(21));

    }

    @Test
    @DisplayName(" проверка значении что в теле 20 браузера, responseLogs")
    void checkTotalWithResponseLogs() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total", is(20));

    }


}
