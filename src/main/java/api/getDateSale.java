/**
 * 作者：兰文捷
 * 时间：2023.8.16
 * 功能：获取货物每日的销售记录
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

@WebServlet("/getDateSale")
public class getDateSale extends HttpServlet implements getData {

    class dateSale{
        String data;
        int count;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public dateSale() {
        }

        public dateSale(String data, int count) {
            this.data = data;
            this.count = count;
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<dateSale> dateSales = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        String sql = "SELECT DATE(changeTime) AS date, sum(changeNum) AS count FROM goodRecord GROUP BY DATE(changeTime);";
        ResultSet resultSet = getData.getData(sql,"goodRecord","webHomework");
        while (true){
            try {
                if (!resultSet.next())
                    break;
                else {
                    String data = resultSet.getString("date");
                    int count = resultSet.getInt("count");
                    dateSale t = new dateSale(data,count);
                    dateSales.add(t);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        String jsonstr = objectMapper.writeValueAsString(dateSales);
        System.out.println(jsonstr);
        resp.getWriter().write(jsonstr);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

    }
}
