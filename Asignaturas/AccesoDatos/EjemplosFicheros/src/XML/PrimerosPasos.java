package XML;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class PrimerosPasos {

    static class Producto implements Serializable {
        private static final long serialVersionUID = 1L;
        String nombre;
        Float precio;

        public Producto(String nombre, Float precio) {
            this.nombre = nombre;
            this.precio = precio;
        }
        public String getNombre() {
            return nombre;
        }
        public Float getPrecio() {
            return precio;
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {

        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        listaProductos.add(new Producto("Papel", 100f));
        listaProductos.add(new Producto("Reloj", 200f));
        listaProductos.add(new Producto("TV", 300f));
        listaProductos.add(new Producto("Mando", 400f));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom  = builder.getDOMImplementation();

        //Creamos el documento XML y el nodo raiz "listaProductos".
        // El nodo raiz es el que va a englobar los productos en este caso
        Document documento = dom.createDocument(null, "xml", null);
        Element raiz = documento.createElement("listaProductos");
        //Se añade la raiz al documento
        documento.getDocumentElement().appendChild(raiz);

        //Elemento para cada producto de la lista
        Element nodoProducto = null;

        //Elemento para cada dato de producto (nombre y precio)
        Element nodoDatos = null;

        //Obtenemos el valor de cada dato llamando a los getters
        Text texto = null;

        //For para obtener cada producto de la lista y añadirlo a la raiz
        for (Producto producto : listaProductos) {
            //Se crea la etiqueta producto y la añadimos a la raiz
            nodoProducto = documento.createElement("producto");
            raiz.appendChild(nodoProducto);

            //Por cada dato de producto hay que hacer otro subnodo y juntarlo a la raiz
            nodoDatos = documento.createElement("nombre");
            nodoProducto.appendChild(nodoDatos);
            texto = documento.createTextNode(producto.getNombre());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("precio");
            nodoProducto.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(producto.getPrecio()));
            nodoDatos.appendChild(texto);
        }

        Source src = new DOMSource(documento);
        Result resultado = new StreamResult(new File("fichero.xml"));

        Transformer transformador = TransformerFactory.newInstance().newTransformer();
        transformador.transform(src,resultado);

        try {
            lectura();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void lectura() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(new File("fichero.xml"));

        File file = new File("src/Archivos/productos.txt");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        NodeList productos = documento.getElementsByTagName("producto");
        for (int i = 0; i < productos.getLength(); i++) {
            Node nodoProducto = productos.item(i);
            Element producto1  = (Element) nodoProducto;
            //bw.write(producto1.getElementsByTagName("nombre").item(0).getTextContent() + " " + producto1.getElementsByTagName("precio").item(0).getTextContent()+ " ");
            System.out.print(producto1.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getTextContent() + " ");
            System.out.println(producto1.getElementsByTagName("precio").item(0).getChildNodes().item(0).getTextContent() + " ");
        }
        bw.close();

    }

}
