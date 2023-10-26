/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_forum1.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author OUMAIMA
 */
public class WeitherApi {

    public WeitherApi() {
    }
    
   public String parseTemperature(String weatherData) {
    try {
        JSONObject json = new JSONObject(weatherData);
        JSONArray data = json.getJSONArray("data");

        if (data.length() > 0) {
            JSONObject weatherInfo = data.getJSONObject(0);
            double temperature = weatherInfo.getDouble("temp");
            return temperature + "°C"; // You may need to convert the units based on the API response.
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return "N/A";
}

   public String parseDescription(String weatherData) {
    try {
        JSONObject json = new JSONObject(weatherData);
        JSONArray data = json.getJSONArray("data");

        if (data.length() > 0) {
            JSONObject weatherInfo = data.getJSONObject(0);
            String description = weatherInfo.getString("datetime");
            return description;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return "N/A";
}

public static String getWeatherData(String apiKey, String city) {
        try {
            String apiUrl = "https://api.weatherbit.io/v2.0/current?city=" + city + "&key=" + apiKey;
            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            connection.disconnect();

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

