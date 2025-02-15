import java.io.*;
import java.net.*;

public class UDP_ServidorOB {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9876);
            
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                
                ByteArrayInputStream byteStream = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream objectStream = new ObjectInputStream(byteStream);
                Object receivedObject = objectStream.readObject();

                Persona receivedPersona = new Persona("Null",0);
                try {
                    receivedPersona = (Persona) receivedObject;
                } catch (Exception e) {
                    System.out.println("Error al Transformar los datos recibidos en un objeto persona");
                }
                
                // Procesar el objeto recibido
                System.out.println("Mensaje recibido del cliente: " + receivedObject.toString());
                System.out.println("Nombre de la persona recibida: " + receivedPersona.getNombre());
                
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                
                String response = "Mensaje recibido correctamente";
                sendData = response.getBytes();
                
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}