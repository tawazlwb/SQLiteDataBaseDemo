package easyway2fourt.com.sqlitedatabasedemo;

/**
 * Created by MMD on 23/01/2016.
 */
public class DataProvider {
    private String name;
    private String mobile;
    private String email;

    public DataProvider(String name, String mobile, String email) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
