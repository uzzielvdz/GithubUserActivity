package com.uzzielvz.githubuseractivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.Event;
import java.lang.reflect.Type;
import java.util.List;

public class GithubUserActivity {

    public static void main(String[] args) {
        if (args.length == 1) {
            System.out.println("Usage: java GithubUserActivity <username>");
            return;
        }

        String username = args[0];
        fetchUserActivity(username);
    }

    public static void fetchUserActivity(String username) {
        String urlString = "https://api.github.com/users/" + username + "/events";

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Establecer cabeceras de la solicitud
            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");

            // Verificar el código de respuesta
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Mostrar la actividad
                displayActivity(response.toString());
            } else {
                System.out.println("Error: Can't fetch the user recent activity. Response code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }

    private static void displayActivity(String jsonResponse) {
        // Crear un objeto Gson
        Gson gson = new Gson();

        // Definir el tipo de datos que esperamos (una lista de objetos Event)
        Type eventListType = new TypeToken<List<Event>>() {}.getType();

        // Convertir el JSON a una lista de eventos
        List<Events> events = gson.fromJson(jsonResponse, eventListType);

        // Iterar sobre cada evento en la lista
        for (Events event : events) {
            String type = event.getType();
            String repoName = event.getRepo().getName();

            // Mostrar la información del evento
            System.out.println("Event: " + type + " on repository: " + repoName);
        }
    }
}

