import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class ClienteTCP {

    public static void main(String[] args) throws IOException {
        System.out.println("--- CLIENTE TCP ---");
        Random random = new Random();
        String host = InetAddress.getLocalHost().getHostAddress();
        int port = 5000; //Server port


        //Inicializacion y conexion con servidor
        System.out.println("Cliente(1) Iniciado....");
        Socket cliente = new Socket(host, port);
        System.out.println("Cliente(1) Conectado con Servidor("+port+")....");

        //Datos (Salida)
        try {
            DataOutputStream salidaCliente = new DataOutputStream(cliente.getOutputStream());
            String ipToSend = writeIp("Introduzca la IP a enviar: ");
            salidaCliente.writeUTF(ipToSend);
            System.out.println("Datos enviados al Servidor...");
            //Datos (Entrada)
            try {
                System.out.println("Esperando respuesta del Servidor (IP BINARY)...");
                DataInputStream entrada = new DataInputStream(cliente.getInputStream());
                String response = entrada.readUTF();
                if(response.equals("fail"))
                {
                    System.out.println("IP "+ipToSend+ " invalida");
                }
                else
                {
                    System.out.println("IP binaria: " + response);
                }
            } catch (Exception e) {
                System.out.println("Error al recibir datos...");
            }

        }
        catch (Exception e){
            System.out.println("Error al enviar datos...");
        }
        cliente.close();
    }

    public static String writeIp(String message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        String ip = scanner.nextLine();
        String[] splittedIp = ip.split("\\.");
        if(splittedIp.length != 4)
        {
            System.out.println("IP invalida");
            return ip;
        }
        for(int i = 0; i < splittedIp.length; i++)
        {
            for(int j = 0; j < splittedIp[i].length(); j++)
            {
                if(!Character.isDigit(splittedIp[i].charAt(j)))
                {
                    System.out.println("IP invalida");
                    return ip;
                }
            }
        }
        for(int i = 0; i < splittedIp.length; i++)
        {
            int ipPart = Integer.parseInt(splittedIp[i]);
            if(ipPart < 0 || ipPart > 255)
            {
                System.out.println("IP invalida");
                return ip;
            }
        }
        return ip;
    }
    
}
