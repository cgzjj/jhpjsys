package cn.com.hyxc.hcpmidsys.util;

import cn.com.hyxc.hcpmidsys.container.ContainerManager;
import cn.com.hyxc.hcpmidsys.container.ControlComputer;
import cn.com.hyxc.hcpmidsys.container.Queue;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * xml文档查询类
 *
 * @author yuanyc
 */
public class XmlQuery {


    /**
     * 发送XML报文
     *
     * @author yuanyc 2020-11-12
     * @param xmlInfo xml报文
     * @return
     */
    public static String sendHttps(String xmlInfo,String url) {
        //String a="";//请求参数
        String result = "";
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            //trustAllHosts();
            URL realUrl = new URL(url);
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
            conn.setConnectTimeout(1500);
            conn.setReadTimeout(5000);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 加密
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

    public static List<ControlComputer> getControlComputerConfig(){
        List<ControlComputer> controlComputers = new Vector<ControlComputer>();
        try {
            Document document = new SAXReader().read("src/main/resources/templates/xml/25C10.xml");
            Element element = document.getRootElement();
            List<Element> list  = element.elements();
            for (Element e:
                    list) {
                controlComputers.add(new ControlComputer.Builder()
                        .setCkbh(e.elementText("ckbh"))
                        .setJsjip(e.elementText("jsjip"))
                        .setJsjlb(e.elementText("jsjlb"))
                        .setKbywlb(e.elementText("kbywlb"))
                        .setSbkzjsjbh(e.elementText("sbkzjsjbh"))
                        .build());
            }
        }catch ( DocumentException e){
            e.printStackTrace();
        }
        return controlComputers;
    }

    /**
     * 补充取号信息写入 xml封装
     *
     * @author yuanyc
     */
    public static String replenishWriteXml(Queue queuing,ControlComputer computer){
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sb.append("	   <queue>");
        sb.append("       <ywckjsjip>"+computer.getJsjip()+"</ywckjsjip>");
        sb.append("       <sbkzjsjip>"+computer.getSbkzjsjip()+"</sbkzjsjip>");
        sb.append("       <qhxxxlh>"+queuing.getQhxxxlh()+"</qhxxxlh>");
        sb.append("       <pdh>"+queuing.getPdh()+"</pdh>");
        sb.append("       <ywlb>"+queuing.getYwlb()+"</ywlb>");
        sb.append("       <sfzmhm>"+queuing.getSfzmhm()+"</sfzmhm>");
        sb.append("       <dlrsfzmhm>"+queuing.getDlrsfzmhm()+"</dlrsfzmhm>");
        sb.append("       <qhrxm>"+queuing.getQhrxm()+"</qhrxm>");
        sb.append("       <rylb>"+queuing.getRylb()+"</rylb>");
        sb.append("       <rxbdjg>"+queuing.getRxbdjg()+"</rxbdjg>");
        sb.append("       <jzzply>"+queuing.getJzzply()+"</jzzply>");
        sb.append("       <jzbdzp>"+queuing.getJzbdzp()+"</jzbdzp>");
        sb.append("       <xczp>"+queuing.getXczp()+"</xczp>");
        sb.append("    </queue>");
        return sb.toString();
    }

    /**
     * 评价结果 xml封装
     *
     * @author yuanyc
     * @return
     */
    public static String evaluationWriteXml(Map<String,String> evaluationMap){
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sb.append("	   <queue>");
        sb.append("       <qhxxxlh>"+evaluationMap.get("qhxxxlh")+"</qhxxxlh>");
        sb.append("       <pjlb>"+evaluationMap.get("pjlb")+"</pjlb>");
        sb.append("       <pjjg>"+evaluationMap.get("pjjg")+"</pjjg>");
        sb.append("    </queue>");
        return sb.toString();
    }

    public static void main(String[] args) {
        //List<ControlComputer> controlComputers = containerManager.getControlComputers();
        //System.out.println(controlComputers);
    }

   
}
