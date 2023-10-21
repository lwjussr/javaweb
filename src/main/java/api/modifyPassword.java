/**
 * 作者：兰文捷
 * 时间：2023.8.16
 * 功能：修改密码的ajax后端api
 */
package api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/modifyPassword")
public class modifyPassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前端传过来的数据
        String userName = req.getParameter("userName");
        String oldPW = req.getParameter("oldPW");
        String newPW = req.getParameter("newPW");
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
        String selectSQl = "select * from table_name where userName = '" + userName + "' and password = '" + oldPW + "'";
        ResultSet resultSet;
        try {
            Statement statement = connection.createStatement();
            //执行sql语句
            resultSet = statement.executeQuery(selectSQl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if (resultSet.next()) {
                String sql = "UPDATE table_name SET  password = ?  WHERE userName = ?";
                try (
                    //创建一个PreparedStatement对象用于预编译sql语句
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    // 设置需要更新的值
                    preparedStatement.setString(1, newPW);
                    preparedStatement.setString(2, userName);
                    // 执行更新操作
                    int rows = preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                resp.setContentType("text/plain");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write("修改成功");
            }else{
                resp.setContentType("text/plain");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write("原密码输入错误");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
