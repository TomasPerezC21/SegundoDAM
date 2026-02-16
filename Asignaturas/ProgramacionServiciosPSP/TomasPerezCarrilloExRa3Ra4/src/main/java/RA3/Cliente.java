package RA3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Locale;
import java.util.Scanner;

public class Cliente {


    public static void main(String[] args) {

        int puerto = 5555;
        String servidor = "localhost";

        Socket socketCliente = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        Scanner sc = new Scanner(System.in);

        try {

            socketCliente = new Socket(servidor, puerto);
            dis = new DataInputStream(socketCliente.getInputStream());
            dos = new DataOutputStream(socketCliente.getOutputStream());

            String bienvenida = dis.readUTF();

            //mensaje de bienvenida del servidor
            System.out.println(bienvenida);

            //Usuario y Contraseña
            System.out.println("Usuario: ");
            dos.writeUTF(sc.nextLine());
            System.out.println("Contraseña: ");
            dos.writeUTF(sc.nextLine());

            if (dis.readUTF().equalsIgnoreCase("MENU")){

                boolean seguir = true;

                String opcion;

                while (seguir) {
                    System.out.println("BIENVENIDO AL PROGRAMA: \n" +
                            "Qué desea obtener del servidor: \n" +
                            "-. FECHA (Servidor enviará fecha actual)\n" +
                            "-. FECHAHORA (Servidor enviará fecha y hora actual)\n" +
                            "-. MES (Servidor enviará mes actual en texto)\n" +
                            "-. ECO (Servidor replicará tu siguiente mensaje)\n" +
                            "-. SUMAR (El servidor hará una suma por ti)\n" +
                            "-. MAYOR (Servidor te dirá si eres mayor de edad o no)\n" +
                            "-. SALIR (Adiós)");

                    //Para que de igual como se escribe y acepte todas
                     opcion= sc.nextLine().toLowerCase();

                    switch (opcion) {
                        case "fecha":
                            dos.writeUTF("FECHA");
                            dos.flush();
                            System.out.println("SERVIDOR: " + dis.readUTF());
                            break;

                        case "fechahora":
                            dos.writeUTF("FECHAHORA");
                            dos.flush();
                            System.out.println("SERVIDOR: " + dis.readUTF());
                            break;

                        case "mes":
                            dos.writeUTF("MES");
                            dos.flush();
                            System.out.println("SERVIDOR: " + dis.readUTF());
                            break;

                        case "eco":
                            dos.writeUTF("ECO");
                            dos.flush();

                            System.out.println("SERVIDOR: " + dis.readUTF());//escribe mensaje
                            String mensaje = sc.nextLine();

                            dos.writeUTF(mensaje);
                            dos.flush();
                            System.out.println("SERVIDOR: " + dis.readUTF());
                            break;

                        case "sumar":
                            dos.writeUTF("SUMAR");
                            dos.flush();

                            System.out.println("SERVIDOR: " + dis.readUTF());//escribe primer numero
                            double num1 =  Double.parseDouble(sc.nextLine());
                            dos.writeDouble(num1);
                            dos.flush();

                            System.out.println("SERVIDOR: " + dis.readUTF());//escribe segundo numero
                            double num2 =  Double.parseDouble(sc.nextLine());
                            dos.writeDouble(num2);
                            dos.flush();

                            System.out.println("SERVIDOR: " + dis.readDouble());//respuesta con la suma
                            break;

                        case "mayor":
                            dos.writeUTF("MAYOR");
                            dos.flush();

                            System.out.println("SERVIDOR: " + dis.readUTF());//Introduce fecha nacimiento

                            dos.writeUTF(sc.nextLine());
                            dos.flush();

                            System.out.println("SERVIDOR: " + dis.readBoolean());//respuesta
                            break;

                        case "salir":
                            dos.writeUTF("SALIR");
                            dos.flush();

                            System.out.printf("SERVIDOR: " + dis.readUTF());
                            seguir = false;
                            break;

                        default:
                            System.out.println("Comando no válido.");
                    }

                }

            }else{
                System.out.println("Error al introducir credenciales.");
            }


            socketCliente.close();


        }catch (IOException e) {
            throw new RuntimeException(e);
        }



    }


}
