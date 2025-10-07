package logica;

import java.io.*;
import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> listaProductos;

    public final String RUTA_FICHERO_DATOS = "productos.txt";

    public Inventario() throws IOException {
        listaProductos=new ArrayList<>();
        leerDatosFicheroTexto();
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
           return false;
       }

       public void leerDatosFicheroTexto() throws IOException {

           FileReader fr = new FileReader(RUTA_FICHERO_DATOS);

           BufferedReader br = new BufferedReader(fr);

           String linea =  br.readLine();

           String[] datosFichero;

           while (linea != null) {
               datosFichero = linea.split(",");
               if (datosFichero[1].equals("Electronico")) {
                   Electronico productoElectronico = new Electronico(Integer.parseInt(datosFichero[0]), datosFichero[2], Double.parseDouble(datosFichero[3]), Integer.parseInt(datosFichero[4]), datosFichero[5], Integer.parseInt(datosFichero[6]));
                   listaProductos.add(productoElectronico);

               }
               else{
                   Ropa productoRopa = new Ropa(Integer.parseInt(datosFichero[0]), datosFichero[2], Double.parseDouble(datosFichero[3]), Integer.parseInt(datosFichero[4]), datosFichero[5], datosFichero[6]);
                   listaProductos.add(productoRopa);
               }
               linea = br.readLine();
           }

           br.close(); //se cierra el fichero para guardar los datos
       }

       public void guardarDatosFichero() throws IOException {

           FileWriter fw = new FileWriter(RUTA_FICHERO_DATOS);
           BufferedWriter bw = new BufferedWriter(fw);

           for (Producto p:listaProductos) {
               if (p instanceof Electronico){
                   Electronico productoElectronico = (Electronico) p;
                   bw.write(productoElectronico.getId()+",Electronico,"+productoElectronico.getNombre()+","+productoElectronico.getPrecio()+","+productoElectronico.getStock()+","+productoElectronico.getStock()+","+productoElectronico.getGarantia()+","+productoElectronico.getGarantia());
               }
               else{
                   Ropa productoRopa = (Ropa) p;
                   bw.write(productoRopa.getId()+",Ropa,"+productoRopa.getNombre()+","+productoRopa.getPrecio()+","+productoRopa.getStock()+","+productoRopa.getTalla()+","+productoRopa.getTalla());
               }
               bw.newLine();
           }
           bw.close();
           fw.close();

       }

    public void eliminarProductoSinStock() throws IOException {

           listaProductos.removeIf(p->p.getStock()<=0);
           guardarDatosFichero();
    }

}
