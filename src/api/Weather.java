package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;

public class Weather {

    public Weather() {
    }

    public static String parseTemperature(String weatherData) {
        try {
            JSONObject json = new JSONObject(weatherData);
            JSONArray data = json.getJSONArray("data");

            if (data.length() > 0) {
                JSONObject weatherInfo = data.getJSONObject(0);
                double temperature = weatherInfo.getDouble("temp");
                return temperature + "°C";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "N/A";
    }

    public static String parseDescription(String weatherData) {
        try {
            JSONObject json = new JSONObject(weatherData);
            JSONArray data = json.getJSONArray("data");

            if (data.length() > 0) {
                JSONObject weatherInfo = data.getJSONObject(0);
                String description = weatherInfo.getJSONArray("weather").getJSONObject(0).getString("description");
                return description;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "N/A";
    }

    public static String getWeatherData(String apiKey, String city) {
        try {
            String apiUrl = "https://home.openweathermap.org/api_keys" + city + "&key=" + apiKey;
            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            StringBuilder response;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            connection.disconnect();

            return response.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
