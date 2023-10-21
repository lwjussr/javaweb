package servlet;

import bean.user;
import dao.register;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/userRegister")
public class userRegisterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取用户输入的用户名
        String userName = req.getParameter("username");
        System.out.println(userName);
        //获取用户输入的密码
        String password = req.getParameter("password");
        //获取用户选择的用户类型
        String userType = req.getParameter("userType");

        int userNo = 0;


        user u = new user(userName,password,userNo);

        try {
            if(register.cheakRegister(u)!=null){
                System.out.println("登录成功");
                req.getRequestDispatcher("main.jsp").forward(req,resp);
            } else {
                // 未找到数据
                System.out.println("登录失败");
                req.setAttribute("currentUsername", userName);
                req.setAttribute("loginMessage", "注册失败，用户已存在");
                req.getRequestDispatcher("index.jsp").forward(req,resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
