package tests;

import api.ApiActions;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pojo.Player;
import utils.StringUtils;
import utils.TestNgUtils;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreatePlayerTests extends BaseTest {
    @Test
    @Parameters({"age", "editor", "gender", "login", "password", "role", "screenName", "responseStatusCode"})
    public void createPlayerCheckThatUserExistTest(int age, String editor, String gender, String login,
                                                   String password, String role, String screenName, int responseStatusCode,
                                                   ITestContext context) {
        Player player = Player.builder()
                .age(age)
                .gender(gender)
                .login(login)
                .password(password)
                .role(role)
                .screenName(screenName)
                .build();

        Response response = ApiActions.createPlayer(player, editor);
        context.setAttribute("CreatedUserID", response.as(Player.class).getId());

        Player actualPlayer = ApiActions
                .getPlayerByID(context.getAttribute("CreatedUserID").toString()).as(Player.class);

        Assert.assertEquals(response.getStatusCode(), responseStatusCode, "Expected status code is different.");
        Assert.assertEquals(actualPlayer, player);
    }

    @Test
    @Parameters({"age", "editor", "gender", "login", "password", "role", "screenName"})
    public void createPlayerContractTest(int age, String editor, String gender, String login,
                                                   String password, String role, String screenName,
                                                   ITestContext context) {
        Player player = Player.builder()
                .age(age)
                .gender(gender)
                .login(login)
                .password(password)
                .role(role)
                .screenName(screenName)
                .build();

        Response response = ApiActions.createPlayer(player, editor);
        context.setAttribute("CreatedUserID", response.as(Player.class).getId());

        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/CreatePlayerResponseSchema.json"));
    }

    @AfterMethod()
    public void cleanUp(ITestContext context) {
        logger.info(String.format("Execute DeleteTestUserAfterTest after '%s' test", context.getName()));

        String createdUserID = TestNgUtils.getAttribute(context,"CreatedUserID");
        if (!StringUtils.isNullOrEmpty(createdUserID)) {
            ApiActions.deletePlayer(createdUserID, "admin");
        }
    }
}
