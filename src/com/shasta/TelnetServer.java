package com.shasta;

import com.shasta.threaded.MultiThreadedServer;

/**
 * Multithreaded server that users can telnet into.
 *
 * @author Chandler Severson, Shasta Networks
 * @since 2019-12-10
 * <p>Made as a starter project for the 2019 Shasta Networks/SOU CS Club Hackathon.</p>
 */
public class TelnetServer {

    /**
     * Port to listen on
     */
    private static final int port = 7777;

    /**
     * Server Object
     */
    private static MultiThreadedServer server;

    /**
     * Entry point for the Telnet Server Program.
     * @param args N/A
     */
    public static void main(String[] args) {
        System.out.println(String.format("Starting server on port %d.", port));

        server = new MultiThreadedServer(port);
        Thread main = new Thread(server);
        main.start();
        try {
            //keep the server open indefinitely.
            main.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Stopping Server.");
        server.stop();
    }
}


