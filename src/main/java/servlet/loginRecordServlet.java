package servlet;

import bean.loginRecord;
import dao.loginRecordDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/loginRecord")
public class loginRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<loginRecord> recordArrayList;
        try {
            recordArrayList = loginRecordDao.getLoginRecordList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(666);
        req.setAttribute("loginRecordList", recordArrayList);
        req.getRequestDispatcher("main.jsp").forward(req,resp);
    }
}
