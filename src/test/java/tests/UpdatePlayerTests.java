package tests;

import api.ApiActions;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pojo.Player;

public class UpdatePlayerTests {
    @BeforeMethod
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
        context.setAttribute("CreatedPlayerObject", player);
    }

    @Test
    public void updatePlayerTest(ITestContext context) {
        Player createdPlayer = ApiActions.getPlayerByID(context.getAttribute("CreatedUserID").toString()).as(Player.class);
        Player changedPlayer = createdPlayer.toBuilder().age(59).build();
        Response response = ApiActions.updatePlayerByID(context.getAttribute("CreatedUserID").toString(),
                changedPlayer, "admin");
        Player actualPlayer = response.as(Player.class);
        Assert.assertEquals(response.as(Player.class), changedPlayer);
    }
}
