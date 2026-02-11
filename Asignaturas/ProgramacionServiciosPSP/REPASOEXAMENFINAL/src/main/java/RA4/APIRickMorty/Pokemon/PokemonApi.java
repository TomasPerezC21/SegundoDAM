package RA4.APIRickMorty.Pokemon;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PokemonApi {

    //Obtener datos de pokemon, listar habilidades y guardar en fichero aquellas que no sean ocultas

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("INTRODUCE NOMBRE POKEMON: ");
        String endpoint = sc.nextLine();

        String api = "https://pokeapi.co/api/v2/pokemon/" + endpoint;

        String fichero = "pokemonxd.txt";

        try {

            HttpClient cliente =  HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(api)).GET().build();

            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {

                JSONObject json = new JSONObject(response.body());

                var habilidades = json.getJSONArray("abilities");

                BufferedWriter writer = new BufferedWriter(new FileWriter(fichero, true));

                System.out.println("LEIDO DESDE API");
                for (int i = 0; i < habilidades.length(); i++) {

                    System.out.println(habilidades.getJSONObject(i).getJSONObject("ability").getString("name"));

                    if(habilidades.getJSONObject(i).getBoolean("is_hidden")){
                        writer.write(habilidades.getJSONObject(i).getJSONObject("ability").getString("name"));
                        writer.newLine();
                    }

                }
                writer.close();

                System.out.println("Leido desde fichero: ");
               BufferedReader reader = new BufferedReader(new FileReader(fichero));

               String linea;

               while ((linea = reader.readLine()) != null) {
                   System.out.println(linea);
               }

            }else{
                System.err.println("error en la api xd");
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
