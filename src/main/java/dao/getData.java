package dao;

import java.sql.*;
import java.util.Set;

public interface getData {
    /**
     * 获取数据库数据的接口
     * @param sql 要执行的sql语句
     * @param table_name 获取数据的表名
     * @param framework_name   获取数据的架构名
     * @return
     */
    public static ResultSet getData(String sql,String table_name,String framework_name){

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //mysql的相关信息
        String url = "jdbc:mysql://81.70.187.168:3306/" + framework_name;
        String user = "root";
        String pwd = "ncu111";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Statement statement;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    /**
     * 删除数据的接口
     * @param sql 要执行的sql语句
     * @param table_name 删除数据所在的表名
     * @param framework_name 删除数据所在的架构名
     */
    public static void deleteData(String sql,String table_name,String framework_name){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //mysql的相关信息
        String url = "jdbc:mysql://81.70.187.168:3306/" + framework_name;
        String user = "root";
        String pwd = "ncu111";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Statement statement;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
