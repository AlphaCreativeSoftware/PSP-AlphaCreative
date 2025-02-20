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
                DataInputStream entrada = new DataInputStream(client.getInputStream());
                System.out.println("Conectado al Input Stream del Cliente...");
                String recibedIp = entrada.readUTF();
                System.out.println("IP recibida: " + recibedIp);

                //Datos (Salida)
                try
                {
                    DataOutputStream salida = new DataOutputStream(client.getOutputStream());
                    System.out.println("Conectado al Output Stream del Cliente...");
                    String binaryIp = ipToBinary(recibedIp);
                    
                    if(!checkIP(recibedIp))
                    {
                        System.out.println("("+recibedIp + ") no es una IP valida, enviando error al Cliente...");
                        System.out.println("Enviando info de vuelta al cliente");
                        salida.writeUTF("fail");
                        System.out.println("Mensaje error enviado al Cliente...");
                    }
                    else
                    {
                        System.out.println("("+recibedIp + ") SI es una IP valida, enviando conversion binaria al Cliente...");
                        System.out.println("IP binaria convertida: " + binaryIp);
                        salida.writeUTF(binaryIp);
                        System.out.println("IP Binaria enviada al Cliente correctamente...");
                    }
                } catch (Exception e)
                {

                }

                
            } catch (Exception e) {
                System.out.println("ERROR CRÍTICO, a, recibir la Ip enviada por el servidor");
            }

            counter += 1;
        }
    }

    public static String ipToBinary(String ip) {
        String[] octetos = ip.split("\\.");
        StringBuilder ipBinaria = new StringBuilder();
        for (int i = 0; i < octetos.length; i++) {
            int numero = Integer.parseInt(octetos[i]);
            // Convertir el número a binario y rellenarlo a 8 dígitos
            String binario = String.format("%8s", Integer.toBinaryString(numero)).replace(' ', '0');
            ipBinaria.append(binario);
            if (i < octetos.length - 1) {
                ipBinaria.append(".");
            }
        }
        return ipBinaria.toString();
    }
    public static boolean checkIP(String ip)
    {
        String[] splittedIp = ip.split("\\.");
        if(splittedIp.length != 4)
        {
            return false;
        }
        for(int i = 0; i < splittedIp.length; i++)
        {
            for(int j = 0; j < splittedIp[i].length(); j++)
            {
                if(!Character.isDigit(splittedIp[i].charAt(j)))
                {
                    return false;
                }
            }
        }
        for(int i = 0; i < splittedIp.length; i++)
        {
            int ipPart = Integer.parseInt(splittedIp[i]);
            if(ipPart < 0 || ipPart > 255)
            {
                return false;
            }
        }
        return true;
    }
    
}
