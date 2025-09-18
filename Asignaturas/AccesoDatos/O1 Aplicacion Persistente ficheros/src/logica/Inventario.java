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
        StringBuilder resultado = new StringBuilder();

        for (Producto producto : listaProductos) {
            resultado.append(producto.getNombre()).append("\n");
        }

        return resultado.toString();
    }

    public void VenderProducto(int id, int cantidad) throws ProductoNoInventarioException {

        for (Producto producto : listaProductos) {
            if (producto.getId() == id) {
                if(cantidad <= producto.getStock()) {
                    producto.setStock(producto.getStock() - cantidad);
                    System.out.println("Vendido. Unidades restantes de " + producto.getNombre() + ": " + producto.getStock() + ".");
                }
                else {
                    throw new ProductoNoInventarioException("No hay " + cantidad + " unidades de " + producto.getNombre() + ".");
                }
            }
            else {
                throw new ProductoNoInventarioException("Producto no encontrado en el inventario.");
            }
        }
    }

    public void ReponerProducto(int id, int cantidad) throws ProductoNoInventarioException {

        for (Producto producto : listaProductos) {
            int cantidadAntigua =  producto.getStock();

            if (producto.getId() == id) {
                producto.setStock(producto.getStock() + cantidad);
                System.out.println("Inventario actualizado con éxito. De " + cantidadAntigua + " a " + producto.getStock() + ".");
            }
            else {
                throw new ProductoNoInventarioException("Producto no encontrado en el inventario.");
            }
        }

    }


}
