package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import bean.sendMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.postSendMessage;

@WebServlet("/sendMessage")
public class sendMessageServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // 设置请求和响应的字符编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        // 读取请求体中的数据
        String requestData =  request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        System.out.println(requestData);

        // 将请求体中的数据解析为对象
        sendMessage sm = objectMapper.readValue(requestData, sendMessage.class);

        // 获取数据
        String recipient = sm.getRecipient();
        String message = sm.getMessage();

        System.out.println(recipient);
        System.out.println(message);
        System.out.println(sm.getSender());

        try {
            postSendMessage.insertMessage(sm);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // 返回成功响应
        response.setStatus(HttpServletResponse.SC_OK);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("发送成功");
    }
}
