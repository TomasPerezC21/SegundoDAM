package EjerciciosGPT;

import Binario.SerializarDeserializar;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class A07SerializarPelicula {
    private static final String RUTA = "src/Archivos/peliculas.dat";
    static ArrayList<Pelicula> listaPeliculas = new ArrayList<Pelicula>();

    public static void mostrarPeliculas() {
        for (Pelicula p : listaPeliculas) {
            System.out.println("Película: "+ p.getTitulo() + " Director: " + p.getDirector() + " Duración" + p.getDuracion());
        }
    }

    static class Pelicula implements Serializable {
        private static final long serialVersionUID = 1L;

        private String titulo;
        private String director;
        private int anio;
        private int duracion;
        private boolean vista;

        public Pelicula(String titulo, String director, int anio, int duracion, boolean vista) {
            this.titulo = titulo;
            this.director = director;
            this.anio = anio;
            this.duracion = duracion;
            this.vista = vista;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public int getAnio() {
            return anio;
        }

        public void setAnio(int anio) {
            this.anio = anio;
        }

        public int getDuracion() {
            return duracion;
        }

        public void setDuracion(int duracion) {
            this.duracion = duracion;
        }

        public boolean isVista() {
            return vista;
        }

        public void setVista(boolean vista) {
            this.vista = vista;
        }

    }


    public static void addPelicula(Pelicula pelicula) {
        listaPeliculas.add(pelicula);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            menuUsuario();
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> altaPelicula(sc);
                case 2 -> deserializar(RUTA);
                case 3 -> modificarPelicula(sc);
                case 4 -> eliminarPelicula(sc);
                case 5 -> {
                    System.out.println("Hasta luego.");
                    return;
                }
                default -> System.out.println("Opción inválida. Prueba de nuevo.");
            }
            System.out.println();
        }

    }

    private static void eliminarPelicula(Scanner sc) {

        boolean serializamos = false;
        deserializar(RUTA);
        System.out.println("Cuál quieres eliminar (Introduce el título): ");
        String titulo = sc.nextLine().toLowerCase();
        for (int i = 0; i < listaPeliculas.size(); i++) {
            if (listaPeliculas.get(i).getTitulo().toLowerCase().equals(titulo)) {
                listaPeliculas.remove(i);
                System.out.println("Pelicula eliminada correctamente.");
                serializamos = true;
                break;
            }
        }

        if (serializamos) {
            serializar(listaPeliculas);
        }

    }

    private static void modificarPelicula(Scanner sc) {

        deserializar(RUTA);

        System.out.println("Escribe el titulo de la que quieras modificar: ");
        String titulo = sc.nextLine().toLowerCase();

        for (int i = 0; i < listaPeliculas.size(); i++) {
            if (titulo.equals(listaPeliculas.get(i).getTitulo().toLowerCase())) {
                System.out.println("La has visto?");
                String visto = String.valueOf(sc.nextLine().charAt(0)).toUpperCase();
                Boolean visto2 = visto.equals("S");
                listaPeliculas.get(i).setVista(visto2);
                System.out.println("Pelicula modificada correctamente.");
                break;
            }
        }
        serializar(listaPeliculas);

    }

    private static void menuUsuario(){
        System.out.println("1.Alta de pelicula. ");
        System.out.println("2.Ver peliculas. ");
        System.out.println("3.Modificar pelicula. ");
        System.out.println("4.Eliminar pelicula. ");
        System.out.println("5.Salir.");
    }



    private static void altaPelicula(Scanner sc) {
        System.out.print("Título: ");
        String titulo = sc.nextLine().trim();

        System.out.print("Director: ");
        String director = sc.nextLine().trim();

        System.out.println("Anio: ");
        int anio = sc.nextInt();
        sc.nextLine();
        System.out.print("Duracion: ");
        int duracion = sc.nextInt();
        sc.nextLine();
        System.out.println("La has visto?(S/N)");
        String vista1 = String.valueOf(sc.nextLine().charAt(0)).toUpperCase();
        boolean vista = vista1.equals("S");

        Pelicula p = new Pelicula(titulo, director, anio, duracion, vista);
        addPelicula(p);
        System.out.println("Pelicula añadida correctamente.");
        serializar(listaPeliculas);
    }

    private static void serializar(ArrayList<Pelicula> listaProductos) {

        FileOutputStream flujoSalida = null;
        ObjectOutputStream serializador = null;

        try {
            flujoSalida = new FileOutputStream(RUTA);
            serializador = new ObjectOutputStream(flujoSalida);
            serializador.writeObject(listaProductos);
            serializador.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void deserializar(String archivo) {
        try {
            ObjectInputStream deserializar = new ObjectInputStream(new FileInputStream(archivo));
            ArrayList<Pelicula> productosLeerArchivo;
            productosLeerArchivo = (ArrayList<Pelicula>) deserializar.readObject();
            System.out.println("Productos leidos desde archivo: ");
            for (Pelicula p : productosLeerArchivo) {
                System.out.println("Película: "+ p.getTitulo() + " || Director: " + p.getDirector() + " || Duración: " + p.getDuracion() + " || Anio: " + p.getAnio() + " || Vista: " + p.isVista());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
