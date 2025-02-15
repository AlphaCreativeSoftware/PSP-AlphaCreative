package com.alphacreative.sockets.udp_obj;

import java.io.*;
import java.net.*;

public class UDP_Objeto_Cliente { //Se tienen que tratar enviando los bytes del objeto y para recibir convirirlos en un objeto (No se transfiere directamente)
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            
            // Objeto a enviar
            String message = "Hola, servidor!";
            
            objectStream.writeObject(message);
            byte[] sendData = byteStream.toByteArray();
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            socket.send(sendPacket);
            
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            String response = new String(receivePacket.getData());
            System.out.println("Respuesta del servidor: " + response);
            
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}