package rusmammals.data.model.dtos;

public class RegistrationDto {
    private String email;
    private String password;
    private Integer userCategoryId;
    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public int getUserCategoryId() {
        return this.userCategoryId.intValue();
    }

    public void setUserCategoryId(Integer num) {
        this.userCategoryId = num;
    }
}
