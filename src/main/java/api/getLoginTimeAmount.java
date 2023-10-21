/**
 * 作者：兰文捷
 * 时间：2023.8.26
 * 功能：获取每日登录次数
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

@WebServlet("/getLoginTimeAmount")
public class getLoginTimeAmount extends HttpServlet implements getData {

    class timeCount{
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

        public timeCount() {
        }

        public timeCount(String time, int count) {
            this.time = time;
            this.count = count;
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<timeCount> timeCounts = new ArrayList<>();
        String sql = "SELECT DATE(loginTime) AS date, COUNT(*) AS count FROM loginRecord GROUP BY DATE(loginTime);";
        ResultSet resultSet = getData.getData(sql,"loginRecord","webHomework");
        while (true){
            try {
                if (!resultSet.next())
                    break;
                else {
                    String time = resultSet.getString("date");
                    int count = resultSet.getInt("count");
                    timeCount t = new timeCount(time,count);
                    timeCounts.add(t);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        String jsonstr = objectMapper.writeValueAsString(timeCounts);
        resp.getWriter().write(jsonstr);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

    }
}
