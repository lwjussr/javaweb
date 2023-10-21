/**
 * 作者：兰文捷
 * 时间：2023.8.26
 * 功能：获取货物每日的销售信息
 */
package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.getData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/getGoodDaySale")
public class getGoodDaySale extends HttpServlet implements getData {
    class  goodDaySale{
        String time;
        int count;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public goodDaySale() {
        }

        public goodDaySale(String time, int count) {
            this.time = time;
            this.count = count;
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<goodDaySale> goodDaySales = new ArrayList<>();
        String goodName = req.getParameter("goodName");
        System.out.println(goodName);

        String sql = "SELECT DATE(changeTime) AS date, SUM(changeNum) AS count FROM goodRecord WHERE goodName = '" +goodName +"' GROUP BY DATE(changeTime)";
        ResultSet resultSet = getData.getData(sql,"goodRecord","webHomework");

        while (true){
            try {
                if (!resultSet.next()) break;
                else {
                    String date = resultSet.getString("date");
                    int count = resultSet.getInt("count");
                    goodDaySale g = new goodDaySale(date,count);
                    goodDaySales.add(g);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        String jsonData = objectMapper.writeValueAsString(goodDaySales);
        System.out.println(jsonData);

        // 在响应中返回 JSON 字符串
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonData);

        // 返回成功响应
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
