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
    private String userName;

    public ControllerChat(int port, String group) throws UnknownHostException, IOException {
        this.port = port;
        this.group = InetAddress.getByName(group);
        this.socket = new MulticastSocket(this.port);
        this.groupAddres = this.group.getHostName();
    }

    public void joinGroup(String userName) throws IOException {
        this.socket.joinGroup(this.group);
        this.userName = userName;
        sendMessage(this.userName, 0);
    }

    public void leaveGroup() throws IOException {
        sendMessage(this.userName, 2);
        this.socket.leaveGroup(this.group);
        this.userName = "";
    }

    public void sendMessage(String message, int messageType) throws IOException {
        switch (messageType) {
            case 0:
                message = (message + " entrou no grupo!");
                break;
            case 1:
                message = (this.userName + " diz: " + message);
                break;
            case 2:
                message = (message + " saiu do grupo!");
                break;

        }
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
