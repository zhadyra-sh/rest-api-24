import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class StatusTests {

    //make request to https://selenoid.autotests.cloud/status

    @Test
    @DisplayName("проверка значении что в теле 20 браузера с логом")
    void checkTotalWithLogs() {
        given()
                .log().all()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total", is(20));

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
