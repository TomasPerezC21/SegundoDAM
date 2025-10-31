package servicio;

import modelo.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Inventario {

    private Connection conexion;
    private final String url = "jdbc:mysql://localhost:3306/tienda_online";
    private final String user = "root";
    private final String password = "alumnoDAM";

    public Inventario() throws IOException, ClassNotFoundException, SQLException {

        conexion= DriverManager.getConnection(url,user,password);

    }
    /**
     *
     * @param producto
     * @return true si el inserta el producto en el invenario si el producto pasado cómo parametro el id no está en el inventario
     */
    public boolean insertarProducto(Producto producto){

        if (producto instanceof Ropa){
            Ropa ropa = (Ropa)producto;
            String sql = "INSERT INTO productos\n" +
                    " (id, nombre, precio, stock, talla, material)\n " +
                    "VALUES (?,?,?,?,?,?)";


        }
        else{

        }
        return true;
    }


    //obtener arrayList de productos Electronicos y Ropa
    public ArrayList<Producto> getListaProductos() throws SQLException {
        ArrayList<Producto> listaProductos=new ArrayList<>();

        String sentenciaSQL = "select p.id, p.nombre, p.precio, p.stock, marca, garantia\n" +
                "from productos p, electronicos e\n" +
                "where p.id = e.id ;\n";

        PreparedStatement pst = conexion.prepareStatement(sentenciaSQL);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){

            Electronico electronico = new Electronico(rs.getInt(1), rs.getString(2),
                    rs.getDouble(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
            listaProductos.add(electronico);
        }

        String sentenciaSQL2 = "select p.id, p.nombre, p.precio, p.stock, talla, material\n" +
                "from productos p , ropa r\n" +
                "where p.id = r.id;\n";

        pst = conexion.prepareStatement(sentenciaSQL2);
        rs = pst.executeQuery();
        while(rs.next()){
            Ropa ropa = new Ropa(rs.getInt(1), rs.getString(2), rs.getDouble(3),
                    rs.getInt(4), rs.getString(5), rs.getString(6));
            listaProductos.add(ropa);
        }


        return listaProductos;

    }



    /**
     *
     * @param idProducto a vender
     * @param cantidad de producto a vender
     * @return true si realiza la venta porque hay suficente stock, devuelve false si no hay suficiente stock
     * @throws ProductoNoInventarioException si el producto a vender no está en el inventario
     */
    public boolean venderProducto(int idProducto, int cantidad) throws ProductoNoInventarioException{
     return true;
    }

    /**
     *
     * @param idProducto a reponer
     * @param cantidad de producto a reponer
     * @throws ProductoNoInventarioException, si el idProducto no está en el inventario
     */
    public boolean reponerProducto(int idProducto, int cantidad) {

        return false;
    }
}
