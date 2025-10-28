package Ejercicio2;

import org.w3c.dom.NodeList;

import java.io.*;
import java.util.ArrayList;

public class Ejercicio2 {

    public static void main(String[] args) {
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();

        Empleado e1 = new Empleado(1, "Tomás", 1500.0);
        Empleado e2 = new Empleado(2, "Álvaro", 500.0);
        Empleado e3 = new Empleado(3, "Borja", 1800.0);

        listaEmpleados.add(e1);
        listaEmpleados.add(e2);
        listaEmpleados.add(e3);

        String rutaBinarioSalida = "src/Archivos/Ejercicio2";

        try {
            serializar(listaEmpleados, rutaBinarioSalida);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Empleado> resultado;

        try {
           resultado = deserializar(rutaBinarioSalida);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Empleado r : resultado) {
            System.out.println(r);
        }

    }

    public static void serializar(ArrayList<Empleado> listaEmpleados, String rutaBinarioSalida) throws IOException {
        File fichero = new File(rutaBinarioSalida);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));
        for (Empleado empleado : listaEmpleados) {
            oos.writeObject(empleado);
        }
        oos.close();

        System.out.println("Empleados serializados.");
    }

    public static ArrayList<Empleado> deserializar(String rutaBinarioSalida) throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaBinarioSalida));

        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        Empleado empleado =  (Empleado) ois.readObject();

        while (empleado != null) {
            System.out.println(empleado);
            listaEmpleados.add(empleado);
            empleado = (Empleado) ois.readObject();
        }
        ois.close();

        return listaEmpleados;
    }



}
