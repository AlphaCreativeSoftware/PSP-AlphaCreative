import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ServidorTCP {
    private static final int PUERTO = 5000;
    private static final int JUGADORES = 4;
    private static int numeroAdivinar;
    private static boolean adivinado = false;

    public static void main(String[] args) {
        numeroAdivinar = getPID();
        System.out.println("Número a adivinar (PID del servidor): " + numeroAdivinar);
        ExecutorService pool = Executors.newFixedThreadPool(JUGADORES);
        
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor esperando a " + JUGADORES + " jugadores...");
            
            for (int i = 0; i < JUGADORES; i++) {
                Socket socket = serverSocket.accept();
                System.out.println("Jugador (" + (i + 1) + ") conectado.");
                pool.execute(new ManejadorJugador(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getPID() {
        return Math.abs((int) ProcessHandle.current().pid());
    }

    private static class ManejadorJugador implements Runnable {
        private Socket socket;
        
        public ManejadorJugador(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {
                
                salida.println("¡Bienvenido! Adivina el número (PID del servidor). Escribe tu intento:");
                
                while (!adivinado) {
                    int intento = Integer.parseInt(entrada.readLine());
                    
                    if (intento == numeroAdivinar) {
                        salida.println("¡Felicidades! Has adivinado el número.");
                        adivinado = true;
                    } else if (intento < numeroAdivinar) {
                        salida.println("El número es mayor.");
                    } else {
                        salida.println("El número es menor.");
                    }
                }
                salida.println("Cerrando el socket...");
                socket.close();
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
