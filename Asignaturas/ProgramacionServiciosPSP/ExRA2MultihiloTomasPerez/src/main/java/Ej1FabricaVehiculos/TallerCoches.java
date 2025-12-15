package Ej1FabricaVehiculos;

import java.util.ArrayList;
import java.util.Random;

//clase que crea coches para el concesionario
public class TallerCoches implements Runnable {


    private Concesionario concesionario;

    private static ArrayList<String> modelosCoche;
    private static ArrayList<String> coloresCoche;

    public TallerCoches(Concesionario concesionario) {
        this.concesionario = concesionario;
        modelosCoche = getModelosCoche();
        coloresCoche = getColoresCoche();
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            Coche c = crearCoche(i+1);
            concesionario.addCoches(c);
            try {
                System.out.println("Coche con id: " + (i+1) + " listo");
                Thread.sleep(1300L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static Coche crearCoche(int id) {

        Coche c = new Coche(id,obtenerModelo(modelosCoche),obtenerColor(coloresCoche));
        return c;
    }

    public static ArrayList<String> getModelosCoche() {

        ArrayList<String> modelosCoche = new ArrayList<String>();

        modelosCoche.add("Seat Ibiza");
        modelosCoche.add("VW Scirocco");
        modelosCoche.add("Audi TT");
        modelosCoche.add("Toyota Corolla");
        return modelosCoche;
    }

    public static ArrayList getColoresCoche(){

        ArrayList<String> coloresCoche = new ArrayList<String>();

        coloresCoche.add("Rojo");
        coloresCoche.add("Azul");
        coloresCoche.add("Verde");
        coloresCoche.add("Violeta");
        coloresCoche.add("Blanco");
        coloresCoche.add("Negro");
        return coloresCoche;
    }

    private static String obtenerColor(ArrayList<String> coloresCoche) {
        Random random = new Random();
        return coloresCoche.get(random.nextInt(coloresCoche.size()));
    }

    private static String obtenerModelo(ArrayList<String> modelosCoche) {
        Random random = new Random();
        return modelosCoche.get(random.nextInt(modelosCoche.size()));
    }


}
