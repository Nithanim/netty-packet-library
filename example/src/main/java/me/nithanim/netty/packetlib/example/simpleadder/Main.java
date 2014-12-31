package me.nithanim.netty.packetlib.example.simpleadder;

public class Main {
    public static void main(String[] args) {
        Runnable serverRunnable = new me.nithanim.netty.packetlib.example.simpleadder.server.Main(9090);
        Thread serverThread = new Thread(serverRunnable);
        serverThread.start();
        
        Runnable clientRunnable = new me.nithanim.netty.packetlib.example.simpleadder.client.Main();
        Thread clientThread = new Thread(clientRunnable);
        clientThread.start();
    }
}
