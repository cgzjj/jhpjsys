package cn.com.hyxc.hcpmidsys.modulelogin.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 综合应用平台模拟接口
 *
 * @author yuanyc
 * @date 2020-10-30
 */
@RestController
public class SimulaController {


    @RequestMapping(value = "/user/action", method = RequestMethod.POST)
    public void action(HttpServletRequest request) {
        System.out.println(" 类型 : " + request.getParameter("onType"));
        System.out.println(" 数据 : " + request.getParameter("reqdata"));
        System.out.println(" 编码格式 : " + request.getParameter("charset"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("onType",request.getParameter("onType"));
        jsonObject.put("reqdata",request.getParameter("reqdata"));
        jsonObject.put("charset",request.getParameter("charset"));
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://192.168.101.108:8080/user/action");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestMethod("POST"); // 设置请求方式
            conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            conn.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            conn.connect();
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(jsonObject.toJSONString());
            out.flush();
            out.close();
            System.out.println("发送成功");
        } catch (Exception e) {
            System.out.println("发送异常");
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }
}
