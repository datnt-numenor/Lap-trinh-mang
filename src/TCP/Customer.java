package TCP;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 20170711L;
    private int id;
    private String code, name, dayOfBirth, userName;
    public Customer(int id, String code, String name, String dayOfBirth, String userName) {
        this.id = id; this.code = code; this.name = name;
        this.dayOfBirth = dayOfBirth; this.userName = userName;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDayOfBirth() { return dayOfBirth; }
    public void setDayOfBirth(String dayOfBirth) { this.dayOfBirth = dayOfBirth; }
    public void setUserName(String userName) { this.userName = userName; }
}
