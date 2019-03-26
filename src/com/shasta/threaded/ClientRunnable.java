package com.shasta.threaded;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Class that defines a Runnable Client object to keep track of and handle a connection.
 * Credits for this class go to http://tutorials.jenkov.com/java-multithreaded-servers/multithreaded-server.html
 * <p>
 * Modified by:
 *
 * @author Chandler Severson, Shasta Networks
 * @since 2019-12-10
 * <p>Made as a starter project for the 2019 Shasta Networks/SOU CS Club Hackathon.</p>
 */
public abstract class ClientRunnable implements Runnable {

    /**
     * The socket that a Client is connected to.
     */
    private Socket clientSocket;

    /**
     * Instantiates a ClientRunnable with the specified socket.
     * @param clientSocket The Socket to run the client on.
     */
    public ClientRunnable(Socket clientSocket) {
        this.clientSocket = clientSocket;
        handleConnect();
    }

    /**
     * Looks for input and handles the input once received.
     */
    public void run() {
        try {
            InputStream input = clientSocket.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            String inStr;

            // Read in each message, one line at a time, and handle the message.
            while ((inStr = in.readLine()) != null) {
                handleMessage(inStr);
            }

            clientSocket.setKeepAlive(true);
        } catch (SocketException ex) {
            System.out.println("SocketException: " + ex);
            handleDisconnect();
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }

    /**
     * Method to handle a new message
     *
     * @param str The message to handle.
     */
    protected abstract void handleMessage(String str);

    /**
     * Method that defines disconnect procedures.
     */
    public abstract void handleDisconnect();

    /**
     * Method that handles a new connection.
     */
    public abstract void handleConnect();

    /**
     * Sends the client a message.
     * @param message The message to send.
     * @throws IOException If there are errors getting the OutputStream from the ClientSocket.
     */
    public void sendMessage(String message) throws IOException {
        getClientSocket().getOutputStream().write(message.getBytes());
    }

    /**
     * Gets the socket for this ClientRunnable
     *
     * @return This worker's socket.
     */
    public Socket getClientSocket() {
        return clientSocket;
    }


}