package tests;

import api.ApiActions;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetPlayerByIdNegativeTests extends BaseTest {
    @Test
    public void GetPlayerById(ITestContext context) {
        String nonExistingID = "123456";
        Response response = ApiActions.getPlayerByID(nonExistingID);
        Assert.assertNotEquals(response.getStatusCode(), 200,
                "Expected code after delete player operation is different.");
    }
}
