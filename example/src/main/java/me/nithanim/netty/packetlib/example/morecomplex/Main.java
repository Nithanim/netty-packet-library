package me.nithanim.netty.packetlib.example.morecomplex;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) {
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);
        
        Runnable serverRunnable = new me.nithanim.netty.packetlib.example.morecomplex.server.Main(9090);
        Thread serverThread = new Thread(serverRunnable);
        serverThread.start();
        
        Runnable clientRunnable = new me.nithanim.netty.packetlib.example.morecomplex.client.Main();
        Thread clientThread = new Thread(clientRunnable);
        clientThread.start();
    }
}
