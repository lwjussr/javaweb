/**
 * 作者：兰文捷
 * 时间：2023.8.15
 * 功能：从数据库中获取全部的用户消息
 */
package dao;

import bean.sendMessage;

import java.sql.*;
import java.util.ArrayList;

public class getReceiveMessageDao implements getData{
    /**
     * 获取对应的用户消息
     * @param currentUser 获取消息接受者的用户名
     * @return 对应用户的消息的arraylist
     * @throws SQLException
     */
    public static ArrayList<sendMessage> getReceiveMessage(String currentUser) throws SQLException {
        ArrayList<sendMessage> receiveMessageList = new ArrayList<>();

        String sql = "select * from  `" + currentUser + "`" ;

        ResultSet resultSet = getData.getData(sql,"currentUser","chatRecord");
        while (resultSet.next()){
            String sender = resultSet.getString("sender");
            String message = resultSet.getString("message");
            String sendTime = resultSet.getString("time");
            System.out.println(sendTime);
            receiveMessageList.add(new sendMessage(sender,currentUser,message,sendTime));
        }
        return receiveMessageList;
    }

}
