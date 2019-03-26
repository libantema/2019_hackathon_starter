package com.shasta.threaded;

import com.shasta.client.Client;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

/**
 * Credits for this class go to http://tutorials.jenkov.com/java-multithreaded-servers/multithreaded-server.html
 * <p>
 * Modified by:
 *
 * @author Chandler Severson
 * @since 2019-12-10
 * <p>Made as a starter project for the 2019 Shasta Networks/SOU CS Club Hackathon.</p>
 */
public class MultiThreadedServer implements Runnable {

    /**
     * The port that the server will listen on.
     */
    private int serverPort;

    /**
     * Socket for accepting connections.
     */
    private ServerSocket serverSocket = null;
    private boolean isStopped = false;

    public MultiThreadedServer(int port) {
        this.serverPort = port;
    }

    /**
     * Runs the server thread and listens on the socket.
     * Creates new {@link Client} for each new connection.
     */
    public void run() {
        openServerSocket();

        // Loop, wait for new connections while the server is still running
        while (!isStopped()) {
            Socket clientSocket;
            try {
                clientSocket = this.serverSocket.accept(); //wait for a new connection
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server Stopped.");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }

            //Client has connected, create a new Client object for them.
            Client client = new Client(clientSocket);
            //Start the Client thread.
            new Thread(client).start();
        }
    }


    /**
     * @return If the server is stopped.
     */
    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    /**
     * Stops the Server and Kills Open Connections
     */
    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    /**
     * Opens a socket to allow connections on the specified port.
     */
    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + serverPort, e);
        }
    }

}