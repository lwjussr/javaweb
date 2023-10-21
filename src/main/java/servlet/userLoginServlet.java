package servlet;
/**
 * 编写人：兰文捷
 * 用途：将登录界面的用户输入的数据与数据库连接并进行查询
 * 编写时间：2023.7.23
 */
import bean.user;
import dao.login;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/userLogin")
public class userLoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户输入的用户名
        String userName = req.getParameter("userName");
        //获取用户输入的密码
        String password = req.getParameter("password");
        System.out.println(userName);


        //获取当前用户请求的ip地址
        String ipAddress = req.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getRemoteAddr();
        }

        user u = login.cheakLogin(userName,password,ipAddress);

        if (u!=null) {
            // 存在查询结果
            System.out.println("登录成功");
            req.setAttribute("currentUsername", userName);
            req.getRequestDispatcher("main.jsp").forward(req,resp);
        } else {
            System.out.println("登录失败");
            req.setAttribute("loginMessage", "登录失败，请检查用户名和密码。");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientIP = getClientIP(request);
        response.getWriter().write("Client IP Address: " + clientIP);
    }

    /**
     * 获取登录系统时的IP地址
     * @param request  请求
     * @return IP地址的String
     */
    private String getClientIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
