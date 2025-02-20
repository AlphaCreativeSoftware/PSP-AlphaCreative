import java.net.*;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) throws Exception {
        System.out.println("--- CLIENTE UDP ---");
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getLocalHost();
        int port = 5000;
        
        System.out.println("Cliente(1) Iniciado....");
        String ipToSend = writeIp("Introduzca la IP a enviar: ");
        byte[] buffer = ipToSend.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, port);
        socket.send(packet);
        System.out.println("Datos enviados al Servidor...");
        
        System.out.println("Esperando respuesta del Servidor (IP BINARY)...");
        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(receivePacket);
        String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
        
        if(response.equals("fail")) {
            System.out.println("IP " + ipToSend + " inválida");
        } else {
            System.out.println("IP binaria: " + response);
        }
        
        socket.close();
    }
    
    public static String writeIp(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        String ip = scanner.nextLine();
        
        String[] splittedIp = ip.split("\\.");
        if(splittedIp.length != 4) {
            System.out.println("IP inválida");
            return ip;
        }
        
        for (String part : splittedIp) {
            if (!part.matches("\\d+")) {
                System.out.println("IP inválida");
                return ip;
            }
            int ipPart = Integer.parseInt(part);
            if (ipPart < 0 || ipPart > 255) {
                System.out.println("IP inválida");
                return ip;
            }
        }
        return ip;
    }
}
