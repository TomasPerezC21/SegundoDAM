package RA4.APIRickMorty;

import com.google.gson.*;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RickMorty {

    //esto es para printear el json bonito en consola
//        JsonElement jsonElement = JsonParser.parseString(response.body());
//
//        Gson gson = new GsonBuilder()
//                .setPrettyPrinting()
//                .create();

    public static void main(String[] args) {

        String api = "https://rickandmortyapi.com/api/character/?page=1";

        try {

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(api)).GET().build();

        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {

            String fichero = "supervivientesss.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichero));

            JSONObject json = new JSONObject(response.body());

            var resultados = json.getJSONArray("results");

            int contadorHumanos = 0;

            System.out.println("--- PERSONAJES VIVOS ENCONTRADOS ---");

            for (int i = 0; i < resultados.length(); i++) {

                JSONObject personaje = resultados.getJSONObject(i);

                String nombre = personaje.getString("name");
                String estado = personaje.getString("status");
                String especie = personaje.getString("species");

                if (estado.equalsIgnoreCase("alive")) {
                    System.out.println(nombre + " .Especie: " + especie);
                    writer.write(nombre + " .Especie: " + especie + "\n");
                    writer.newLine();

                    if (especie.equalsIgnoreCase("Human")) {
                        contadorHumanos++;
                    }
                }
            }
            writer.close();


            System.out.println("------------------------------------");
            System.out.println("Total de humanos vivos en esta página: " + contadorHumanos);

        } else {
            System.out.println("Error en la petición: " + response.statusCode());

        }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

}
