package Ejercicio5;

import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class ClienteUDP {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int puertoServidor = 5555;
        String hostServidor = "localhost";

        try{
            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getByName(hostServidor);

            //Primero le pasamos el nombre al servidor
            System.out.print("Introduce tu nombre para entrar al chat: ");
            String nombre = sc.nextLine();
            byte[] bufferNombre = nombre.getBytes();
            DatagramPacket paqueteNombre = new DatagramPacket(bufferNombre, bufferNombre.length,
                    direccionServidor,puertoServidor);

            socket.send(paqueteNombre);
            System.out.println("Bienvenido. Escribe mensajes('EXIT' para salir): ");

            boolean salir = false;

            while(!salir){
                System.out.println("Escribe: ");
                String mensaje = sc.nextLine();

                byte[] bufferMensaje = mensaje.getBytes();
                DatagramPacket paqueteMensaje = new DatagramPacket(bufferMensaje, bufferMensaje.length,
                        direccionServidor,puertoServidor);

                socket.send(paqueteMensaje);

                if(mensaje.equalsIgnoreCase("EXIT")) {

                    //esperamos 10 segundos a recibir respuesta o cerrar conexion "forzosamente"
                    socket.setSoTimeout(10000);

                    try {
                        byte[] bufferRespuesta = new byte[1024];
                        DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);

                        // Nos quedamos bloqueados aquí esperando respuesta
                        socket.receive(paqueteRespuesta);

                        String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
                        System.out.println("Servidor dice: " + respuesta);

                    } catch (InterruptedIOException e) {
                        // Esto se ejecuta si pasan 10 segundos y nadie responde
                        System.out.println("No se ha recibido respuesta del servidor (Timeout 10s). Cerrando conexión.");
                    }
                    salir = true;
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }






    }


}
