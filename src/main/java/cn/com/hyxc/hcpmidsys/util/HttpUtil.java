package cn.com.hyxc.hcpmidsys.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2020年11月10日 16:56
 */
public class HttpUtil {
    public static void doPost(String str,String json) {

        URL url = null;
        try {
            url = new URL(str);
            URLConnection con = url.openConnection();
            // post请求必须设置下面两项
            con.setDoOutput(true);
            con.setDoInput(true);
            // 不使用缓存
            con.setUseCaches(false);
            // 这句是打开链接
            OutputStream out = con.getOutputStream();
            // 把数据写到报文;
            out.write((json).getBytes());

            out.flush();
            out.close();
            // 这句才是真正发送请求
            con.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
