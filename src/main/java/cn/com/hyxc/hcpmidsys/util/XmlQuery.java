package cn.com.hyxc.hcpmidsys.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.XMLReader;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * xml文档查询类
 */
public class XmlQuery {


    /**
     * 查询 报文
     *
     * @param xmlInfo xml报文
     * @return
     */
    public static String sendHttps(String xmlInfo) {
        //String a="";//请求参数
        String result = "";
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            //trustAllHosts();
            URL realUrl = new URL("https://apitest.bwjf.cn/openNozzle");

            //如果是https就是下面两行代码
            /*HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
            conn.setHostnameVerifier(DO_NOT_VERIFY);*/
            //如果是http则是下面一行代码
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "text/plain;charset=utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //加密
            String base64keyString = encoder(xmlInfo);
            // 发送请求参数
            out.print(base64keyString);
            System.out.println("发送报文："+xmlInfo);
            System.out.println("加密报文："+base64keyString);
            // flush输出流的缓冲
            out.flush();
            // System.out.println("响应报文："+conn.getInputStream());
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            //根据接收的url，从中获取xml文档流生成xml文件
            //getDocument(in);
            System.out.println("响应报文："+result);
            String key = decoder(result);
            System.out.println("响应解密报文："+key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {// 使用finally块来关闭输出流、输入流
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 从inputStream里面读取文件
     * @param in
     * @return
     */
    public static Document getDocument(BufferedReader in) {
        Document doc = null;
        SAXReader  reader = null;
        try {
            doc = reader.read(in);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 从url读取文件
     */
    public static Document getDocument(String url) {
        Document doc = null;
        SAXReader  reader = null;
        try {
            doc = reader.read(url);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doc;
    }


    /**
     *  加密 base64
     */
    public static String encoder(String xmlInfo) {
        byte[] bytes=xmlInfo.getBytes();
        String base64keyString =new BASE64Encoder().encodeBuffer(bytes);
        return base64keyString;
    }
    /**
     *  解密 base64
     */
    public static String decoder(String xmlInfo) throws IOException {
        byte[] bt = (new BASE64Decoder()).decodeBuffer(xmlInfo);
        String key=new String(bt);
        return key;
    }

    /**
     * 请求格式
     * @return
     */
    public String requestXml(){
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sb.append("	   <business id=\"CXSBZT\" >");
        sb.append("    <body>");
        sb.append("    <input>");
        sb.append("        <jsbh>" +"节点内容" + "</jsbh>");
        sb.append("    </input>");
        sb.append("    </body>");
        sb.append("</business>");
        return sb.toString();
    }

    public static void main(String[] args) {
        ClassPathResource fileResource = new ClassPathResource("/xml/25C10.xml");
        System.out.println(fileResource);
        try {
            Document document = new SAXReader().read(fileResource.getFile());
            Element element = document.getRootElement();
            List<Element> list  = element.elements("root");
            for (Element e:
                 list) {
                System.out.println("设备控制计算机 : "+ e.elementText("sbkzjsjbh"));
                System.out.println("计算机类别 : "+ e.elementText("jsjlb"));
                System.out.println("计算机IP : "+ e.elementText("jsjip"));
                System.out.println("窗口编号 : "+ e.elementText("ckbh"));
                System.out.println("可办业务类别 : "+ e.elementText("kbywlb"));

            }
        }catch (IOException | DocumentException e){
            e.printStackTrace();
        }

    }

   
}
