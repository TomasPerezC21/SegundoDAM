package Parte3API;

import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EjercicioNasa {

    public static void main(String[] args) {

        String fechaNacimiento = "1999-08-26";

        String urlApi = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=" + fechaNacimiento;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlApi))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());

                String titulo = json.getString("title");
                String urlImagen = json.getString("url");
                String explicacion = json.getString("explanation");


                String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
                String nombreArchivo = timestamp + "-TomasPerezCarrillo.md";

                String contenidoMd =  " Título: " + titulo + "\n\n" +
                        "URL de la imagen: " + urlImagen + "\n\n" +
                        "Explicación:\n" + explicacion;

                try {
                    FileWriter writer = new FileWriter(nombreArchivo);
                    writer.write(contenidoMd);
                    System.out.println("--- CONTENIDO DEL ARCHIVO GENERADO (" + nombreArchivo + ") ---");
                    System.out.println(contenidoMd);
                    System.out.println("\nArchivo guardado correctamente.");
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }

            } else {
                System.out.println("Error al conectar con la API: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}