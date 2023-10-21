/**
 * 作者：兰文捷
 * 时间：2023.8.27
 * 功能：获取用户登录次数
 */
package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dao.getData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/getUserLoginAmount")
public class getUserLoginAmount extends HttpServlet implements getData {
    //用于存储登录次数相关信息的内部类
   public class loginAmount  {
        String userName;
        int loginNum;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getLoginNum() {
            return loginNum;
        }

        public void setLoginNum(int loginNum) {
            this.loginNum = loginNum;
        }

        public loginAmount(String userName, int loginNum) {
            this.userName = userName;
            this.loginNum = loginNum;
        }

        public loginAmount() {
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList <loginAmount> amountArrayList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        String sql = "SELECT userName, COUNT(*) AS count FROM loginRecord GROUP BY userName" ;
        ResultSet resultSet = getData.getData(sql,"loginRecord","webHomework");
        while (true){
            try {
                if (!resultSet.next())
                    break;
                else{
                    int loginNum = resultSet.getInt("count");
                    String userName = resultSet.getString("userName");
                    loginAmount l = new loginAmount();
                    l.loginNum = loginNum;
                    l.userName = userName;
                    amountArrayList.add(l);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        amountArrayList.forEach((i)->{
            System.out.println(i.loginNum);
            System.out.println(i.userName);
        });
        String jsonData = objectMapper.writeValueAsString(amountArrayList);

        System.out.println(jsonData);
        // 在响应中返回 JSON 字符串
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonData);

        // 返回成功响应
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
