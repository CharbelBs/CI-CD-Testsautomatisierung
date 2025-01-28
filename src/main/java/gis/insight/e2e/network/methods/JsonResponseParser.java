package gis.insight.e2e.network.methods;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonResponseParser {
    
    public String getPosition(String jsonResponse, String erwarteteErgebnisse) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Parse the JSON string
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode hitsNode = rootNode.path("hits").path("hits");
            

            // Iterate through the hits array
            for (int i = 0; i < hitsNode.size(); i++) {
                JsonNode hitNode = hitsNode.get(i);
                String label = hitNode.path("_source").path("label").asText();
                

                // Check if label matches
                if (erwarteteErgebnisse.equals(label)) {
                    return String.valueOf(i + 1);  // Exit loop once found
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }
}
