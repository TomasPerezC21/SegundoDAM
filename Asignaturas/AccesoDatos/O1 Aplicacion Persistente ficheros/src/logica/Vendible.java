package logica;

public interface Vendible {

    public void vender (int cantidad);

    public void reponer (int cantidad);

    public boolean estaDisponible (int cantidad);
}
