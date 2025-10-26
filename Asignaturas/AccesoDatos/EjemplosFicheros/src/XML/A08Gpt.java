package XML;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class A08Gpt {

    static class Libro {

        String titulo;
        String autor;
        int anio;
        String genero;

        public Libro(String titulo, String autor, int anio, String genero) {
            this.titulo = titulo;
            this.autor = autor;
            this.anio = anio;
            this.genero = genero;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getAutor() {
            return autor;
        }

        public void setAutor(String autor) {
            this.autor = autor;
        }

        public int getAnio() {
            return anio;
        }

        public void setAnio(int anio) {
            this.anio = anio;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }
    }

    public static void main(String[] args) {

        ArrayList<Libro> libros = new ArrayList<Libro>();
        libros.add(new Libro("La Rosa Roja", "Álvaro Guy", 2015, "Terror"));

        try {
            crearXML(libros);
            lectura();
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    public static void crearXML(ArrayList<Libro> listaLibros) throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document documento = builder.newDocument();
        Element raiz = documento.createElement("biblioteca");
        documento.appendChild(raiz);


        for (Libro libro : listaLibros) {
            Element nodoLibro = documento.createElement("libro");
            raiz.appendChild(nodoLibro);

            Element titulo = documento.createElement("titulo");
            titulo.appendChild(documento.createTextNode(libro.getTitulo()));
            nodoLibro.appendChild(titulo);

            Element autor = documento.createElement("autor");
            autor.appendChild(documento.createTextNode(libro.getAutor()));
            nodoLibro.appendChild(autor);

            Element anio = documento.createElement("anio"); // sin tilde
            anio.appendChild(documento.createTextNode(String.valueOf(libro.getAnio())));
            nodoLibro.appendChild(anio);

            Element genero = documento.createElement("genero");
            genero.appendChild(documento.createTextNode(libro.getGenero()));
            nodoLibro.appendChild(genero);
        }

        Source src = new DOMSource(documento);
        Result resultado = new StreamResult(new File("ficheroLibros.xml"));

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
        Document documento = builder.parse(new File("ficheroLibros.xml"));
        documento.getDocumentElement().normalize();

        NodeList libros = documento.getElementsByTagName("libro");
        for (int i = 0; i < libros.getLength(); i++) {
            Element elemento = (Element) libros.item(i);
            System.out.print(elemento.getElementsByTagName("titulo").item(0).getTextContent() + " ");
            System.out.println(elemento.getElementsByTagName("autor").item(0).getTextContent());
            System.out.println(elemento.getElementsByTagName("anio").item(0).getTextContent());   // <= "anio"
            System.out.println(elemento.getElementsByTagName("genero").item(0).getTextContent());
        }
    }


}
