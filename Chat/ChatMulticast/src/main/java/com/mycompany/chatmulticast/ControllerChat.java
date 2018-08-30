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
    private String msg;
    private byte data[];
    private DatagramPacket msgOut;

    public ControllerChat(int port, String group) throws UnknownHostException, IOException {
        this.group = InetAddress.getByName(group);
        this.socket = new MulticastSocket(port);

    }

    public void joinGroup() throws IOException {
        socket.joinGroup(group);
    }

    public void leaveGroup() throws IOException {
        socket.leaveGroup(group);
    }

    public void sendMessage(String message) throws IOException {
        this.msgOut = new DatagramPacket(data, data.length, group, port);
        socket.send(msgOut);
    }

}
