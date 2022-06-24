import dto.PlayerDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class PlayerControllerPositiveTests {
    @Test
    public void createPlayerTest() {
        PlayerDTO player = new PlayerDTO(20,
                "supervisor",
                "male",
                "login",
                "password",
                "user",
                "test");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://18.157.148.180:8080/");

    }
}
