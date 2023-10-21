/**
 * 作者：兰文捷
 * 时间：2023.8.16
 * 功能：删除用户消息的ajax后端api
 */
package api;

import dao.getData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/deleteUserInfo")
public class deleteUserInfo extends HttpServlet implements getData {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");

        String sql = "DELETE FROM userInformation WHERE userName = '" + userName + "'";;
        getData.deleteData(sql,"userInformation","webHomework");

        String sql1 = "DELETE FROM table_name WHERE userName = '" + userName + "'";;
        getData.deleteData(sql1,"table_name","webHomework");

        String sql2 = "DROP TABLE " + userName + "";;
        getData.deleteData(sql2,userName,"chatRecord");
    }
}
