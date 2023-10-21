/**
 * 作者：兰文捷
 * 时间：2023.8.16
 * 功能：删除货物信息的ajax后端api
 */
package api;

import dao.getData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteGoodInfo")
public class deleteGoodInfo extends HttpServlet implements getData {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        String sql = "DELETE FROM goodInfo WHERE id = '" + id + "'";;
        getData.deleteData(sql,"goodInfo","webHomework");
    }
}
