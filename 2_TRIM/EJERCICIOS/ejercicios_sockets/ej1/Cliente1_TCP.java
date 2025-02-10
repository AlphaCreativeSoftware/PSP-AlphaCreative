import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente1_TCP  {
  public static void main(String[] args) throws Exception {
	Scanner in = new Scanner(System.in);

	String Host = InetAddress.getLocalHost().getHostAddress();
	int Puerto = 6666;//puerto remoto	
	
	System.out.println("Cliente(1) Iniciado....");
	Socket cliente = null;
	for (int i=1; i<= 3; i++)
	{
		try
		{
			cliente = new Socket(Host, Puerto);
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
	System.out.println("Cliente(1) Conectado con Servidor....");

	// Creación flujo de salida hacia el servidor
	DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

	System.out.print("Indique un número para enviar al servidor: ");
	int number = extractNumbers(in.next());
	flujoSalida.writeInt(number);
	cliente.close();




	System.out.print("Esperando respuesta del numero procesado por Servidor y Cliente(2)...");
	cliente = new Socket(Host,Puerto);
	// Creación flujo de entrada desde el servidor
	DataInputStream flujoEntrada = new  DataInputStream(cliente.getInputStream());

	int numeroProcesadoRecibido = flujoEntrada.readInt();
	System.out.println("Recibido número procesado por Cliente(2) y Servidor: " + numeroProcesadoRecibido);

	// CERRAR STREAMS Y SOCKETS	
	flujoEntrada.close();	
	flujoSalida.close();

	cliente.close();
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
}