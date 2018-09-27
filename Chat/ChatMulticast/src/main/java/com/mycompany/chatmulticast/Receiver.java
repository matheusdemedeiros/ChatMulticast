/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chatmulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.Arrays;
import javax.swing.JTextArea;

/**
 *
 * @author Matheus
 */
public class Receiver extends Thread {

    private DatagramPacket msgIn;
    private final byte[] buffer;
    private String message;
    private JTextArea areaChat;
    private MulticastSocket socket;

    public Receiver(JTextArea areaChat, MulticastSocket socket) {
        this.buffer = new byte[1024];
        this.areaChat = areaChat;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            this.msgIn = new DatagramPacket(this.buffer, this.buffer.length);
            try {
                this.socket.receive(this.msgIn);
                this.message = new String(this.msgIn.getData());
                this.areaChat.append(this.msgIn.getAddress().getHostName() + " : " + this.message + "\n");
            } catch (IOException ex) {
                this.areaChat.append("Erro ao receber a mensagem!!\nErro: " + ex.getMessage());
            }
            this.message = new String(this.msgIn.getData());
            Arrays.fill(this.buffer, (byte) 0);
        }
    }
}
