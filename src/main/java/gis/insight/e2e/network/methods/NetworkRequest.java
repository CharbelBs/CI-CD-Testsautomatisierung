package gis.insight.e2e.network.methods;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.v130.network.model.Request;
import org.openqa.selenium.devtools.v130.network.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkRequest {
    private static final Logger logger = LoggerFactory.getLogger(NetworkRequest.class);

    public String createNetworkRequest(WebDriver driver, String suchbegriff, int size) {
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();

        // Enable Network
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.clearListeners();

        // Add listeners for request and response
        devTools.addListener(Network.requestWillBeSent(), request -> {
            Request req = request.getRequest();
            logger.debug("Request URL: {}", req.getUrl());
            logger.debug("Request Method: {}", req.getMethod());
            logger.debug("Request Headers: {}", req.getHeaders());
        });

        devTools.addListener(Network.responseReceived(), response -> {
            Response res = response.getResponse();
            logger.debug("Response URL: {}", res.getUrl());
            logger.debug("Status Code: {}", res.getStatus());
            logger.debug("Response Headers: {}", res.getHeaders());
        });

        // Send the network request with authentication
        return sendPostRequest(driver, suchbegriff, size, "VM6");
    }

    private String sendPostRequest(WebDriver driver, String suchbegriff, int size, String environment) {
        HttpURLConnection connection = null;
        StringBuilder response = new StringBuilder();
        try {
            // Define the URL (replace with actual URLs for different environments)
            URL url = new URL(environment.equals("VM6") ? "http://vm6.example.com" : "http://prelive.example.com");
            connection = (HttpURLConnection) url.openConnection();

            // Set up the request method and headers
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Include authentication cookies or tokens
            String cookies = getCookiesFromSelenium(driver);
            if (!cookies.isEmpty()) {
                connection.setRequestProperty("Cookie", cookies);
            }

            connection.setDoOutput(true);

            // Construct and log the JSON payload
            String jsonPayload = String.format(
                    "{ \"search\": \"%s\", \"from\": 0, \"size\": %d, \"indices\": [\"ms\"], \"filter\": [] }",
                    escapeJson(suchbegriff), size
            );
            logger.debug("Request Payload: {}", jsonPayload);

            // Write the JSON payload
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get the response code and log it
            int responseCode = connection.getResponseCode();
            logger.debug("Response Code: {}", responseCode);

            // Read the response body
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    responseCode >= 200 && responseCode < 300 ? connection.getInputStream() : connection.getErrorStream(),
                    StandardCharsets.UTF_8))) {
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line.trim());
                }
                logger.debug("Response: {}", response);
            }

        } catch (Exception e) {
            logger.error("Error sending POST request to {}: {}", environment, e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response.toString();
    }

    private String getCookiesFromSelenium(WebDriver driver) {
        StringBuilder cookies = new StringBuilder();
        var seleniumCookies = driver.manage().getCookies();
        for (var cookie : seleniumCookies) {
            cookies.append(cookie.getName())
                    .append("=")
                    .append(cookie.getValue())
                    .append("; ");
        }
        return cookies.toString();
    }

    private String escapeJson(String input) {
        return input.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
