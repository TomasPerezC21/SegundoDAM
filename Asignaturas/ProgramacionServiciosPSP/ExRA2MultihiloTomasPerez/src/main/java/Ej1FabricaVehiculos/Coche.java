package Ej1FabricaVehiculos;

//Clase con los parametros del coche
public class Coche {

    private int idCoche;
    private String modelo;
    private String color;

    public Coche(int idCoche, String modelo, String color) {
        this.idCoche = idCoche;
        this.modelo = modelo;
        this.color = color;
    }

    public int getIdCoche() {
        return idCoche;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "idCoche=" + idCoche +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
