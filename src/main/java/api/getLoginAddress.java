/**
 * 作者：兰文捷
 * 时间：2023.8.26
 * 功能：获取登录IP的归属地
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

@WebServlet("/getLoginAddress")
public class getLoginAddress extends HttpServlet implements getData {
    class addressCount{
        String address;
        int count;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public addressCount() {
        }

        public addressCount(String address, int count) {
            this.address = address;
            this.count = count;
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<addressCount> addressCounts = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        ResultSet resultSet;

        String sql = "select address ,Count(*) as count from loginRecord group by address";
        resultSet = getData.getData(sql,"loginRecord","webHomework");
        while (true){
            try {
                if (!resultSet.next())
                    break;
                else {
                    String address = resultSet.getString("address");
                    int count = resultSet.getInt("count");
                    addressCount a = new addressCount(address,count);
                    addressCounts.add(a);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        String jsonData = objectMapper.writeValueAsString(addressCounts);
        System.out.println(jsonData);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonData);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
