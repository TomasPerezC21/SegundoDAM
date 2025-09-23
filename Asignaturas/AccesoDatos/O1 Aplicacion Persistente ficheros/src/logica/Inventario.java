package logica;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> listaProductos;

    public Inventario() {
        listaProductos=new ArrayList<>();
        Electronico productoElectronico1=new Electronico(1,"Aire acondicionado", 1000,2,"Lg", 12);
        Electronico productoElectronico2=new Electronico(2,"Ventilador",50,5,"lg",24);
        Ropa productoRopa1=new Ropa(3,"Camiseta",30, 4,"M","Algodon");
        Ropa productoRopa2=new Ropa(4,"Pantalon",50, 3,"M","Algodon");
        listaProductos.add(productoElectronico1);
        listaProductos.add(productoElectronico2);
        listaProductos.add(productoRopa1);
        listaProductos.add(productoRopa2);

    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public boolean insertarProducto(Producto producto){
        for (Producto p:listaProductos) {
            if (p.getId() == producto.getId()) {
                return false;
            }
        }
        listaProductos.add(producto);
        return true;
    }
    public String mostrarInventario(){
        String resultado="Listado de productos."+System.lineSeparator();
        for(Producto p:listaProductos){
            resultado= resultado+ p.mostrarDetalles()+System.lineSeparator();
        }
        return resultado;
    }

    /**
     *
     * @param idProducto, a vender
     * @param cantidadVender,
     * @return true si se vende el producto correctamente, false si no hay stock suficiente
     * @throws ProductoNoInventarioException, el proeucto no está en el inventario.
     */
    public boolean venderProducto(int idProducto, int cantidadVender) throws ProductoNoInventarioException{
        for (Producto p:listaProductos){
            if (p.getId()==idProducto){
                if (p.estaDisponible(cantidadVender)){
                    p.vender(cantidadVender);
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        throw new ProductoNoInventarioException("El producto no esta en el inventario");

       }

    /**
     *
     * @param idProducto
     * @param cantidadReponer
     * @return true si se repone el producto, si no está el producto devuelve ProductoNoInventarioException
     * @throws ProductoNoInventarioException
     */
       public boolean reponerProducto(int idProducto, int cantidadReponer) throws ProductoNoInventarioException{
           for (Producto p:listaProductos) {
               if (p.getId() == idProducto) {
                   p.reponer(cantidadReponer);
                   return true;
               }
           }
           throw  new ProductoNoInventarioException("El producto no esta en el inventario");

       }
}
