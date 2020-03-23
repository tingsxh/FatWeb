package com.atings.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/***
 ** @author xiaohui
 ** @created_at 2020/3/22
 **/
public class Server {


    public static void main(String[] args) {
        startServer();
    }


    /***
     *
     */
    public static void startServer() {
        try {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));
            System.out.println("服务端启动。。。。");
            SocketChannel accept = serverSocketChannel.accept();
            System.out.println("与" + accept.getRemoteAddress() + "建立连接");
            while (true) {
                String msg = getMsg(accept);
                System.out.println("client: " + msg);
                System.out.println("server：");
                String line = bfr.readLine();
                sendMsg(accept, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startClient() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8080));
        Scanner sc = new Scanner(System.in);
        System.out.println("客户端启动。。。");
        while (true) {
            System.out.println("client: ");
            String line = sc.nextLine();
            sendMsg(socketChannel, line);
            String msg = getMsg(socketChannel);
            System.out.println("server: " + msg);
        }

    }

    private static void sendMsg(SocketChannel accept, String msg) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(msg.getBytes());
        buffer.flip();
        accept.write(buffer);
    }

    private static String getMsg(SocketChannel accept) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        accept.read(buffer);
        // 这是读取转换的切换
        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        return new String(bytes);
    }


}
