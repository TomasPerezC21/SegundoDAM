package Ej1FabricaVehiculos;

//Clase con los parametros de la moto
public class Moto {

    private int idMoto;
    private String modelo;
    private int cilindrada;

    public Moto(int idMoto, String modelo, int cilindrada) {
        this.idMoto = idMoto;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
    }

    public int getIdMoto() {
        return idMoto;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "modelo='" + modelo + '\'' +
                ", cilindrada=" + cilindrada +
                '}';
    }
}
