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
import java.net.SocketException;

/**
 *
 * @author Aluno
 */
public class Connection {

    public static void main(String[] args) {

        try {
            int port = 50000;
            InetAddress group = InetAddress.getByName("228.5.6.7");
            String msg = "multicast_test";
            MulticastSocket socket = new MulticastSocket(port);
            socket.joinGroup(group);
            byte[] data = msg.getBytes();
            DatagramPacket msgOut = new DatagramPacket(data, data.length, group, port);
            socket.send(msgOut);

            // get message from others in the group
            byte[] buffer = new byte[1000];
            for (int i = 0; i < 3; i++) {
                DatagramPacket msgIn = new DatagramPacket(buffer, buffer.length);
                socket.receive(msgIn);
                System.out.println("Received: " + new String(msgIn.getData()));
            }
            socket.leaveGroup(group);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        }
    }

}

