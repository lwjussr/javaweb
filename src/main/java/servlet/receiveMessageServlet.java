package servlet;


import bean.sendMessage;
import dao.getReceiveMessageDao;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/receiveMessage")
public class receiveMessageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException{
        String currentUserName = request.getParameter("currentUserName");
        System.out.println(currentUserName);
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<sendMessage> sendMessageList;

        try {
            sendMessageList = getReceiveMessageDao.getReceiveMessage(currentUserName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String jsonData = objectMapper.writeValueAsString(sendMessageList);

        System.out.println(jsonData);
        // 在响应中返回 JSON 字符串
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonData);

        // 返回成功响应
        response.setStatus(HttpServletResponse.SC_OK);
    }


}
