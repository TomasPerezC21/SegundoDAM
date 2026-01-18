package Ejercicio3;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class GestorPalabras implements Runnable{

    private Socket socketCliente;
    private int idCliente;
    private ArrayList<String> copiaPalabrasFichero;

    private final Object candado = new Object();
    private static int NUMCLIENTE = 0;

    private static int NUMEROPALABRAS = 0;

    public GestorPalabras(Socket socketCliente, ArrayList<String> palabrasFicheroServidor) {
        this.socketCliente = socketCliente;
        this.copiaPalabrasFichero = new ArrayList<>(palabrasFicheroServidor);
        NUMEROPALABRAS = palabrasFicheroServidor.size();

        synchronized (candado) {
            NUMCLIENTE++;
            this.idCliente = NUMCLIENTE;
        }
    }

    @Override
    public void run() {

        DataInputStream entradaDelCliente = null;
        DataOutputStream salidaAlCliente = null;

        ArrayList<String> palabrasEsp = obtenerPalabrasEsp(copiaPalabrasFichero);
        ArrayList<String> palabrasIngles = obtenerPalabrasIngles(copiaPalabrasFichero);

        int contadorAciertos = 0;
        ArrayList<String> resumenFinal = new ArrayList<>();

        try {
            entradaDelCliente = new DataInputStream(socketCliente.getInputStream());
            salidaAlCliente = new DataOutputStream(socketCliente.getOutputStream());

            while(!palabrasEsp.isEmpty()){

                String palabraActualEsp = palabrasEsp.removeFirst();
                String solucionesIngles = palabrasIngles.removeFirst();
                String lineaActual = copiaPalabrasFichero.removeFirst();

                salidaAlCliente.writeUTF(palabraActualEsp);
                System.out.println("Le he enviado al jugador: "+ idCliente +" la palabra: "+palabraActualEsp);

                String respuestaCliente = entradaDelCliente.readUTF();
                System.out.println("El jugador "+ idCliente +" responde: "+respuestaCliente);

                //Validación de respuesta de usuario para resumen final del juego
                if(comprobarRespuestaCliente(solucionesIngles,respuestaCliente)){
                    contadorAciertos++;
                    resumenFinal.add(lineaActual);
                }
            }

            //Para avisar al cliente de que el juego se ha acabado
            String fin = "fin";
            salidaAlCliente.writeUTF(fin);

            System.out.println("RESUMEN FINAL "+ " Jugador "+ idCliente + ". Total de aciertos: " + contadorAciertos + " / " + NUMEROPALABRAS);
            for (String s : resumenFinal) {
                System.out.println("Acertada: "+ s);
            }

            entradaDelCliente.close();
            salidaAlCliente.close();
            socketCliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<String> obtenerPalabrasEsp(ArrayList<String> palabrasFichero) {

        ArrayList<String> palabrasEsp = new ArrayList<>();

        for (String linea : palabrasFichero) {
            String[] palabras = linea.split(" ");
            palabrasEsp.add(palabras[0]);
        }
        return palabrasEsp;
    }

    private ArrayList<String> obtenerPalabrasIngles(ArrayList<String> palabrasFichero) {

        ArrayList<String> palabrasIngles = new ArrayList<>();

        for (String linea : palabrasFichero) {
            String[] palabras = linea.split(" ");
            String palabrasInglesConcatenadas = palabras[1] + " " + palabras[2];
            palabrasIngles.add(palabrasInglesConcatenadas);

        }
        return palabrasIngles;
    }

    private boolean comprobarRespuestaCliente(String palabrasIngles, String respuestaCliente) {
        String[] palabras = palabrasIngles.split(" ");
        return respuestaCliente.equalsIgnoreCase(palabras[0]) || respuestaCliente.equalsIgnoreCase(palabras[1]);
    }
}
