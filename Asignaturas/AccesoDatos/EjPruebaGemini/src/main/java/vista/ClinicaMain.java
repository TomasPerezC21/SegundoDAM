package vista;

import entidades.Cita;
import entidades.Perro;
import servicio.ClinicaService;

import java.util.List;

public class ClinicaMain {

    public static void main(String[] args) {

        ClinicaService servicio = new ClinicaService();


        //prueba1(servicio, 1);
        //prueba2(servicio,"Pastor Alemán");
        prueba3(servicio,1);

    }

    private static void prueba3(ClinicaService servicio, int i) {

    Cita c = servicio.obtenerCitaConMascota(i);

        System.out.println(c.toString());

    }

    private static void prueba2(ClinicaService servicio, String raza) {

        List<Perro> lista = servicio.listarSoloPerrosDeRaza(raza);

        for (Perro p : lista) {
            System.out.println(p.getNombre());
        }

    }

    public static void prueba1(ClinicaService service, int id){

        double resultado = service.totalInvertidoPorDueño(id);

        System.out.println("resultado: "+resultado);

    }



}
