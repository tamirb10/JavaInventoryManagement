package com.hit.server;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    
    private static final int THREAD_POOL_SIZE = 2;
    private static boolean serverUp = true;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8080);
            while (serverUp) {
                Socket someClient = server.accept();
                threadPool.execute(new Request(someClient));
            }
            threadPool.shutdown();
            server.close();
        } catch (Exception e) {
            System.out.println("Tired of waiting for connection");
        }
    }
}