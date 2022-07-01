package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pojo.Player;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestDataUtil {
    public static List<Player> readJsonFromResources() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/defaultUsers.json"));
        List<Player> players = new Gson().fromJson(reader, new TypeToken<List<Player>>(){}.getType());
        return players;
    }
}
