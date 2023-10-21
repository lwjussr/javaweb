/**
 * 作者：兰文捷
 * 时间：2023.8.13
 * 功能：登陆记录的实体类
 */
package bean;

public class loginRecord {
    
    //登录的用户名
    private String userName;

    //登录时的时间
    private String loginTime;

    //登陆时所在地的ip地址
    private String loginIP;

    public loginRecord(String userName, String loginTime, String loginIP) {
        this.userName = userName;
        this.loginTime = loginTime;
        this.loginIP = loginIP;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }
}
