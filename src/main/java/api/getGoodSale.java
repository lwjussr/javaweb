/**
 * 作者：兰文捷
 * 时间：2023.8.26
 * 功能：获取货物销售记录
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

@WebServlet("/getGoodSale")
public class getGoodSale extends HttpServlet implements getData {
    class goodSale {
        String goodName;
        int sale;

        public String getGoodName() {
            return goodName;
        }

        public void setGoodName(String goodName) {
            this.goodName = goodName;
        }

        public int getSale() {
            return sale;
        }

        public void setSale(int sale) {
            this.sale = sale;
        }

        public goodSale() {
        }

        public goodSale(String goodName, int sale) {
            this.goodName = goodName;
            this.sale = sale;
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<goodSale> goodSales = new ArrayList<>();
        String sql = "select goodName ,sum(changeNum) as sum from goodRecord group by goodName;";
        ResultSet resultSet = getData.getData(sql,"goodRecord","webHomework");
        while (true){
            try {
                if (!resultSet.next()) break;
                else {
                    String goodName = resultSet.getString("goodName");
                    int sum = resultSet.getInt("sum");
                    goodSale g = new goodSale(goodName,sum);
                    goodSales.add(g);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        String jsonData = objectMapper.writeValueAsString(goodSales);
        System.out.println(jsonData);

        // 在响应中返回 JSON 字符串
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonData);

        // 返回成功响应
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
