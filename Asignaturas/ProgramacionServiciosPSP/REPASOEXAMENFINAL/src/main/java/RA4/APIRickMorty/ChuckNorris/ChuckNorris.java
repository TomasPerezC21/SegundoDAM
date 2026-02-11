package RA4.APIRickMorty.ChuckNorris;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChuckNorris {
    //API Sugerida (Objeto simple)
    //URL: https://api.chucknorris.io/jokes/random
    //
    //Ejercicio de repaso: "El Filtro de Humor"
    //Objetivo: Realizar una petición para obtener una frase de Chuck Norris y guardarla en un fichero solo si la frase es corta (menos de 100 caracteres).
    //Lógica:
    //
    //Extrae el valor del campo "value" (que contiene la frase).
    //
    //Si no, muestra por consola: "Frase demasiado larga para el archivo".

    public static void main(String[] args) {

        String api = "https://api.chucknorris.io/jokes/random";

        try {

            HttpClient cliente = HttpClient.newHttpClient();

            //for (int i = 0; i < 5; i++) {

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(api)).GET().build();

            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {

                JSONObject json = new JSONObject(response.body());

                String frase = json.getString("value");

                String fichero = "src/main/java/RA4/APIRickMorty/ChuckNorris/ficheroNorris.txt";

                if (frase.length() < 100) {

                    BufferedWriter writer = new BufferedWriter(new FileWriter(fichero, true));
                    writer.write(frase);
                    writer.newLine();
                    writer.close();
                }else{
                    System.out.println("Frase demasiado larga.");
                }

            }else{
                System.out.println("Ha fallado la conexión: " + response.statusCode());
            }

            //for }
        } catch (IOException   | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

}
