package cn.com.hyxc.hcpmidsys.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2020年11月10日 16:57
 */
public class RequestJsonUtil {
    public static String getRequestJsonString(HttpServletRequest request) {
        String submitMehtod = request.getMethod();
        // GET
        try {
            if (submitMehtod.equals("GET")) {
                return new String(request.getQueryString().getBytes("iso-8859-1"), "utf-8").replaceAll("%22", "\"");
                // POST
            } else {
                return getRequestPostStr(request);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return new JSONObject().toJSONString();
    }

    public static String getRequestPostStr(HttpServletRequest request) throws IOException{
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

    public static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException{
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {


            int readlen = request.getInputStream().read(buffer, i,
                        contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }
}
