import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente2_TCP  {
  public static void main(String[] args) throws Exception {
	Scanner in = new Scanner(System.in);

	String Host = InetAddress.getLocalHost().getHostAddress();
	int Puerto = 6667;//puerto remoto	
	
	System.out.println("Cliente(2) Iniciado....");
	Socket Cliente = null;
	for (int i=1; i<= 3; i++)
	{
		try
		{
			Cliente = new Socket(Host, Puerto);
			break;
		}
		catch (IOException e)
		{
			System.out.println("Intentando reconectar con el servidor en el puerto " + Puerto);
			if(i == 3)
			{
				System.out.println("No se pudo conectar, saliendo de Cliente(1)...");
				return;
			}
		}
	}
	System.out.println("Cliente(2) Conectado con Servidor....");
	// Creación flujo de entrada desde el servidor
    System.out.println("Esperando a recibir el numero enviado por Cliente(1) al servidor....");
	DataInputStream flujoEntrada = new  DataInputStream(Cliente.getInputStream());
    int numeroRecibido = flujoEntrada.readInt();
	System.out.println("Número Recibido del Servidor: " + numeroRecibido);

    int factorialProcesado = factorial(numeroRecibido);
    System.out.println("Factorial del numero recibido: " + factorialProcesado);

    System.out.println("Enviando el factorial al Servidor...");
    DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());
    flujoSalida.writeInt(factorialProcesado);
    System.out.println("Factorial enviado correctamente :), cerrando Cliente(2)...");
	// CERRAR STREAMS Y SOCKETS	
	flujoEntrada.close();
	flujoSalida.close();
	Cliente.close();	
  }

  public static int extractNumbers(String cadena) {
	int resultado = 0;
	int signo = 1; // 1 para positivo, -1 para negativo

	for (int i = 0; i < cadena.length(); i++) {
		char caracter = cadena.charAt(i);

		if (i == 0 && caracter == '-') {
			signo = -1;
		} else if (Character.isDigit(caracter)) {
			resultado = resultado * 10 + Character.getNumericValue(caracter);
		}
	}

	return resultado * signo;
}
public static int factorial(int n) {
    if (n <= 1) {
        return 1;
    }
    return n * factorial(n - 1);
}
}