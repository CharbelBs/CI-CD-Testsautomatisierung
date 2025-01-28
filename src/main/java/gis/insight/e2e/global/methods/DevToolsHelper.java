package gis.insight.e2e.global.methods;

import org.openqa.selenium.devtools.v130.network.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DevToolsHelper {

    private final org.openqa.selenium.devtools.DevTools devTools;
    private final List<String> capturedRequests;

    public DevToolsHelper(org.openqa.selenium.devtools.DevTools devTools) {
        this.devTools = devTools;
        this.capturedRequests = new ArrayList<>();
        this.devTools.createSession(); // Ensure a session is created
    }

    /**
     * Enables network monitoring.
     */
    public void enableNetworkMonitoring() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(), request -> {
            String networkURL = request.getRequest().getUrl();
            System.out.println("Captured URL: " + networkURL);
            capturedRequests.add(networkURL);
        });
    }

    /**
     * Validates whether the captured requests contain the expected URL.
     *
     * @param expectedUrl The expected URL to search for.
     * @return True if the URL was found, otherwise false.
     */
    public boolean isUrlCaptured(String expectedUrl) {
        return capturedRequests.stream().anyMatch(networkURL -> networkURL.contains(expectedUrl));
    }

    /**
     * Clears all listeners and resets the captured requests.
     */
    public void clear() {
        devTools.clearListeners();
        capturedRequests.clear();
    }

    /**
     * Closes the DevTools session.
     */
    public void close() {
        devTools.close();
    }
}
