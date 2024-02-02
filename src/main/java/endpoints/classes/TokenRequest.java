//package api;
//
//import endpoints.Routes;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//
//public class TokenRequest {
//
//    public static void main(String[] args) throws IOException {
//        // Define the URL
//        String tokenUrl  = Routes.post_tokenEndpoint_url;
//        URL url = new URL(tokenUrl);
//
//        // Create the connection
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//        // Set the request method to POST
//        connection.setRequestMethod("POST");
//
//        // Set the content type
//        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//
//        // Enable input/output streams
//        connection.setDoOutput(true);
//        connection.setDoInput(true);
//
//        // Create the request body with the required parameters
//        String requestBody = "grant_type=" + URLEncoder.encode("client_credentials", "UTF-8") +
//                "&username=" + URLEncoder.encode("malinda", "UTF-8") +
//                "&password=" + URLEncoder.encode("Pass@1234", "UTF-8") +
//                "&client_id=" + URLEncoder.encode("Meq_qTUrqSVHvXetqIXX8R8rNfoa", "UTF-8") +
//                "&client_secret=" + URLEncoder.encode("mZDItwX1896lxAlvqIPLyZFKQvUa", "UTF-8");
//
//        // Write the request body to the output stream
//        try (OutputStream outputStream = connection.getOutputStream()) {
//            outputStream.write(requestBody.getBytes("UTF-8"));
//        }
//
//        // Get the response code
//        int responseCode = connection.getResponseCode();
//        System.out.println("Response Code: " + responseCode);
//
//        // Read the response
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//            StringBuilder response = new StringBuilder();
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//
//            // Print the response
//            System.out.println("Response Body: " + response.toString());
//        }
//
//        // Disconnect the connection
//        connection.disconnect();
//    }
//}

package endpoints.classes;

import endpoints.Routes;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TokenRequest {

    public static String requestToken() throws IOException {
        // Define the URL
        String tokenUrl = Routes.post_tokenEndpoint_url;
        URL url = new URL(tokenUrl);

        // Creating the connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        // Set the content type
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // Enable input/output streams
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // Create the request body with the required parameters
        String requestBody = "grant_type=" + URLEncoder.encode("client_credentials", "UTF-8") +
                "&username=" + URLEncoder.encode("malinda", "UTF-8") +
                "&password=" + URLEncoder.encode("Pass@1234", "UTF-8") +
                "&client_id=" + URLEncoder.encode("Meq_qTUrqSVHvXetqIXX8R8rNfoa", "UTF-8") +
                "&client_secret=" + URLEncoder.encode("mZDItwX1896lxAlvqIPLyZFKQvUa", "UTF-8");

        // Write the request body to the output stream
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(requestBody.getBytes("UTF-8"));
        }

        // Get the response code
        int responseCode = connection.getResponseCode();
        System.out.println("Token Request Response Code: " + responseCode);

        // Read the response
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        // Disconnect the connection
        connection.disconnect();

        // Parse the response to extract the access token
        return parseAccessToken(response.toString());
    }

    private static String parseAccessToken(String response) {
        // Assuming the response is in JSON format and the access token is a field named "access_token"
        return response.split("\"access_token\":\"")[1].split("\"")[0];
    }
}
