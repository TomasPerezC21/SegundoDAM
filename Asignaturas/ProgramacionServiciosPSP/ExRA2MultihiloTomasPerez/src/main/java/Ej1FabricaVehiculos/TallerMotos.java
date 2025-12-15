package Ej1FabricaVehiculos;

import java.util.ArrayList;
import java.util.Random;


//clase que crea motos para el concesionario
public class TallerMotos implements Runnable {

    private Concesionario concesionario;

    private static ArrayList<String> modelosMoto;

    public TallerMotos(Concesionario concesionario){
        this.concesionario = concesionario;
        modelosMoto = getModelosMoto();
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            Moto m = crearMoto(i+1);
            concesionario.addMotos(m);
            try {
                System.out.println("Moto con id: " + (i+1) + " listo");
                Thread.sleep(800L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static Moto crearMoto(int id) {

        Moto m = new Moto(id, obtenerModelo(modelosMoto), getCilindrada());
        return m;
    }

    public static ArrayList<String> getModelosMoto() {

        ArrayList<String> modelosMoto = new ArrayList<String>();

        modelosMoto.add("Honda cbr");
        modelosMoto.add("Kawasaki Ninja");
        modelosMoto.add("Yamaha r8");
        modelosMoto.add("Suzuki r92");
        return modelosMoto;
    }

    public static int getCilindrada(){
        Random random = new Random();
        return random.nextInt(50, 1001);
    }

    private static String obtenerModelo(ArrayList<String> modelosMoto) {
        Random random = new Random();
        return modelosMoto.get(random.nextInt(modelosMoto.size()));
    }

}
