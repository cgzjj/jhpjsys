package cn.com.hyxc.hcpmidsys.modulelogin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSendAndRecv {
    public static void main(String[] args) {
        new Thread(new DD()).start();
        //先开启接收端的线程
                //new Thread(new TT()).start();
        //        //在开启发送端的线程
    }
}
//发送端的代码如下：
class TT implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(123);
            Socket s = new Socket("192.168.101.108", 8023);
            //创建一个socket绑定的端口和地址为：9977，本机。
            System.out.println(321);
            OutputStream oos = s.getOutputStream();
            //获取到输出流
            String line = "S02";
            OutputStreamWriter outwriter = new OutputStreamWriter(oos,"UTF-8");
            outwriter.write(line);
            outwriter.flush();
            System.out.println(111);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}

class DD implements Runnable{

    @Override
    public void run() {
        ServerSocket serverSocket =null;
        Socket socket = null;
        InputStream in = null;
        try {
            System.out.println("sssss");
             serverSocket = new ServerSocket(8777);
            System.out.println("2312312");
             socket = serverSocket.accept();
             System.out.println("214345");
             in = socket.getInputStream();
            System.out.println("12546756879");
            byte[] bytes = new byte[1024];
            int len;
            len = in.read(bytes);
            System.out.println("data : "+new String(bytes,0,len));
            InetAddress address = socket.getInetAddress();
            System.out.println("sender : "+address);



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {

            try {
                if(in != null){
                    in.close();
                }
                if(socket != null){
                    socket.close();
                }
                if(serverSocket != null){
                    serverSocket.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }
}
