package RA3.EjercicioSubastas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5555;

        try (Socket socket = new Socket(host, puerto);
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             Scanner sc = new Scanner(System.in)) {

            System.out.println(dis.readUTF()); // Mensaje bienvenida
            System.out.println(dis.readUTF()); //Solicitud de nombre

            String nombre = sc.nextLine();
            dos.writeUTF(nombre);
            dos.flush();

            boolean seguir = true;

            while (seguir) {
                String estado = dis.readUTF();
                System.out.println("\n" + estado);

                String invitacion = dis.readUTF();
                System.out.println(invitacion);

                int miPuja = Integer.parseInt(sc.nextLine());
                dos.writeInt(miPuja);
                dos.flush();

                String respuesta = dis.readUTF();
                System.out.println("SERVIDOR -> " + respuesta);

                if (miPuja <= 0) {
                    seguir = false;
                    System.out.println("Saliendo de la subasta...");
                }
            }

        } catch (IOException e) {
            System.err.println("Error de conexión: El servidor no está disponible.");
        } catch (NumberFormatException e) {
            System.err.println("Error: Debes introducir un número entero para la puja.");
        }
    }
}