/**
 * 作者：兰文捷
 * 时间：2023.8.27
 * 功能：插入新货物的信息
 */
package api;

import dao.getData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/insertGoodInfo")
public class insertGoodInfo extends HttpServlet implements getData {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String goodName = req.getParameter("goodName");
        String manufacturer = req.getParameter("manufacturer");
        double buyPrice = Double.parseDouble(req.getParameter("buyPrice"));
        double sellPrice = Double.parseDouble(req.getParameter("sellPrice"));
        int goodNum = Integer.parseInt(req.getParameter("goodNum"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //mysql的相关信息
        String url = "jdbc:mysql://81.70.187.168:3306/" + "webHomework";
        String user = "root";
        String pwd = "ncu111";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Statement statement;



        String insertDate = "INSERT INTO goodInfo (id , goodName,manufacturer,buyPrice,sellPrice,goodNum) VALUES  (?, ?, ?, ?, ?, ?)";
        String sql = "select * from goodInfo where id = '" + id + "'";
        ResultSet resultSet = getData.getData(sql,"goodInfo","webHomework");
        try {
            if (resultSet.next()){
                resp.setContentType("text/plain");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write("该id已存在");
            }else {
                PreparedStatement preparedStatement;
                // 创建PreparedStatement对象，预编译SQL语句
                try {
                    preparedStatement = connection.prepareStatement(insertDate);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                // 设置参数值
                try {
                    preparedStatement.setInt(1, id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    preparedStatement.setString(2, goodName);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    preparedStatement.setString(3, manufacturer);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    preparedStatement.setDouble(4, buyPrice);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    preparedStatement.setDouble(5, sellPrice);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    preparedStatement.setInt(6, goodNum);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                try {
                    int rowsInserted = preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                resp.setContentType("text/plain");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write("添加成功");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
