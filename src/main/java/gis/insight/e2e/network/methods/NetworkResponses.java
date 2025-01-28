package gis.insight.e2e.network.methods;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.v130.network.model.RequestId;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkResponses {
	private static final Logger logger = LoggerFactory.getLogger(NetworkResponses.class);

	public static boolean AktionErfolgreich(String requestUrl, String authorizationToken) {
		int maxRetries = 10;
		int waitTime = 3000; // 3 seconds

		String expectedContentType = "application/json";
		String userAgent = "Mozilla/5.0";

		for (int attempt = 1; attempt <= maxRetries; attempt++) {
			try {
				URL url = new URL(requestUrl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accept", expectedContentType);
				connection.setRequestProperty("Authorization", authorizationToken);
				connection.setRequestProperty("User-Agent", userAgent);

				int responseCode = connection.getResponseCode();
				String contentType = connection.getHeaderField("Content-Type");

				if (responseCode == 200 && expectedContentType.equals(contentType)) {
					String content = readResponse(connection);
					logger.info("Request Successful: {}", content);
					connection.disconnect();
					return true;
				} else {
					logger.warn("Attempt {} failed with status: {} and content type: {}", attempt, responseCode, contentType);
				}
				connection.disconnect();

			} catch (Exception e) {
				logger.error("Attempt {}: Exception occurred - {}", attempt, e.getMessage());
			}

			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				logger.error("Interrupted during retry wait", e);
				Thread.currentThread().interrupt();
			}
		}

		logger.error("Max retries reached. Network check failed.");
		return false;
	}

	public void NetworkSearchResultPosition(WebDriver driver, String networkName, String objektName,
											String objektElement, String sourceElement, String sourceElementValue) {
		DevTools devTools = ((HasDevTools) driver).getDevTools();
		devTools.createSession();

		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		devTools.clearListeners();

		devTools.addListener(Network.responseReceived(), responseReceived -> {
			String requestUrl = responseReceived.getResponse().getUrl();
			if (requestUrl.contains(networkName)) {
				RequestId requestId = responseReceived.getRequestId();
				Network.GetResponseBodyResponse body = devTools.send(Network.getResponseBody(requestId));
				String responseBody = body.getBody();

				if (isJson(responseBody)) {
					processJsonResponse(responseBody, objektName, objektElement, sourceElement, sourceElementValue);
				} else {
					logger.warn("Response is not JSON: {}", responseBody);
				}
			}
		});
	}

	private void processJsonResponse(String responseBody, String objektName, String objektElement,
									 String sourceElement, String sourceElementValue) {
		try {
			JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
			if (jsonObject.has(objektName)) {
				JsonArray hitsArray = jsonObject.getAsJsonObject(objektName).getAsJsonArray(objektElement);
				for (int i = 0; i < hitsArray.size(); i++) {
					JsonElement hitElement = hitsArray.get(i);
					if (hitElement.isJsonObject()) {
						JsonObject hitObject = hitElement.getAsJsonObject();
						if (hitObject.has("_source")) {
							JsonObject sourceObject = hitObject.getAsJsonObject("_source");
							if (sourceObject.has(sourceElement) &&
									sourceElementValue.equals(sourceObject.get(sourceElement).getAsString())) {
								logger.info("Position = {}", i + 1);
								return;
							}
						}
					}
				}
				logger.info("No matching elements found.");
			} else {
				logger.warn("No '{}' array found in the response.", objektName);
			}
		} catch (Exception e) {
			logger.error("Error processing JSON response", e);
		}
	}

	private boolean isJson(String responseBody) {
		try {
			JsonParser.parseString(responseBody);
			return true;
		} catch (JsonSyntaxException e) {
			return false;
		}
	}

	private static String readResponse(HttpURLConnection connection) throws IOException {
		StringBuilder response = new StringBuilder();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		}
		return response.toString();
	}
}
