package com.atings.bio;

import java.io.*;
import java.net.Socket;

/***
 ** @author xiaohui
 ** @created_at 2020/3/22
 **/
public class SocketClient {
    public static void main(String[] args) {
        startClient();
    }

    public static void startClient() {
        try {
            Socket socket = new Socket("localhost", 8080);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            PrintWriter printWriter=new PrintWriter(bufferedWriter,true);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("请输入消息:");
                String msg = bfr.readLine();
                //发送给服务端
                printWriter.println(msg);
                if ("close".equals(msg)) {
                    break;
                }
                System.out.println("客户端接收到返回：" + bufferedReader.readLine());
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
