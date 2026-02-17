package vista;

import entidades.Reserva;
import jakarta.persistence.criteria.CriteriaBuilder;
import servicio.GimnasioService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Instancia del servicio
        GimnasioService service = new GimnasioService();

        //Cada metodo recibe el servicio y se llama a los metodos pasando los atributos

        //prueba1(service, 1, 2);
        //prueba2(service);
        prueba3(service,1);

    }




    public static void prueba1(GimnasioService servicio, Integer idCliente, Integer idActividad) {
        if (servicio.insertarReserva(idCliente, idActividad)) {
            System.out.println("Reserva agregada");
        }
        else {
            System.out.println("Error al agregar la reserva");
        }
    }
    public static void prueba2(GimnasioService servicio) {
        List<Reserva> reservasActivas=servicio.resrvasActiva();
        for (Reserva res : reservasActivas) {
            System.out.println(res);
        }
    }
    public static void prueba3(GimnasioService servicio, Integer idCliente) {
        List<Reserva> reservasPorCilente=servicio.listarReservaPorClinete(idCliente);
        for (Reserva res : reservasPorCilente) {
            System.out.println(res);
        }
    }

}
