package tests;

import api.ApiActions;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pojo.Player;

public class DeletePlayerNegativeTests extends BaseTest {
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
        context.setAttribute("CreatedUserObject", response.as(Player.class));
    }

    @Test
    public void deletePlayerNegativeTest(ITestContext context) {
        String createdUserID = context.getAttribute("CreatedUserID").toString();
        Response response = ApiActions.deletePlayer(createdUserID, "nonExistingLogin");
        Assert.assertNotEquals(response.getStatusCode(), 204,
               "Expected code after delete player operation is different.");
    }
}
