package Ejercicio4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


    public class Cliente {

        public static void main(String[] args) {

            String host = "localhost";
            int puerto = 2100;

            Socket socketCliente = null;
            DataInputStream reciboServidor = null;
            DataOutputStream envioServidor = null;
            Scanner sc = new Scanner(System.in);

            try {
                socketCliente = new Socket(host,puerto);

                reciboServidor = new DataInputStream(socketCliente.getInputStream());
                envioServidor = new DataOutputStream(socketCliente.getOutputStream());

                // Variable local para controlar en qué paso de la autenticación estamos
                // Inicialmente asumimos que el servidor está en estado 1 (pidiendo usuario)
                int estadoActual = 1;

                // Login del usuario en base a las credenciales especificadas en el servidor.
                while (estadoActual != 3) {

                    if (estadoActual == 1) {
                        // El servidor espera un USUARIO
                        System.out.print("Introduce usuario: ");
                        String usuario = sc.nextLine();
                        envioServidor.writeUTF(usuario);

                        // Leemos qué dice el servidor (nos devolverá 1 si fallamos, 2 si acertamos)
                        estadoActual = reciboServidor.readInt();

                        if (estadoActual == 1) {
                            System.out.println("Usuario incorrecto. Vuelve a intentarlo");
                        }

                    } else if (estadoActual == 2) {
                        // El servidor espera una CONTRASEÑA
                        System.out.print("Introduce contraseña: ");
                        String clave = sc.nextLine();
                        envioServidor.writeUTF(clave);

                        // Leemos respuesta (2 si fallamos, 3 si acertamos)
                        estadoActual = reciboServidor.readInt();

                        if (estadoActual == 2) {
                            System.out.println("Contraseña incorrecta. Vuelve a intentarlo");
                        }
                    }

                    // Si el servidor nos devuelve -1 es que se acabaron los intentos
                    if (estadoActual == -1) {
                        System.out.println("Has agotado los intentos. Conexión cerrada.");
                        return;
                    }
                }

                System.out.println("¡Login correcto! Bienvenido.");

                //Inicio de sesion correcto ahora la parte de los comandos
                boolean salir = false;
                while (!salir) {
                    System.out.println("\nEscribe un comando (ls, get, exit):");
                    System.out.print("> ");
                    String comando = sc.nextLine();

                    // Enviamos el comando al servidor
                    envioServidor.writeUTF(comando);

                    switch (comando.toLowerCase()) {
                        case "ls":
                            // El servidor nos enviará la lista de archivos del directorio en el que nos encontramos
                            String respuestaLs = reciboServidor.readUTF();
                            System.out.println("--- Archivos del Servidor ---");
                            System.out.println(respuestaLs);
                            break;

                        case "get":
                            // El protocolo dice que ahora debemos enviar el nombre del archivo
                            System.out.print("Introduce el nombre del archivo: ");
                            String archivo = sc.nextLine();
                            envioServidor.writeUTF(archivo);

                            // Ahora esperamos el contenido del archivo
                            String contenido = reciboServidor.readUTF();
                            System.out.println("--- Contenido de " + archivo + " ---");
                            System.out.println(contenido);
                            System.out.println("--------------------------------");
                            break;

                        case "exit":
                            salir = true;
                            System.out.println("Desconectando...");
                            break;

                        default:
                            // Si nos equivocamos, el servidor envía un mensaje de error
                            String error = reciboServidor.readUTF();
                            System.out.println("Servidor: " + error);
                            break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error en la comunicación: " + e.getMessage());
            }
        }

}
