import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

// retreive weather data from API - this backend logic will fetch the latest weather
// data from the external API and return it. The GUI will
// display this data to the user
public class WeatherApp {
    // fetch weather data for given location
    public static JSONObject getWeatherData(String locationName){
        //get location coordinates using the geolocation API

        JSONArray locationData = getLocationData(locationName);

        // extract latitude and longitude data
        JSONObject location = (JSONObject) locationData.get(0);
        double latitude = (double) location.get("latitude");
        double longitude = (double) location.get("longitude");

        // buil API request URL with location coordinates
        String urlString = "https://api.open-meteo.com/v1/forecast?" +
                "latitude=" + latitude + "&longitude=" + longitude +
                "&hourly=temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m";
    try{
        // call api and get response
        HttpURLConnection conn = fetchApiResponse(urlString);

        // check for response status
        // 200 - means that the connection was a Success
        if(conn.getResponseCode() != 200){
            System.out.println("Erro: Could not connect to API");
            return null;
        }
        // store resulting json data
        StringBuilder resultJson = new StringBuilder();
        Scanner scanner = new Scanner(conn.getInputStream());
        while (scanner.hasNext()){
            // read and store into the String builder
            resultJson.append(scanner.nextLine());
        }
        // close scanner
        scanner.close();;

        // close url connection
        conn.disconnect();

        //parse through our data
        JSONParser parser = new JSONParser();
        JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

        // retrieve hourly data

        JSONObject hourly = (JSONObject)  resultJsonObj.get("hourly");

        // we want to get the current hour's data
        // so we need to get the index of our current hour


    }catch (Exception e){
        e.printStackTrace();
    }

        return null;
    }
        // retrieves geographic coordinates for given location name
    public static JSONArray getLocationData(String locationName){
        // replace any whitespace in location name to + to adhere to API's request format
        locationName = locationName.replaceAll("", "+");

        // build API url withlocation parameter
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" +
                locationName + "&count=10&language=en&format=json";
        try{
            // call api and get a response
            HttpURLConnection conn = fetchApiResponse(urlString);

            // check response status
            // 200 means successfull connection
            if(conn.getResponseCode() != 200){
                System.out.println("Error: Could not connect to API");
                return null;
            }else {
                 // Store the API result
                StringBuilder resultJson = new StringBuilder();
                Scanner scanner =new Scanner(conn.getInputStream());

                //  read and store the resulting data into our String builder
                while (scanner.hasNext()){
                    resultJson.append(scanner.nextLine());

                }

                // close scanner
                scanner.close();

                // close url connection
                conn.disconnect();

                //parse the JSON string int a Json obj
                JSONParser parser = new JSONParser();
                JSONObject resultsJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

                // get the list of location data the API generated from the location name
                JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
                return locationData;

            }


        }catch (Exception e){
            e.printStackTrace();
        }
        // couldn't find location
        return null;
    }

    private static HttpURLConnection fetchApiResponse(String urlString){
        try {
            // attempt to create connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // set request metho to get

            conn.setRequestMethod("GET");

            // connect to our API

            conn.connect();
            return conn;
        }catch (IOException e){
            e.printStackTrace();
        }

        // could not make connection

        return null;

    }
}

