package logica;

import java.util.ArrayList;

public class Inventario {

    private ArrayList<Producto> listaProductos;

    public Inventario() {
        this.listaProductos = new ArrayList<>();

        Electronico productoElectronico1 = new Electronico(1, "Tablet", 300, 8, "Samsung", 12);
        listaProductos.add(productoElectronico1);

        Electronico productoElectronico2 = new Electronico(2, "Televisión", 750, 4, "Panasonic", 24);
        listaProductos.add(productoElectronico2);

        Ropa productoRopa1 = new Ropa(3, "Camiseta", 32, 4, "XL", "Algodón");
        listaProductos.add(productoRopa1);

        Ropa productoRopa2 = new Ropa(4, "Pantalón", 40, 10, "L", "Lino");
        listaProductos.add(productoRopa2);

    }

    public void insertarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public String mostrarInventario(){
        String resultado = "";

        for (Producto producto : listaProductos) {
            resultado = resultado + producto.getNombre() + "\n";
        }

        return resultado;
    }


}
