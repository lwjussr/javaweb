/**
 * 作者：兰文捷
 * 时间：2023.8.25
 * 功能：修改货物信息
 */
package api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/modifyGoodInfo")
public class modifyGoodInfo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        String goodName = null;
        String manufacturer = null;
        double buyPrice = 0;
        double sellPrice = 0;
        int goodNum = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
            goodName = req.getParameter("goodName");
            manufacturer = req.getParameter("manufacturer");
            buyPrice = Double.parseDouble(req.getParameter("buyPrice"));
            sellPrice = Double.parseDouble(req.getParameter("sellPrice"));
            goodNum = Integer.parseInt(req.getParameter("goodNum"));
        } catch (NumberFormatException e) {
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("输入的数据有错误");
            throw new RuntimeException(e);
        }
        System.out.println(goodName);
        //加载数据库驱动
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
        //连接数据库
        try {
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "UPDATE goodInfo SET goodName = ?, manufacturer = ?, buyPrice = ?, sellPrice = ?, goodNum = ? WHERE id = ?";
        //String sql = "UPDATE goodInfo SET  goodName = ?  ,manufacturer = ?, buyPrice = ?,sellPrice = ?,goodNum = ?,WHERE id = ''" + id + "'";
        try (
                //创建一个PreparedStatement对象用于预编译sql语句
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // 设置需要更新的值
            // 设置参数值
            try {
                preparedStatement.setString(1, goodName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.setString(2, manufacturer);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.setDouble(3, buyPrice);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.setDouble(4, sellPrice);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.setInt(5, goodNum);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            preparedStatement.setInt(6, id);
            // 执行更新操作
            int rows = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("修改成功");
    }

}
