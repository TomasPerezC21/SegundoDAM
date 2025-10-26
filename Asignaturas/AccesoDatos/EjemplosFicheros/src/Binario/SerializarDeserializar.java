package Binario;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.RandomGenerator;

public class SerializarDeserializar {

    static class Producto implements Serializable {
        private static final long serialVersionUID = 1L;
        String nombre;
        Float precio;

        public Producto(String nombre, Float cantidad) {
            this.nombre = nombre;
            this.precio = cantidad;
        }
        public String getNombre() {
            return nombre;
        }
        public Float getPrecio() {
            return precio;
        }
    }

    public static void main(String[] args) {
        Random aleatorio = new Random();

        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        String alfabeto = "abcdefghijklmnñopqrstuvwxyz";

        Producto producto = null;
        for (int i = 0; i < 4; i++) {
            StringBuilder nombre = new StringBuilder();
            float precio = aleatorio.nextFloat(1, 50);
            int indice;
            int longitudNombre = aleatorio.nextInt(4, 8);
            for (int j = 0; j < longitudNombre; j++) {
                 indice = aleatorio.nextInt(alfabeto.length());
                 nombre.append(alfabeto.charAt(indice));
            }

            producto = new Producto(nombre.toString(), precio);
            listaProductos.add(producto);
        }

        serializar(listaProductos);
        deserializar("src/Archivos/archivo.dat");
    }

    public static void serializar(ArrayList<Producto> listaProductos) {

        FileOutputStream flujoSalida = null;
        ObjectOutputStream serializador = null;

        try {
            flujoSalida = new FileOutputStream("src/Archivos/archivo.dat");
            serializador = new ObjectOutputStream(flujoSalida);
            serializador.writeObject(listaProductos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (serializador != null) {
                try {
                    serializador.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void deserializar(String archivo) {
        try {
            ObjectInputStream deserializar = new ObjectInputStream(new FileInputStream(archivo));
            ArrayList<Producto> productosLeerArchivo;
            productosLeerArchivo = (ArrayList<Producto>) deserializar.readObject();
            System.out.println("Productos leidos desde archivo: ");
            for (Producto producto : productosLeerArchivo) {
                System.out.println(producto.getNombre() + " " + producto.getPrecio());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
