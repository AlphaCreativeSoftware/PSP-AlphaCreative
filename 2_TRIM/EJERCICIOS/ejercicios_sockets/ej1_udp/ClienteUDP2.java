
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP2 {
    public static void main(String[] args) throws IOException {
        System.out.println("--- CLIENTE 2 UDP ---");
        //Inicializamos los que usaremos más tarde
        InetAddress serverAddress = InetAddress.getLocalHost();
        int serverPort = 5001; //Puerto a donde enviaremos el número ya que en el server hemos abierto un socket en 5001 para el cliente 2
        byte[] buffer = new byte[1024];

        //Abrimos un socket para nuestro cliente 1 en el puerto 6002
		DatagramSocket client2_socket = new DatagramSocket(6002);
        System.out.println("Cliente 2 esperando número...");

        //Recibimos el número del servidor
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        client2_socket.receive(packet);
        String numeroStr = new String(packet.getData(), 0, packet.getLength());
        int numero = Integer.parseInt(numeroStr);
        System.out.println("Número recibido del servidor: " + numero);

        //Calcular factorial
        int numeroFac = factorial(numero);
        String numeroFacStr = Integer.toString(numeroFac);

        //Enviar resultado al servidor
        System.out.println("Enviando el factorial " + numeroFacStr + "...");
        DatagramPacket factorialPacket = new DatagramPacket(
            numeroFacStr.getBytes(), numeroFacStr.length(), serverAddress, serverPort
        );
        client2_socket.send(factorialPacket);
        System.out.println("Factorial enviado al servidor...");

        client2_socket.close();
	}


    public static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        int factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }
    
}
