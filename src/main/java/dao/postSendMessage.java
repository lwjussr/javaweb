/**
 * 作者：兰文捷
 * 时间：2023.8.15
 * 功能：将用户发送的消息存储到对应的数据库中
 */
package dao;

import bean.sendMessage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class postSendMessage {
    /**
     * 将用户发送的消息存储进数据库当中
     * @param sm 用户发送消息的相关信息的sendMessage类
     * @return
     * @throws SQLException
     */
    public static sendMessage insertMessage(sendMessage sm) throws SQLException {

        //启动数据库驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //mysql的相关信息
        String url = "jdbc:mysql://81.70.187.168:3306/chatRecord";
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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.now();
            // 格式化当前日期时间
            String currentDateTime = dateTime.format(formatter);

            String insertDate = "INSERT INTO `"+ sm.getRecipient()+"`(sender , message, time) VALUES  (?, ?, ?)";
            PreparedStatement preparedStatement;
            // 创建PreparedStatement对象，预编译SQL语句
            preparedStatement = connection.prepareStatement(insertDate);

            // 设置参数值
            preparedStatement.setString(1, sm.getSender());
            preparedStatement.setString(2, sm.getMessage());
            preparedStatement.setString(3, currentDateTime);

            // 执行插入操作
            int rowsInserted = preparedStatement.executeUpdate();

            return sm;
        }


    }
}
