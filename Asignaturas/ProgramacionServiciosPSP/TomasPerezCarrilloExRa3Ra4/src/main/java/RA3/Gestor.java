package RA3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Gestor implements Runnable {

    private Socket socketCliente;
    private int idCliente;
    private static int NUMCLIENTE = 0;

    private String user = "Tomas";
    private String password = "Perez";

    private static final Object candado = new Object();
    Scanner sc = new Scanner(System.in);

    public Gestor(Socket socketCliente) {
        this.socketCliente = socketCliente;
        synchronized (candado) {
            NUMCLIENTE++;
            this.idCliente = NUMCLIENTE;
        }
    }


    @Override
    public void run() {

        DataInputStream dis = null;
        DataOutputStream dos = null;

        try {

            dis = new DataInputStream(socketCliente.getInputStream());
            dos = new DataOutputStream(socketCliente.getOutputStream());

            String bienvenida = "El usuario " + idCliente + " se ha conectado.\n" +
                    "Introduzca sus credenciales: ";

            dos.writeUTF(bienvenida);
            dos.flush();

            String usuario = dis.readUTF();
            String contrasenia = dis.readUTF();

            if (usuario.equalsIgnoreCase(user) && contrasenia.equalsIgnoreCase(password)) {
                dos.writeUTF("MENU");
                dos.flush();

                String respuestaCliente;

                boolean seguir = true;

                while(seguir){

                    respuestaCliente = dis.readUTF();

                    switch (respuestaCliente) {

                        case "FECHA":
                            dos.writeUTF(obtenerFechaActual());
                            dos.flush();
                            break;

                        case "FECHAHORA":
                            dos.writeUTF(obtenerFechaHoraActual());
                            dos.flush();
                            break;

                        case "MES":
                            dos.writeUTF(obtenerMesActual());
                            dos.flush();
                            break;

                        case "ECO":
                            dos.writeUTF("Escribe un mensaje: ");
                            dos.flush();

                            String usuarioMensaje = dis.readUTF();//mensaje a replicar
                            dos.writeUTF(usuarioMensaje);
                            dos.flush();
                            break;
                        case "SUMAR":
                            dos.writeUTF("Introduce el primer número: ");
                            dos.flush();

                            double num1Usu =  dis.readDouble();

                            dos.writeUTF("Introduce el segundo número: ");
                            dos.flush();

                            double num2Usu =  dis.readDouble();

                            dos.writeDouble(num1Usu + num2Usu);
                            break;

                        case "MAYOR":
                            dos.writeUTF("Introduce tu fecha de nacimiento (FORMATO: dd/MM/yyyy): ");
                            dos.flush();

                            String fechaNac  = dis.readUTF();

                            dos.writeBoolean(esMayor(fechaNac));
                            break;

                        case "SALIR":
                            dos.writeUTF("Hasta luego.");
                            dos.flush();
                            seguir = false;
                            break;
                    }
                }

            } else {
                dos.writeUTF("ERROR");
                dos.flush();
            }
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private static String obtenerFechaActual () {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            return formatter.format(LocalDateTime.now());
    }

    private static String obtenerFechaHoraActual () {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss dd/MM/yyyy");

        return formatter.format(LocalDateTime.now());
    }

    private static String obtenerMesActual(){

        return LocalDate.now().getMonth().toString();
    }

    private static boolean esMayor(String fechaUsu){

        //Primero formateamos la fecha
        String[] partesFecha =  fechaUsu.split("/");

        int anio = Integer.valueOf(partesFecha[2]);

        LocalDate ahora = LocalDate.now();

        return ahora.getYear() - anio >= 18;

    }

}
