/**
 * 作者：兰文捷
 * 时间：2023.8.14
 * 功能：用户信息的实体类
 */
package bean;

public class userInfo {
    private String userName;

    private String name;

    private  String gender;

    private String telephone;

    public userInfo(String userName, String name, String gender, String telephone) {
        this.userName = userName;
        this.name = name;
        this.gender = gender;
        this.telephone = telephone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
