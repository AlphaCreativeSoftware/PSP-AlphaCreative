import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP1 {
    public static void main(String[] args) throws IOException {
        System.out.println("--- CLIENTE 1 UDP ---");
        //Inicializamos los que usaremos más tarde
        Scanner scanner = new Scanner(System.in);
        InetAddress serverAddress = InetAddress.getLocalHost();
        int serverPort = 5000;
        byte[] buffer = new byte[1024];

		//Abrimos un socket para nuestro cliente 1 en el puerto 6001
		DatagramSocket client1_socket = new DatagramSocket(6001);
        System.out.print("Ingrese un número para calcular su factorial: ");
        int numero = scanner.nextInt();

        //Enviamos ese número al servidor
        String mensaje = Integer.toString(numero);
        DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.length(), serverAddress, serverPort);
        client1_socket.send(packet);

        //Recibimos el número del servidor
        DatagramPacket packetFactorial = new DatagramPacket(buffer, buffer.length);
        client1_socket.receive(packetFactorial);
        String numeroStr = new String(packetFactorial.getData(), 0, packetFactorial.getLength());
        int numeroFactorial = Integer.parseInt(numeroStr);
        System.out.println("Factorial recibido del servidor: " + numeroFactorial);

        client1_socket.close();
	}
    
}
