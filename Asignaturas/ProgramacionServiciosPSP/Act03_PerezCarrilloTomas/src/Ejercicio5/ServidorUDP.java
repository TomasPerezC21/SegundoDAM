package Ejercicio5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class ServidorUDP {

    public static void main(String[] args) {

        int puertoServidor = 5555;

        //hashmap para guardar usuarios únicos
        HashMap<String, String> usuariosConectados = new HashMap<>();

        try  {
            DatagramSocket socket = new DatagramSocket(puertoServidor);

            System.out.println("Servidor UDP iniciado en el puerto " + puertoServidor);
            System.out.println("Esperando mensajes...");

            while (true) {
                byte[] buffer = new byte[1024];

                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);

                socket.receive(paqueteRecibido);

                // Obtenemos la ip y el puerto para generar ID único de cada cliente
                InetAddress direccionIP = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();

                String idCliente = direccionIP.getHostAddress() + ":" + puertoCliente;

                //mensaje enviado por el cliente
                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength()).trim();

                //El hash map está vacío así que añadimos ese usuario y mostramos el nombre recibido como primer mensaje en clase cliente
                if (!usuariosConectados.containsKey(idCliente)) {
                    usuariosConectados.put(idCliente, mensaje); // Guardamos al usuario
                    System.out.println(mensaje + " se ha conectado al chat");

                } else {

                    // Ya sabemos quién es, recuperamos su nombre (primer mensaje)
                    String nombreUsuario = usuariosConectados.get(idCliente);

                    if (mensaje.equalsIgnoreCase("EXIT")) {

                        System.out.println(nombreUsuario + " ha abandonado el chat");

                        // Enviar respuesta de despedida (Solo se contesta si es EXIT)
                        String despedida = "Gracias por participar en el Chat";
                        byte[] bufferSalida = despedida.getBytes();
                        DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida,bufferSalida.length,
                                direccionIP, puertoCliente);
                        socket.send(paqueteSalida);

                        // Borramos al usuario de la lista de conectados
                        usuariosConectados.remove(idCliente);

                    } else {
                        // si es un mensaje normal pues lo mostrados
                        System.out.println("/" + idCliente + ". Nombre: " + nombreUsuario + " dice: " + mensaje);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}