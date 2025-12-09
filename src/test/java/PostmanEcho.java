import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class PostmanEcho {

    @Test
    public void shouldCheckStatusCodeSuccessAndValidBody() {
        // Given - When - Then
// Предусловия
        given()
                .baseUri("https://postman-echo.com")
                .body("some data") // отправляемые данные (заголовки и query можно выставлять аналогично)
// Выполняемые действия
                .when()
                .post("/post")
// Проверки
                .then()
                .statusCode(200)
                .body("data", equalTo("some data"))
        ;
    }

    @Test
    public void shouldValidateBodyWithCyrillic() {
        // Given - When - Then
// Предусловия
        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body("что-то") // отправляемые данные (заголовки и query можно выставлять аналогично)
// Выполняемые действия
                .when()
                .post("/post")
// Проверки
                .then()
                .statusCode(200)
                .body("data", equalTo("что-то"))
        ;
    }

    @Test
    public void basicAuth() {
        // Given - When - Then
// Предусловия
        given()
                .baseUri("https://postman-echo.com")
                .header("Authorization", "Basic cG9zdG1hbjpwYXNzd29yZA==")
// Выполняемые действия
                .when()
                .get("/basic-auth")
// Проверки
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(false))
        ;
    }
}
