package Ejercicio4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static final String USUARIO = "Tomas";
    private static final String CLAVE = "1234";
    private static int numIntentos = 5;
    private static String nombreArchivoSolicitado = "";

    public static void main(String[] args) {

        int puertoServidor = 2100;

        int estadoServidor = 0;

        ServerSocket socketServidor = null;
        Socket socketCliente = null;
        DataInputStream reciboCliente = null;
        DataOutputStream envioCliente = null;


        try{
            socketServidor = new ServerSocket(puertoServidor);
            socketCliente = socketServidor.accept();
            reciboCliente = new DataInputStream(socketCliente.getInputStream());
            envioCliente = new DataOutputStream(socketCliente.getOutputStream());

            System.out.println("Servidor iniciado. Esperando credenciales de inicio de sesión...");

            while(estadoServidor != -1){

                switch (estadoServidor){
                    case 0:
                        estadoServidor = 1;
                    break;

                    case 1:
                        String usuarioCliente = reciboCliente.readUTF(); //leemos usuario

                        if(comprobarUsuario(usuarioCliente)){
                            numIntentos = 5; //Si el usuario es correcto se reinician los intentos a 5
                            estadoServidor = 2; //Actualizamos el estado para avanzar a la contraseña
                            envioCliente.writeInt(estadoServidor); //Pasamos a solicitar contraseña


                        }else {
                            numIntentos--; //Si el usuario no es correcto se van restando intentos.
                            envioCliente.writeInt(estadoServidor); //Seguimos en estado1 pidiendo usuario hasta acabar intentos
                            }
                    break;

                    case 2:
                        String claveCliente = reciboCliente.readUTF(); //leemos clave
                        if(comprobarClave(claveCliente)){
                            numIntentos = 5;
                            estadoServidor = 3;
                            envioCliente.writeInt(estadoServidor);//El estado avanza y pasamos a solicitar comando

                        }else  {
                            numIntentos--;
                            envioCliente.writeInt(estadoServidor); //Notificamos que siga introduciendo contraseña
                        }
                    break;

                    case 3:

                        System.out.println("Esperando comando del cliente...");
                        String comandoCliente = reciboCliente.readUTF().toLowerCase();

                        switch(comandoCliente) {
                            case "ls":
                                // El diagrama dice que si es "ls", vamos al estado 4
                                estadoServidor = 4;
                                break;

                            case "get":
                                // El diagrama dice que si es "get", vamos al estado 5
                                estadoServidor = 5;
                                break;

                            case "exit":
                                // El diagrama dice que si es "exit", vamos a Fin (-1)
                                estadoServidor = -1;
                                break;

                            default:
                                //Si escribe algo raro, le avisamos y nos quedamos en el estado 3
                                envioCliente.writeUTF("Comando desconocido. Usa: ls, get, exit");
                                break;
                        }
                    break;

                    case 4:
                        System.out.println("Listando directorio...");
                        File carpeta = new File("."); // directorio actual
                        String[] listaArchivos = carpeta.list();

                        StringBuilder mensajeAEnviar = new StringBuilder();
                        if (listaArchivos != null) {
                            for (String fichero : listaArchivos) {
                                mensajeAEnviar.append(fichero).append("\n"); // Añadimos salto de línea
                            }
                        }

                        envioCliente.writeUTF(mensajeAEnviar.toString()); // Enviamos el listado al cliente

                        estadoServidor = 3;
                    break;

                    case 5:
                        nombreArchivoSolicitado = reciboCliente.readUTF();
                        estadoServidor = 6;

                    break;

                    case 6:
                        File archivo = new File(nombreArchivoSolicitado);

                        if(archivo.exists()){
                            BufferedReader br = new BufferedReader(new FileReader(archivo));
                            StringBuilder mensaje = new StringBuilder();
                            String linea;
                            while ((linea = br.readLine()) != null) {
                                mensaje.append(linea).append("\n");
                            }

                            envioCliente.writeUTF(mensaje.toString()); //Envio de contenido del archivo
                            br.close();
                        }else{
                            envioCliente.writeUTF("Error. Archivo no encontrado.");
                        }

                        estadoServidor = 3;//volvemos al 3
                        break;
                }

                //Si los intentos se acaban se cierra la sesión forzando el estadoServidor a -1
                if(numIntentos == 0){
                    System.out.println("Sesión errónea. Intentos agotados.");
                    envioCliente.writeInt(-1);
                    estadoServidor = -1;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static boolean comprobarUsuario(String usuario){
        return usuario.equals(USUARIO);
    }

    private static boolean comprobarClave(String clave){
        return clave.equals(CLAVE);
    }


}
