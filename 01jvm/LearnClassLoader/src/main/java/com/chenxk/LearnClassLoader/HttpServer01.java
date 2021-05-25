package com.chenxk.LearnClassLoader;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

// 单线程的socket程序
public class HttpServer01 {
    public static void main(String[] args) throws IOException{

        SocketThread(8801).start();
        SocketThread(8802).start();
    }

    private static  Thread SocketThread(int port){
       return  new Thread(new Runnable() {
            @Override
            public void run() {
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket();
                    serverSocket.bind(new InetSocketAddress("127.0.0.1",port));
                    while(true){
                        Socket socket = serverSocket.accept();
                        service(socket);
                        Thread.sleep(100);
                    }

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private static AtomicLong counter=new AtomicLong(0);
    private static void service(Socket socket) {
        try {
//            Thread.sleep(5);
            long count=counter.incrementAndGet();
            System.out.println("收到请求"+count);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            StringBuilder builder = new StringBuilder("hello,nio1.");

            builder.append("\nAnd this is the ").append(count).append(" time. \nAnd the port is ").append(""+ socket.getLocalPort());
            String body=builder.toString();
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
            System.out.println(body);
            System.out.println("处理结束");
        } catch (IOException e) { // | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
