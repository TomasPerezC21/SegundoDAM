package FichaJuana;

import Binario.SerializarDeserializar;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class A07Juana {

    private static ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();

    static class Alumno implements Serializable{
        private static final long serialVersionUID = 1L;
        private String nre;
        private String nombre;
        private String apellidos;
        private int telefono;
        private LocalDate fechaNacimiento;
        private Boolean beca;

        public Alumno(String nre, String nombre, String apellidos, int telefono, LocalDate fechaNacimiento, Boolean beca) {
            this.nre = nre;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.telefono = telefono;
            this.fechaNacimiento = fechaNacimiento;
            this.beca = beca;
        }

        public String getNre() {
            return nre;
        }

        public void setNre(String nre) {
            this.nre = nre;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellidos() {
            return apellidos;
        }

        public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
        }

        public int getTelefono() {
            return telefono;
        }

        public void setTelefono(int telefono) {
            this.telefono = telefono;
        }

        public LocalDate getFechaNacimiento() {
            return fechaNacimiento;
        }

        public void setFechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }

        public Boolean getBeca() {
            return beca;
        }
        public void setBeca(Boolean beca) {
            this.beca = beca;
        }
    }

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
        System.out.println("Cuantos alumnos quieres: ");
        int n  = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Ingresa el nre del alumno: ");
            String nre = sc.nextLine();
            System.out.println("Ingresa el nombre del alumno: ");
            String nombre = sc.nextLine();
            System.out.println("Ingresa los apellidos: ");
            String apellidos = sc.nextLine();
            System.out.println("Ingresa el telefono: ");
            int telefono = sc.nextInt();
            sc.nextLine();
            System.out.println("Ingresa el año de nacimiento: ");
            int anio = sc.nextInt();
            sc.nextLine();
            System.out.println("Ingresa mes de nacimiento: ");
            int mes = sc.nextInt();
            sc.nextLine();
            System.out.println("Ingresa dia de nacimiento: ");
            int dia = sc.nextInt();
            sc.nextLine();
            System.out.println("Tiene beca: (S/N)");
            String beca = String.valueOf(sc.nextLine().charAt(0)).toUpperCase();
            Boolean beca2 = beca.equals("S");
            Alumno alumno = new Alumno(nre, nombre, apellidos, telefono, LocalDate.of(anio,mes,dia), beca2);
            listaAlumnos.add(alumno);
        }

        serializar(listaAlumnos);
        deserializar("src/Archivos/Alumnos.dat");
    }

    public static void serializar(ArrayList<Alumno> listaAlumnos) {

        FileOutputStream flujoSalida = null;
        ObjectOutputStream serializador = null;

        try {
            flujoSalida = new FileOutputStream("src/Archivos/Alumnos.dat");
            serializador = new ObjectOutputStream(flujoSalida);
            serializador.writeObject(listaAlumnos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deserializar(String archivo) {
        try {
            ObjectInputStream deserializar = new ObjectInputStream(new FileInputStream(archivo));
            ArrayList<Alumno> AlumnosLeerArchivo;
            AlumnosLeerArchivo = (ArrayList<Alumno>) deserializar.readObject();
            System.out.println("Productos leidos desde archivo: ");
            for (Alumno a : AlumnosLeerArchivo) {
                System.out.println("Nombre: " + a.getNombre() + " || Apellidos: " + a.getApellidos() + " || Teléfono: " + a.getTelefono() + " || Fecha de nacimiento: " + a.getFechaNacimiento() + " || Tiene beca: " + a.getBeca());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
