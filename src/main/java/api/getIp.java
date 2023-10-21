/**
 * 作者：兰文捷
 * 时间：2023.8.24
 * 功能：获取IP的次数
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

@WebServlet("/getIp")
public class getIp extends HttpServlet implements getData {
    public class login{
        public String loginIp;
        public int count;

        public login() {
        }

        public login(String loginIp, int count) {
            this.loginIp = loginIp;
            this.count = count;
        }

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<login> loginArrayList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        String sql = "select loginIP ,count(*) as count from loginRecord group by loginIP;";
        ResultSet resultSet = getData.getData(sql,"loginRecord","webHomework");

        while (true){
            try {
                if (!resultSet.next())
                    break;
                else {
                    String loginIp = resultSet.getString("loginIp");
                    int count = resultSet.getInt("count");
                    login l = new login(loginIp,count);
                    loginArrayList.add(l);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        String jsonStr = objectMapper.writeValueAsString(loginArrayList);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(jsonStr);
        System.out.println(jsonStr);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
