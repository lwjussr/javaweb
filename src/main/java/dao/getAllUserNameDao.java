/**
 * 作者：兰文捷
 * 时间：2023.8.15
 * 功能：从数据库中获取全部的用户信息
 */
package dao;

import java.sql.*;
import java.util.ArrayList;

public class getAllUserNameDao implements getData{
    /**
     * 将数据库全部的用户信息拿出来
     * @return 用户信息的arraylist
     * @throws SQLException
     */
    public static ArrayList<String> getAllUserName() throws SQLException {
        ArrayList<String> allUserNameList = new ArrayList<>();
        String sql = "select userName from table_name ";
        ResultSet resultSet = getData.getData(sql,"userName","webHomework");

        while (resultSet.next()){
            int i = 1;
            allUserNameList.add(resultSet.getString(i));
            i++;
        }

        allUserNameList.forEach(a ->{
            System.out.println(a);
        });

        return allUserNameList;
    }
}
