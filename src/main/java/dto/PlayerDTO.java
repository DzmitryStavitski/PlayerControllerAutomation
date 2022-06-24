package dto;

public class PlayerDTO {
    private Integer age;
    private String gender;
    private String editor;
    private String login;
    private String password;
    private String role;
    private String screenName;

    public PlayerDTO(Integer age,
                     String editor,
                     String gender,
                     String login,
                     String password,
                     String role,
                     String screenName) {
        this.age = age;
        this.editor = editor;
        this.gender = gender;
        this.login = login;
        this.password = password;
        this.role = role;
        this.screenName = screenName;
    }

    public Integer getAge() {
        return age;
    }

    public String getEditor() {
        return editor;
    }

    public String getGender() {
        return gender;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getScreenName() {
        return screenName;
    }
}
