package Ej1FabricaVehiculos;

public class ClienteCoches implements Runnable {

    private int idCliente;

    private Concesionario concesionario;

    public ClienteCoches(int idCliente,  Concesionario c) {
        this.idCliente = idCliente;
        this.concesionario = c;
    }

    public ClienteCoches(Concesionario c) {
        this.idCliente = 0;
        this.concesionario = c;
    }

    public int getIdCliente() {
        return idCliente;
    }

    @Override
    public void run() {
            concesionario.entregarCoches();
    }
}
