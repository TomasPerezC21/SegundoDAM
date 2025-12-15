package Ej1FabricaVehiculos;

public class ClienteMotos implements Runnable{

    private int idClienteMoto;
    private Concesionario concesionario;

    public ClienteMotos(int idClienteMoto, Concesionario concesionario) {
        this.idClienteMoto = idClienteMoto;
        this.concesionario = concesionario;
    }

    public ClienteMotos(Concesionario concesionario) {
        this.idClienteMoto = 0;
        this.concesionario = concesionario;
    }

    public int getIdClienteMoto() {
        return idClienteMoto;
    }

    @Override
    public void run() {
            concesionario.entregarMotos();

    }
}
