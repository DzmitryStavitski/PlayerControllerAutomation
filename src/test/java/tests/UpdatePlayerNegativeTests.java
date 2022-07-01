package tests;

import api.ApiActions;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pojo.Player;

public class UpdatePlayerNegativeTests {
    @Test
    public void updatePlayerTest(ITestContext context) {
        Player testPlayer = Player.builder().build();
        Response response = ApiActions.updatePlayerByID(context.getAttribute("999999").toString(),
                testPlayer, "admin");
        Assert.assertNotEquals(response.getStatusCode(), "200");
    }
}
