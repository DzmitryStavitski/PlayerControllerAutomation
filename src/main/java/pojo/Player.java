package pojo;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.HashMap;

@Builder(access = AccessLevel.PUBLIC, toBuilder = true)
@Jacksonized
@EqualsAndHashCode(exclude = {"id"})
@Getter
@ToString
public class Player {
    private Integer id;
    private Integer age;
    private String gender;
    private String login;
    private String password;
    private String role;
    private String screenName;

    public HashMap<String, String> convertToMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        map.put("age", String.valueOf(age));
        map.put("gender", gender);
        map.put("login", login);
        map.put("password", password);
        map.put("role", role);
        map.put("screenName", screenName);

        return map;
    }
}
