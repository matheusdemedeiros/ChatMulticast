/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chatmulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus
 */
public class ControllerChat extends Thread {

    private int port;
    private MulticastSocket socket;
    private String groupAddres;
    private InetAddress group;
    private String msg;
    private byte[] data;
    private DatagramPacket msgOut;
    private DatagramPacket msgIn;
    private byte[] buffer;
    private String message;

    public ControllerChat(int port, String group) throws UnknownHostException, IOException {
        this.port = port;
        this.group = InetAddress.getByName(group);
        this.socket = new MulticastSocket(this.port);

    }

    public void joinGroup() throws IOException {
        this.socket.joinGroup(this.group);
    }

    public void leaveGroup() throws IOException {
        this.socket.leaveGroup(this.group);
    }

    public void sendMessage(String message) throws IOException {
        this.data = message.getBytes();
        this.msgOut = new DatagramPacket(this.data, this.data.length, this.group, this.port);
        this.socket.send(this.msgOut);
    }

    public void receiveMessage() throws IOException {
        this.buffer = new byte[1000];
        while (true) {
            this.msgIn = new DatagramPacket(this.buffer, this.buffer.length);
            this.socket.receive(this.msgIn);
            this.message = new String(this.msgIn.getData());
        }

    }

    public int getPort() {
        return port;
    }

    public MulticastSocket getSocket() {
        return socket;
    }

    public String getGroupAddres() {
        return groupAddres;
    }

    public InetAddress getGroup() {
        return group;
    }

    public String getMsg() {
        return msg;
    }

    public byte[] getData() {
        return data;
    }

    public DatagramPacket getMsgOut() {
        return msgOut;
    }

    public DatagramPacket getMsgIn() {
        return msgIn;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void run() {
        try {

            receiveMessage();

        } catch (IOException ex) {
            Logger.getLogger(ControllerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
