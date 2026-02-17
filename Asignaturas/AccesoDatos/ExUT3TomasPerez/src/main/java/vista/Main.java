package vista;

import entidades.Prestamo;
import servicio.BibliotecaService;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        BibliotecaService servicio =  new BibliotecaService();

        //listadoPrestamosActivos(servicio);

        //listadoPrestamosSocio(servicio, 7);

//        if(insertarPrestamo(servicio,10, 9 )){
//            System.out.println("Prestamo insertado correctamente.");
//        }else{
//            System.out.println("Error al insertar prestamo.");
//        }

//       if (cerrarConexion(servicio)){
//            System.out.println("Conexión cerrada.");
//        }else{
//            System.out.println("Error al cerrar la conexión.");
//        }

    }

    private static boolean insertarPrestamo(BibliotecaService servicio, int idPubli, int idSocio) {
        return servicio.insertarPrestamo(idPubli, idSocio);
    }

    private static void listadoPrestamosSocio(BibliotecaService servicio, int idSocio) {

        System.out.println("Listando prestamos del socio: " + idSocio);

        List<Prestamo> prestamos = servicio.obtenerPrestamosSocio(idSocio);

        for (Prestamo prestamo : prestamos) {
            System.out.println(prestamo);
        }

    }

    public static void listadoPrestamosActivos(BibliotecaService servicio){

        System.out.println("Lista de prestamos activos: ");

       List<Prestamo> prestamos = servicio.obtenerPrestamos();

       for (Prestamo prestamo : prestamos) {
           System.out.println(prestamo);
       }

    }

    public static boolean cerrarConexion(BibliotecaService servicio){
        return servicio.cerrarConexion();
    }

}
