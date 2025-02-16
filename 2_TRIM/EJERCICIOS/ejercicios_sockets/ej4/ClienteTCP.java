import java.io.*;
import java.net.*;

public class ClienteTCP {
    public static void main(String[] args) {
        try{
            String serverHost = InetAddress.getLocalHost().getHostAddress();
            int serverPort = 5000;
            Socket socket = new Socket(serverHost, serverPort);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            System.out.println(entrada.readLine());

           while (true)
            {
               System.out.print("Introduce un número: ");
               String intento = teclado.readLine();
               salida.println(intento);

               String respuesta = entrada.readLine();
               System.out.println(respuesta);

               if (respuesta.contains("¡Felicidades!")) {
                   break;
               }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}