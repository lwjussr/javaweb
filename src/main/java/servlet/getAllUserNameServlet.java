package servlet;

import dao.getAllUserNameDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/getAllUserName")
public class getAllUserNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {
        // 后端逻辑处理，获取数据
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<String> data = null;
        try {
            data = getAllUserNameDao.getAllUserName();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String jsonData = objectMapper.writeValueAsString(data);

        // 在响应中返回 JSON 字符串
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonData);
        // 返回成功响应
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
