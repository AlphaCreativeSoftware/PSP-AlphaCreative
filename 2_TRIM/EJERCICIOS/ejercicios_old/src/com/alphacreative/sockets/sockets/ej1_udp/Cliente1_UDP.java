import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente1_UDP {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        InetAddress direccionServidor = InetAddress.getLocalHost();
        DatagramSocket socket = new DatagramSocket();

        System.out.print("Introduce un número para enviar al servidor: ");
        String numero = in.next();
        byte[] buffer = numero.getBytes();
        DatagramPacket paqueteSalida = new DatagramPacket(buffer, buffer.length, direccionServidor, 6666);
        socket.send(paqueteSalida);
        System.out.println("Número enviado al servidor.");

        byte[] bufferEntrada = new byte[1024];
        DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
        socket.receive(paqueteEntrada);
        String numeroProcesado = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength()).trim();
        System.out.println("Número procesado recibido del servidor: " + numeroProcesado);
        socket.close();
    }
}