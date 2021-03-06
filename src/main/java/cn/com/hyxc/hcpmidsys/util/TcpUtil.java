package cn.com.hyxc.hcpmidsys.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: TODO
 * @author: jiangt
 * @date: 2020年11月12日 10:47
 */
public class TcpUtil {

    /**
     * 发送tcp消息到评价器
     * @param receiveIp
     * @param port
     * @param msg
     * @return
     */
    public static int senderTcp(String receiveIp,Integer port,String msg){
        int s = 1;
        OutputStreamWriter outwriter = null;
        Socket client = null;
        OutputStream outputStream = null;
        try {
            client = new Socket(receiveIp, port);
            //创建一个socket绑定的端口和地址为：9977，本机。
            outputStream = client.getOutputStream();
            //获取到输出流
            outwriter = new OutputStreamWriter(outputStream, "UTF-8");
            outwriter.write(msg);
            outwriter.flush();
        } catch(IOException e){
            s=2;
            e.printStackTrace();
        } finally {
            try {
                if (outwriter!=null){
                    outwriter.close();
                }
                if (outputStream!=null){
                    outputStream.close();
                }
                if (client!=null){
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }
    /**
     * 接收从评价发送来的tcp消息
     * @param port
     * @param qhxxlh
     * @return
     */
    public static int receiverTcp(Integer port,String qhxxlh){
        int msg = 0;
        InputStream in = null;
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            in = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = in.read(bytes);
            msg = len;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (socket != null) {
                    socket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }

}
