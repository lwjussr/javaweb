/**
 * 作者：兰文捷
 * 时间：2023.8.16
 * 功能：获取用户信息的ajax后端api
 */
package api;

import bean.sendMessage;
import bean.userInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/getUserInfo")
public class getUserInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String userName = req.getParameter("currentUserName");
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

        Statement statement;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "select * from userInformation " + "where userName = '" + userName + "'";
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        userInfo ui = null;
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String username = null;
            try {
                username = resultSet.getString("userName");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String name = null;
            try {
                name = resultSet.getString("name");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String gender = null;
            try {
                gender = resultSet.getString("gender");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String telephone = null;
            try {
                telephone = resultSet.getString("telephone");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ui = new userInfo(username, name, gender, telephone);
        }
        String jsonData = objectMapper.writeValueAsString(ui);

        // 在响应中返回 JSON 字符串
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonData);

        // 返回成功响应
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
