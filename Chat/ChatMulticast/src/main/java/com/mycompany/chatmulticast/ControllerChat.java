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

/**
 *
 * @author Matheus
 */
public class ControllerChat {

    private int port;
    private MulticastSocket socket;
    private String groupAddres;
    private InetAddress group;
    private byte[] data;
    private DatagramPacket msgOut;

    public ControllerChat(int port, String group) throws UnknownHostException, IOException {
        this.port = port;
        this.group = InetAddress.getByName(group);
        this.socket = new MulticastSocket(this.port);
        this.groupAddres = this.group.getHostName();
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

    public DatagramPacket getMsgOut() {
        return msgOut;
    }
}
