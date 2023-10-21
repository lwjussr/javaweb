/**
 * 作者：兰文捷
 * 时间：2023.7.21
 * 功能：登录功能的dao层
 */
package dao;

import bean.user;
import api.getAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class login {
    /**
     * 将登录界面传进来进行数据库查询
     * @param userName 用户输入的用户名
     * @param password 用户输入的密码
     * @param ip 用户登录登陆时的ip地址
     * @return 如果用户名和密码匹配则返回对应的user对象
     * 如果错误则返回一个空对象
     */
    public static user cheakLogin(String userName, String password,String ip) {
        //启动数据库驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //mysql的相关信息
        String url = "jdbc:mysql://81.70.187.168:3306/webHomework";
        String user = "root";
        String pwd = "ncu111";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (connection == null) {
            System.out.println("连接失败");
            return null;
        } else {
            //创建statemate类对象，用来执行sql语句
            Statement statement;
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String sql = "select * from table_name where userName = '" + userName + "' and password = '" + password + "'";
            ResultSet resultSet;
            try {
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (resultSet.next()) {
                    // 存在查询结果
                    System.out.println("登录成功");
                    user u = new user(resultSet.getString("userName"),resultSet.getString("password"),resultSet.getInt("" +
                            "userType"));

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.now();
                    // 格式化当前日期时间
                    String currentDateTime = dateTime.format(formatter);
                    String address = getAddress.getIp(ip);
                    String insertDate = "INSERT INTO loginRecord (userName , loginTime,loginIP,address) VALUES  (?, ?, ?, ?)";
                    PreparedStatement preparedStatement;
                    // 创建PreparedStatement对象，预编译SQL语句
                    preparedStatement = connection.prepareStatement(insertDate);

                    // 设置参数值
                    preparedStatement.setString(1, u.getUserName());
                    preparedStatement.setString(2, currentDateTime);
                    preparedStatement.setString(3, ip);
                    preparedStatement.setString(4, address);
                    int rowsInserted = preparedStatement.executeUpdate();

                    //关闭连接
                    statement.close();
                    connection.close();
                    return u;

                } else {
                    // 未找到数据
                    statement.close();
                    connection.close();
                    System.out.println("登录失败");
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
