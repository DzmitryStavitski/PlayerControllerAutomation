package tests;

import api.ApiActions;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pojo.Player;
import utils.StringUtils;
import utils.TestNgUtils;

public class GetPlayerByIdTests extends BaseTest {
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
    public void GetPlayerById(ITestContext context) {
        Player expectedPlayer = (Player) context.getAttribute("CreatedPlayerObject");
        String createdPlayerID = context.getAttribute("CreatedUserID").toString();

        Player player = ApiActions.getPlayerByID(createdPlayerID).as(Player.class);

        Assert.assertEquals(player, expectedPlayer, "Returned player doesn't the same as created player");
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
