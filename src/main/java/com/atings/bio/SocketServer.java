package com.atings.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/***
 ** @author xiaohui
 ** @created_at 2020/3/22
 **/
public class SocketServer {

    public static void main(String[] args) {
        SocketServer.startServer(8080);
    }


    public static void startServer(int port) {

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            OutputStream outputStream = accept.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));

            PrintWriter printWriter=new PrintWriter(bw,true);

            System.out.println("连接成功-----"+accept.getInetAddress().getHostAddress());
            while (true) {
                String line = br.readLine();
                if (line!=null){
                    System.out.println("服务端接收到了请求：" + line);
                    if ("close".equals(line)) {
                        break;
                    }
                    printWriter.println("我们收到了您的请求"+line.length());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
