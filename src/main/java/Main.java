import api.getAddress;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        String apiUrl = "https://opendata.baidu.com/api.php?query=106.6.107.167&co=&resource_id=6006&oe=utf8";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //创建一个URL对象来将地址容纳进去从而后续来进行连接
            URL url = new URL(apiUrl);
            //将api地址开放连接打开
            URLConnection urlConnection = url.openConnection();
            //读取信息
            InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
            //将读取的信息拼接成一个字符串
            StringBuilder sb = new StringBuilder();


            int ch;
            while ((ch = inputStreamReader.read()) != -1){
                sb.append((char) ch);
            }
            inputStreamReader.close();
            System.out.println(sb.toString());

            JsonNode jsonNode = objectMapper.readTree(sb.toString());
            JsonNode data = jsonNode.get("data");
            data.forEach(p->{
                String location = p.get("location").asText();
                System.out.println("Location: " + location);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(getAddress.getIp("8"));
    }

}