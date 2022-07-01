package tests;

import api.ApiActions;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pojo.Player;
import utils.StringUtils;
import utils.TestNgUtils;

public class CreatePlayerNegativeTests extends BaseTest {
    @Test
    @Parameters({"age", "editor", "gender", "login", "password", "role", "screenName", "responseStatusCode"})
    public void createPlayerNegativeTest(int age, String editor, String gender, String login,
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
        if (response.getStatusCode() == 200) {
            context.setAttribute("CreatedUserID", response.as(Player.class).getId());
        }


        Assert.assertEquals(response.getStatusCode(), responseStatusCode,
                "Expected status code is different.");
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
