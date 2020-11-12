package cn.com.hyxc.hcpmidsys.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * HTTP 请求工具类
 *
 * @Description: TODO
 * @author: scott
 * @date: 2020年11月10日 16:56
 */
public class HttpUtil {


    /**
     * 发送请求
     * 注明：发送请求时开启子线程避免阻塞
     *
     * @param str  http://192.168.0.1:port/xxx
     * @param json
     */
    public static void sendHttp(String str,String json) {
       new Thread(new Runnable() {
           @Override
           public void run() {
               URL url = null;
               try {
                   url = new URL(str);
                   URLConnection con = url.openConnection();
                   // post请求必须设置下面两项
                   con.setDoOutput(true);
                   con.setDoInput(true);
                   // 不使用缓存
                   con.setUseCaches(false);
                   con.setConnectTimeout(1500);
                   // 这句是打开链接
                   OutputStream out = con.getOutputStream();

                   // 把数据写到报文;
                   out.write((json).getBytes());
                   out.flush();
                   out.close();
               } catch (MalformedURLException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }).run();
    }
}
