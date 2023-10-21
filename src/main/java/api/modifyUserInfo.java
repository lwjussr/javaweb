/**
 * 作者：兰文捷
 * 时间：2023.8.16
 * 功能：修改用户消息的ajax后端api
 */
package api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/modifyUserInfo")
public class modifyUserInfo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前端传过来的数据
        String userName = req.getParameter("userName");
        String realNa = req.getParameter("realNa");
        String sex = req.getParameter("sex");
        String phone = req.getParameter("phone");

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
        try {
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "UPDATE userInformation SET name = ?, gender = ? ,telephone = ? WHERE userName = ?";

        try (
            //预编译sql语句
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // 设置需要更新的值
            statement.setString(1, realNa);
            statement.setString(2, sex);
            statement.setString(3, phone);
            statement.setString(4, userName);

            // 执行更新操作
            int rows = statement.executeUpdate();

            //将情况返回给前端
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("修改成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
