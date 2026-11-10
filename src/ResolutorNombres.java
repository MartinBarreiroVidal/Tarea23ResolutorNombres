import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ResolutorNombres {

    public static void main(String[] args) {
        try {
            InetAddress miEquipo = InetAddress.getLocalHost();
            System.out.println("--- DATOS DEL EQUIPO LOCAL ---");
            System.out.println("Nombre: " + miEquipo.getHostName());
            System.out.println("IP: " + miEquipo.getHostAddress());
            System.out.println("");

        } catch (UnknownHostException e) {
            System.out.println("No se pudo obtener la información del equipo local." + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Introduce un nombre de dominio (o 'salir' para terminar): ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("salir")){
                System.out.println("Programa finalizado.");
                break;
            }

            if (entrada.isEmpty()) {
                continue;
            }

            try {
                InetAddress[] direcciones = InetAddress.getAllByName(entrada);
                System.out.println("Resultados para " + entrada + ":");

                for (InetAddress ip : direcciones) {

                    if (ip instanceof Inet4Address) { //distinguimos que es IPv4
                        System.out.println(" [IPv4] " + ip.getHostAddress());
                    } else if (ip instanceof Inet6Address) {
                        System.out.println(" [IPv6] " + ip.getHostAddress()); //distinguimos que es IPv6
                    } else {
                        System.out.println("  [IP]  " + ip.getHostAddress());
                    }
                }

            } catch (UnknownHostException e) {
                System.out.println("Error: No se pudo resolver el dominio " + entrada + ". " + e.getMessage());;
            }

            System.out.println();
        }

        scanner.close();
    }
}