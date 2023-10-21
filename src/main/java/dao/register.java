/**
 * 作者：兰文捷
 * 时间：2023.7.21
 * 功能：注册功能的dao层
 */
package dao;

import bean.user;

import java.sql.*;

public class register {
    /**
     *
     * @param u 用户注册界面传入的信息
     * @return 如果注册成功则返回传入的user类如果注册失败则返回null
     * @throws SQLException
     */
    public static user cheakRegister(user u) throws SQLException {
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
            String sql = "select * from table_name where userName = '" + u.getUserName() + "'";
            ResultSet resultSet;
            try {
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            PreparedStatement preparedStatement;
            try {
                if (resultSet.next()) {
                    System.out.println("用户名有重复");
                    return null;
                } else {
                    String insertDate = "INSERT INTO table_name (userName , password, userType) VALUES  (?, ?, ?)";

                    String insertInfo = "INSERT INTO userInformation (userName , name, gender, telephone) VALUES  (?, ?, ?, ?)";
                    // 创建PreparedStatement对象，预编译SQL语句
                    preparedStatement = connection.prepareStatement(insertDate);

                    // 设置参数值
                    preparedStatement.setString(1, u.getUserName());
                    preparedStatement.setString(2, u.getPassword());
                    preparedStatement.setInt(3, u.getUserType());


                    // 执行插入操作
                    int rowsInserted = preparedStatement.executeUpdate();

                    preparedStatement = connection.prepareStatement(insertInfo);
                    preparedStatement.setString(1, u.getUserName());
                    preparedStatement.setString(2, "");
                    preparedStatement.setString(3, "");
                    preparedStatement.setString(4, "");
                    preparedStatement.executeUpdate();

                    // 创建表的SQL语句
                    String createTableSQL = "CREATE TABLE `" + u.getUserName() + "` ("
                            + "sender varchar(20) NULL,"
                            + "message varchar(200) NULL,"
                            + "time varchar(20) NULL"
                            + ") DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";

                    // 执行创建表操作
                    try  {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://81.70.187.168:3306/chatRecord", user, pwd);
                        Statement stmt = conn.createStatement();
                        stmt.executeUpdate(createTableSQL);
                        System.out.println("Table created successfully.");
                        conn.close();
                        stmt.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    if (rowsInserted > 0) {
                        // 关闭连接和声明
                        statement.close();
                        connection.close();
                        System.out.println("数据插入成功！");
                        return u;
                    } else {
                        statement.close();
                        connection.close();
                        System.out.println("未插入任何数据。");
                        return null;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
