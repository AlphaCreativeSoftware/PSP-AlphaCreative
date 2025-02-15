import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class ClienteTCP {

    public static void main(String[] args) throws IOException {
        System.out.println("--- CLIENTE TCP ---");
        Random random = new Random();
        String host = InetAddress.getLocalHost().getHostAddress();
        int port = 5000; //Server port


        //Inicializacion y conexion con servidor
        System.out.println("Cliente(1) Iniciado....");
        Socket cliente = new Socket(host, port);
        System.out.println("Cliente(1) Conectado con Servidor....");


        //Datos (Salida)
        try {
            ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
            int[] numeros = new int[10];
            for (int i = 0; i < 10; i++) 
            {
                numeros[i] = random.nextInt(100);
                System.out.println("Elemento " + i + ": " + numeros[i]);
            }
            output.writeObject(numeros);
            System.out.println("Datos enviados al Servidor...");

            //Datos (Entrada)
            try {
                System.out.println("Esperando respuesta del Servidor (SUMA, MINIMO y MAXIMO)...");
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                int[] respuesta = (int[]) entrada.readObject();
                System.out.println("Datos recibidos: ");
                System.out.println("SUMA: " + respuesta[0]);
                System.out.println("MINIMO: " + respuesta[1]);
                System.out.println("MAXIMO: " + respuesta[2]);
            } catch (Exception e) {
                System.out.println("Error al recibir datos...");
            }

        } catch (Exception e){
            System.out.println("Error al enviar datos...");
        }
        cliente.close();
    }
    
}
