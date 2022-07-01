package tests;

import api.ApiActions;
import pojo.Player;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestDataUtil;

import java.util.List;

@Test
public class GetAllPlayersTests {
    public void getAllPayersTest() throws Exception {
        List<Player> expectedPlayers = TestDataUtil.GetDefaultUsersFromResources();
        List<Player> playerDTOList = ApiActions.getAllPlayers();
        expectedPlayers.forEach(x -> Assert.assertTrue(playerDTOList.contains(x),
                "Expected default player not present in the system. Player: " + x));
    }
}
