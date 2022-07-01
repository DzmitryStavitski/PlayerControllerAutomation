package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;
import pojo.Player;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigurationUtil;

import java.util.List;
import java.util.logging.Logger;

public class ApiActions {
    private static final Logger logger = Logger.getLogger(ApiActions.class.getName());

    private static final RequestSpecification specification = RestAssured.given()
            .baseUri(ConfigurationUtil.GetValue("application.url"))
            .basePath(ConfigurationUtil.GetValue("application.basePath"))
            .contentType(ContentType.JSON)
            .filter(new AllureRestAssured())
            .filter(new RequestLoggingFilter());

    public static Response createPlayer(Player player, String editor) {
        return RestAssured.given()
                .spec(specification)
                .contentType(ContentType.JSON)
                .when()
                .queryParams(player.convertToMap())
                .get(String.format("create/%s", editor));
    }

    public static Response deletePlayer(String playerId, String editor) {
        logger.info("Delete player with next id: " + playerId);
        return RestAssured.given()
                .spec(specification)
                .filter(new AllureRestAssured())
                .when()
                .body(String.format("{\"playerId\": %s}", playerId))
                .delete(String.format("delete/%s", editor));
    }

    public static List<Player> getAllPlayers() {
        logger.info("Get All Players");
        return RestAssured.given()
                .spec(specification)
                .when()
                .get("get/all")
                .then()
                .extract()
                .body()
                .jsonPath().getList("players", Player.class);
    }

    public static Response getPlayerByID(String id) {
        logger.info("Get Player By ID");
        return RestAssured.given()
                .spec(specification)
                .when()
                .body(String.format("{\"playerId\": %s}", id))
                .post("get");
    }

    public static Response updatePlayerByID(String id, Player changedPlayer, String editor) {
        return RestAssured.given()
                .spec(specification)
                .when()
                .body(changedPlayer)
                .patch(String.format("update/%s/%s", editor, id));
    }
}
