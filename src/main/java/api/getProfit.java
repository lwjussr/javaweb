/**
 * 作者：兰文捷
 * 时间：2023.8.24
 * 功能：获取每日的利润
 */
package api;

import bean.goods;
import bean.profit;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.getData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/getProfit")
public class getProfit extends HttpServlet implements getData {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<profit> profitArrayList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        String sql = "select * from profit order by profit DESC;";
        ResultSet resultSet = getData.getData(sql,"profit","webHomework");
        int i = 0;
        while (true){
            try {
                if (!resultSet.next()){
                    break;
                }else {
                    if (i < 5){
                        double cost = resultSet.getDouble("cost");
                        double income = resultSet.getDouble("income");
                        double profit = resultSet.getDouble("profit");
                        String sellTime = resultSet.getString("sellTime");
                        profit p = new profit(cost,income,profit,sellTime);
                        profitArrayList.add(p);
                        i++;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        String jsonData = objectMapper.writeValueAsString(profitArrayList);
        System.out.println(jsonData);
        // 在响应中返回 JSON 字符串
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonData);

        // 返回成功响应
        resp.setStatus(HttpServletResponse.SC_OK);

    }
}
