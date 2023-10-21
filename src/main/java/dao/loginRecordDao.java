/**
 * 作者：兰文捷
 * 时间：2023.8.15
 * 功能：从数据库中获取全部的登陆记录
 */
package dao;

import bean.loginRecord;

import java.sql.*;
import java.util.ArrayList;

public class loginRecordDao {

    /**
     * 从数据库获取登录数据
     * @return  登陆数据的arraylist
     * @throws SQLException
     */
   public static ArrayList<loginRecord> getLoginRecordList() throws SQLException {

       ArrayList<loginRecord> recordArrayList = new ArrayList<>();
        //加载jbdc驱动
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
            System.out.println("连接成功");

            //创建statemate类对象，用来执行sql语句
            Statement statement;
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String sql = "select * from loginRecord";
            ResultSet resultSet;
            try {
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            while (resultSet.next()){
                String userName = resultSet.getString(1);
                String loginTime = resultSet.getString(2);
                String loginIP = resultSet.getString(3);
                loginRecord l = new loginRecord(userName,loginTime,loginIP);
                recordArrayList.add(l);
            }

            return recordArrayList;
        }

    }
}
