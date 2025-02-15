import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP
{
    public static void main(String[] args) throws IOException {
        System.out.println("--- Servidor UDP ---");
        DatagramSocket socket_c1 = new DatagramSocket(5000); //Abrimos un socket para el servidor en el puerto 5000
        byte[] buffer = new byte[1024];
        System.out.println("Servidor ejecutado en el puerto (5000)...");

        //CLIENTE 1
        //Recibir datos
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket_c1.receive(packet);
        String numberStr = new String(packet.getData(), 0, packet.getLength());
        int number = Integer.parseInt(numberStr);
        System.out.println("El cliente envió: " + numberStr);
        
        //Capturamos los datos del Cliente1
        InetAddress addressCliente1 = packet.getAddress();
        int portCliente1 = packet.getPort();


        //CLIENTE 2
        //Enviamos los datos a cliente 2
        DatagramSocket socket_c2 = new DatagramSocket(5001); //Abrimos un socket para el servidor CON CLIENTE 2 en el puerto 5001
        
        InetAddress addressCliente2 = InetAddress.getByName("localhost");
        int portCliente2 = 6002;
        DatagramPacket packetToClient2 = new DatagramPacket(numberStr.getBytes(), numberStr.length(), addressCliente2, portCliente2);
        socket_c2.send(packetToClient2);
        System.out.println("Número enviado a Cliente 2");

        //Recibimos los datos de cliente2
        DatagramPacket packetFromClient2 = new DatagramPacket(buffer, buffer.length);
        socket_c2.receive(packetFromClient2);
        String resultStr = new String(packetFromClient2.getData(), 0, packetFromClient2.getLength());
        System.out.println("Factorial recibido de Cliente2: " + resultStr);

        //Enviamos factorial a cliente 1
        DatagramPacket packetToClient1 = new DatagramPacket(resultStr.getBytes(), resultStr.length(), addressCliente1, portCliente1);
        socket_c2.send(packetToClient1);
        System.out.println("Factorial enviado a Cliente 1... ");


        socket_c1.close();
        socket_c2.close();

    }
    
}
