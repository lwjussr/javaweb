/**
 * 作者：兰文捷
 * 时间：2023.8.16
 * 功能：删除货物出库记录的ajax后端api
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

@WebServlet("/deleteGoodRecord")
public class deleteGoodRecord extends HttpServlet implements getData {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String changeTime = req.getParameter("changeTime");
        System.out.println(changeTime);
        String sql = "DELETE FROM goodRecord WHERE changeTime = '" + changeTime + "'";
        getData.deleteData(sql,"goodRecord","webHomework");
    }
}
