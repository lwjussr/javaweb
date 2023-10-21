/**
 * 作者：兰文捷
 * 时间：2023.8.24
 * 功能：将IP地址的归属地进行查询
 */
package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class getAddress {
    public static String getIp(String ip){
        ObjectMapper objectMapper = new ObjectMapper();
        String apiurl = "http://opendata.baidu.com/api.php?query="+ip+"&co=&resource_id=6006&oe=utf8";//"https://opendata.baidu.com/api.php?query="+ ip +"&co=&resource_id=6006&oe=utf8";
        ArrayList<String> arr = new ArrayList<>();
        try {
            //创建一个URL对象来将地址容纳进去从而后续来进行连接
            URL url = new URL(apiurl);
            //将api地址开放连接打开
            URLConnection urlConnection = url.openConnection();
            //读取信息
            InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream(),"UTF-8");
            //将读取的信息拼接成一个字符串
            StringBuilder sb = new StringBuilder();

            //将读取到的信息转化为字符串
            int ch;
            while ((ch = inputStreamReader.read()) != -1){
                sb.append((char) ch);
            }
            inputStreamReader.close();
            System.out.println(sb.toString());

            //由于读取回来的是json格式，所以我们需要将json格式化
            JsonNode jsonNode = objectMapper.readTree(sb.toString());
            //获取json数据当中的data类
            JsonNode data = jsonNode.get("data");
            //由于data类是json的数组格式所以我们需要进行遍历
            data.forEach(p-> {
                //获取location属性后面的数据
                String location = p.get("location").asText();
                arr.add(location);
                System.out.println("Location: " + location);
            });
            if (ip.equals("0:0:0:0:0:0:0:1")){
                arr.add("localhost");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(arr.get(0));
        return arr.get(0);
    }
}
