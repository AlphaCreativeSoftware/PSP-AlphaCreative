import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) throws Exception {
        System.out.println("--- SERVIDOR UDP ---");
        int port = 5000;
        DatagramSocket socket = new DatagramSocket(port);
        
        while (true) {
            System.out.println("Esperando conexión de algún cliente...");
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            
            String receivedIp = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("IP recibida: " + receivedIp);
            
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            
            String response;
            if (!checkIP(receivedIp)) {
                System.out.println("(" + receivedIp + ") no es una IP válida, enviando error al Cliente...");
                response = "fail";
            } else {
                response = ipToBinary(receivedIp);
                System.out.println("(" + receivedIp + ") es una IP válida, enviando conversión binaria al Cliente...");
            }
            
            byte[] sendBuffer = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
            socket.send(sendPacket);
            System.out.println("Respuesta enviada al Cliente...");
        }
    }
    
    public static String ipToBinary(String ip) {
        String[] octetos = ip.split("\\.");
        StringBuilder ipBinaria = new StringBuilder();
        for (int i = 0; i < octetos.length; i++) {
            int numero = Integer.parseInt(octetos[i]);
            String binario = String.format("%8s", Integer.toBinaryString(numero)).replace(' ', '0');
            ipBinaria.append(binario);
            if (i < octetos.length - 1) {
                ipBinaria.append(".");
            }
        }
        return ipBinaria.toString();
    }
    
    public static boolean checkIP(String ip) {
        String[] splittedIp = ip.split("\\.");
        if (splittedIp.length != 4) {
            return false;
        }
        for (String part : splittedIp) {
            if (!part.matches("\\d+")) {
                return false;
            }
            int ipPart = Integer.parseInt(part);
            if (ipPart < 0 || ipPart > 255) {
                return false;
            }
        }
        return true;
    }
}