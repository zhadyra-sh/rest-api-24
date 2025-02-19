import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class LoginTests {
    /*
    1. make request POST to https://reqres.in/api/login
    with body { "email": "eve.holt@reqres.in",   "password": "cityslicka"
    2. get response token - { "token": "QpwL5tke4Pnpja7X4"}
    3. check token { "token": "QpwL5tke4Pnpja7X4"} and status code 200
     */

    @Test
    @DisplayName("проверка логина")
    void successfulLoginTest() {

        String authData = "{\n" + "\"email\": \"eve.holt@reqres.in\",\n" + "\"password\": \"cityslicka\"\n" + "}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }

    @Test
    @DisplayName("проверка на логина")
    void unsuccessfulLoginTest() {
        given()
                .log().uri()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(415)
        ;

    }

    @Test
    @DisplayName("вход без пароля")
    void unsuccessfulBadRequestLoginTest() {

        String authData = "{\n" + "\"email\": \"eve.holt@reqres.in\"}";

        given()
                .body(authData)
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));

    }

    @Test
    @DisplayName("вход на неправильный email")
    void unsuccessfulWrongEmailTest() {

        String authData = "{\n" + "\"email\": \"eve.holtewd@reqres.in\",\n" + "\"password\": \"cityslicka\"\n" + "}";

        given()
                .body(authData)
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));

    }

    @Test
    @DisplayName("вход без email")
    void unsuccessfulWithoutEmailTest() {

        String authData = "{\n" + "\"password\": \"cityslicka\"\n" + "}";

        given()
                .body(authData)
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));

    }
}
