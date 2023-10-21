/**
 * 作者：兰文捷
 * 时间：2023.7.20
 * 功能：用户的实体类
 */
package bean;

public class user {
    //用户名
    private String userName;
    //密码
    private String password;
    //用户类型
    private int userType;

    public user(String userName, String password, int userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
