/**
 * 作者：兰文捷
 * 时间：2023.8.16
 * 功能：删除消息的ajax后端api
 */
package api;

import dao.getData;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/deleteMessage")
public class deleteMessage extends HttpServlet implements getData {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException{
        String sendTime = request.getParameter("sendTime");
        String currentUserName = request.getParameter("currentUserName");

        String sql = "DELETE FROM " + currentUserName + " WHERE time = '" + sendTime + "'";;
        getData.deleteData(sql,currentUserName,"chatRecord");

    }
}
