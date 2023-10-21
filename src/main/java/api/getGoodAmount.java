/**
 * 作者：兰文捷
 * 时间：2023.8.24
 * 功能：获取商品数量
 */
package api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import bean.goods;
import bean.sendMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.getData;

@WebServlet("/getGoodAmount")
public class getGoodAmount extends HttpServlet implements getData {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<goods> goodsArrayList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();


        String sql = "select * from goodInfo order by goodNum DESC;";
        ResultSet resultSet = getData.getData(sql,"goodInfo","webHomework");

        int i = 0;
        while (true){
            try {
                if (!resultSet.next()){
                    break;
                }else {
                    if (i < 10){
                        int goodId = resultSet.getInt("id");
                        String goodName = resultSet.getString("goodName");
                        String manufacturer = resultSet.getString("manufacturer");
                        float buyPrice = resultSet.getFloat("buyPrice");
                        float sellPrice = resultSet.getFloat("sellPrice");
                        int goodNum = resultSet.getInt("goodNum");
                        goods g = new goods(goodId,goodName,manufacturer,buyPrice,sellPrice,goodNum);
                        goodsArrayList.add(g);
                        i++;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        String jsonData = objectMapper.writeValueAsString(goodsArrayList);
        System.out.println(jsonData);
        // 在响应中返回 JSON 字符串
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonData);

        // 返回成功响应
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
