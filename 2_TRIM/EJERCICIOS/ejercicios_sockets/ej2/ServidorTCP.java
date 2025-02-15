import java.io.*;
import java.net.*;
import java.util.Arrays;
public class ServidorTCP {

    public static void main(String[] args) throws IOException , ClassNotFoundException{
        System.out.println("--- SERVIDOR TCP ---");
        System.out.println("---- PORT: 5000 ----");
        ServerSocket servidor = new ServerSocket(5000);

        int counter = 0;
        while(counter < 10)//Deberia ser un bucle indefinido pero le he puesto 10 repeticiones de limite
        {

            //Conexión con algun cliente
            System.out.println("Esperando conexión de algun cliente...");
            Socket client = servidor.accept();
            System.out.println("Cliente conectado en puerto " + client.getPort() + "...");
            
            //Datos (Entrada)
            System.out.println("Esperando datos...");
            try {
                ObjectInputStream entrada = new ObjectInputStream(client.getInputStream());
                System.out.println("Datos recibidos del Cliente: ");
                int[] arrayRecibido = (int[]) entrada.readObject();
                int suma = 0;
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for(int i = 0; i < arrayRecibido.length; i++) {
                    System.out.println("Elemento " + i + ": " + arrayRecibido[i]);
                    suma += arrayRecibido[i];
                    if(arrayRecibido[i] < min)
                    {
                        min = arrayRecibido[i];
                    }
                    if(arrayRecibido[i] > max)
                    {
                        max = arrayRecibido[i];
                    }
                }
                int[] resultArray = {suma, min, max};
                ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
                output.writeObject(resultArray);
                System.out.println("Datos (Suma, Minimo y Maximo): " + Arrays.toString(resultArray) + " enviados al Cliente...");
            } catch (ClassNotFoundException e) {
                System.out.println("ERROR CRÍTICO, el objeto no se pudo recibir por que la clase que se ha recibido no existe, el servidor no la reconoce...");
            }

            counter += 1;
        }
    }
    
}
