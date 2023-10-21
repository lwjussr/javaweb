package servlet;

import bean.goodInfo;
import bean.loginRecord;
import bean.user;
import com.google.gson.Gson;
import dao.loginRecordDao;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
     @description 结算按钮的实现
     @author 周懿
     @date 2023/8/31 20:37
**/
@WebServlet("/payGoods")
public class PaymentServlet extends HttpServlet {

    Connection connection;

    static int a = 0;

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String str = req.getParameter("allMessage");
        String totalPrice = req.getParameter("totalPrice");

        // 去除外层的中括号
        str = str.substring(2, str.length() - 2);

        // 按"],["分割字符串
        String[] array = str.replace("\"","").split("\\],\\[");
        String[][] result = new String[array.length][];
        for (int i = 0; i < array.length; i++) {
            // 按","分割字符串
            result[i] = array[i].split(",");
        }

        connection = getConnection();

        double allcost = 0;
        double allincome = 0;
        for(int i= 0; i < result.length;i++){
            if(!"0".equals(result[i][4])) {
                updateGoodInfo(result[i]);
                goodInfo goodInfo = getGoodInfoById(result[i]);
                allcost += goodInfo.getBuyPrice() * Double.valueOf(result[i][4]);
                allincome += goodInfo.getSellPrice() * Double.valueOf(result[i][4]);
                insertGoodRecord(goodInfo,result[i][4]);
            }
        }
        insertProfit(allcost,allincome);

    }

    private goodInfo getGoodInfoById(String[] goodInfo) throws SQLException {
        int goodId = Integer.valueOf(goodInfo[0]);

        Statement statement;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "select * from goodInfo where id =" + goodId;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
            goodInfo good = null;
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String goodName = resultSet.getString(2);
                String manufacturer = resultSet.getString(3);
                double buyPrice = resultSet.getDouble(4);
                double sellPrice = resultSet.getDouble(5);
                int goodNum = resultSet.getInt(6);
                good = new goodInfo(id,goodName,manufacturer,buyPrice,sellPrice,goodNum);
                break;
            }
            return good;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            resultSet.close();
            statement.close();
        }
    }

    private void insertGoodRecord(goodInfo goodInfo,String changeNum) throws SQLException {
        a = a + 1;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            statement = connection.createStatement();

            String goodName = goodInfo.getGoodName();
            String manufacturer = goodInfo.getManufacturer();
            Integer num = Integer.valueOf(changeNum);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.now();
            // 格式化当前日期时间
            String currentDateTime = dateTime.format(formatter);

            String insertDate = "INSERT INTO goodRecord (id,goodName, manufacturer,changeNum,changeTime) VALUES  (?, ?, ?, ?,?)";

            // 创建PreparedStatement对象，预编译SQL语句
            preparedStatement = connection.prepareStatement(insertDate);

            // 设置参数值
            preparedStatement.setInt(1, a);
            preparedStatement.setString(2, goodName);
            preparedStatement.setString(3, manufacturer);
            preparedStatement.setInt(4, num);
            preparedStatement.setString(5,currentDateTime);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            preparedStatement.close();
            statement.close();
        }
    }

    private void insertProfit(Double allcost, Double allincome) throws SQLException {
        // 创建DecimalFormat对象，指定保留两位小数的格式
        DecimalFormat df = new DecimalFormat("#.00");

        // 格式化double类型的数据，保留两位小数
        allcost = Double.valueOf(df.format(allcost));
        allincome = Double.valueOf(df.format(allincome));

        Double profit = Double.valueOf(df.format(allincome - allcost));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime dateTime = LocalDateTime.now();
        // 格式化当前日期时间
        String currentDateTime = dateTime.format(formatter);


        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        //查询利润表中相同日期的数据
        String sql = "select * from profit where sellTime = '" + currentDateTime +"'";
        System.out.println(sql);

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            //表中有数据存在表示执行更新操作
            if (resultSet.next()) {

                //获取之前利润表中的详细数据加上现有需更新的利润值作为利润表的最近利润数据
                allcost = resultSet.getDouble(1) + allcost;
                allincome = resultSet.getDouble(2) + allincome;
                profit = Double.valueOf(df.format(allincome - allcost));

                //将利润表进行更新
                sql = "update profit set cost = " + allcost + ",income=" + allincome + ",profit="
                        + profit + "where sellTime = '" + currentDateTime +"'";

                statement.executeUpdate(sql);

            } else {
                String insertDate = "INSERT INTO profit (cost,income,profit,sellTime) VALUES  (?, ?, ?,?)";

                // 创建PreparedStatement对象，预编译SQL语句
                preparedStatement = connection.prepareStatement(insertDate);

                // 设置参数值
                preparedStatement.setDouble(1, allcost);
                preparedStatement.setDouble(2, allincome);
                preparedStatement.setDouble(3, profit);
                preparedStatement.setString(4, currentDateTime);
                preparedStatement.executeUpdate();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            resultSet.close();
            statement.close();
        }
    }




    //获取连接
    public static Connection getConnection(){
        //启动数据库驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //mysql的相关信息
        String url = "jdbc:mysql://81.70.187.168:3306/webHomework";
        String user = "root";
        String pwd = "ncu111";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (connection == null) {
            System.out.println("连接失败");
            return null;
        }
        return connection;
    }

    public void updateGoodInfo(String[] goodInfo) throws SQLException {

        int goodNum = Integer.valueOf(goodInfo[3]);
        int goodId = Integer.valueOf(goodInfo[0]);

        //创建statemate类对象，用来执行sql语句
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "update goodInfo set goodNum = " + goodNum + " where id = " + goodId;
            int res = statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            statement.close();
        }
    }
}
