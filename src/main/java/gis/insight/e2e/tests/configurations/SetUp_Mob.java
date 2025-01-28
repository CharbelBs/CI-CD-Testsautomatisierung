package gis.insight.e2e.tests.configurations;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.JavascriptExecutor;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SetUp_Mob {

    private static final String DEVICE_NAME = "Android_Device";
    private static final String PLATFORM_NAME = "Android";
    private static final String PLATFORM_VERSION = "15";
    private static final String APP_PACKAGE = "gis.insight.mobile.v2";
    private static final String APP_ACTIVITY = "gis.insight.mobile.v2.MainActivity";
    private static final Duration COMMAND_TIMEOUT = Duration.ofSeconds(30000);
    private static final int IMPLICIT_WAIT_SECONDS = 30;
    private static final int WEBVIEW_RETRY_DELAY_MS = 2000;
    private static final int WEBVIEW_RETRY_COUNT = 5;
    private static final String JSESSION_ID_KEY = "JSESSIONID";
    private static final String INSIGHT_SESSION_ID_KEY = "INSIGHTSESSIONID";

    private static String jsessionId = "";
    private static String insightSessionId = "";

    public static DelayedWebAppiumDriver SetMobileDriver() throws MalformedURLException {
        var options = new UiAutomator2Options()
                .setDeviceName(DEVICE_NAME)
                .setUdid("emulator-5554")
                .setPlatformName(PLATFORM_NAME)
                .setPlatformVersion(PLATFORM_VERSION)
                .setAutomationName("UiAutomator2")
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setEnsureWebviewsHavePages(true)
                .setNativeWebScreenshot(true)
                .setNewCommandTimeout(COMMAND_TIMEOUT)
                .setAutoGrantPermissions(true)
                .setDisableWindowAnimation(true)
                .setAutoWebview(true);
                //.setNoReset(true);

        options.setCapability("unicodeKeyboard", true);
        options.setCapability("resetKeyboard", true);

        DelayedWebAppiumDriver driver = new DelayedWebAppiumDriver(new URL("http://127.0.0.1:4723"), options, 500);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_SECONDS, TimeUnit.SECONDS);

        switchToWebViewContext(driver);
        captureSessionCookies(driver);

        return driver;
    }



    private static void switchToWebViewContext(DelayedWebAppiumDriver driver) {
        Set<String> contextNames;
        boolean switched = false;

        for (int i = 0; i < WEBVIEW_RETRY_COUNT; i++) {
            contextNames = ((SupportsContextSwitching) driver).getContextHandles();
            for (String context : contextNames) {
                if (context.contains("WEBVIEW")) {
                    ((SupportsContextSwitching) driver).context(context);
                    switched = true;
                    System.out.println("Switched to WebView context: " + context);
                    break;
                }
            }
            if (switched) break;
            try {
                Thread.sleep(WEBVIEW_RETRY_DELAY_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting to switch to WebView context", e);
            }
        }
        if (!switched) {
            System.err.println("Failed to switch to WebView context.");
        }
    }

    public static void captureSessionCookies(AppiumDriver driver) {
        if (driver instanceof JavascriptExecutor) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            String cookies = (String) jsExecutor.executeScript("return document.cookie;");
            System.out.println("Cookies: " + cookies);

            if (cookies == null || cookies.isEmpty()) {
                retrieveSessionFromStorage(jsExecutor);
            } else {
                if (cookies.contains(JSESSION_ID_KEY)) {
                    jsessionId = extractTokenFromCookies(cookies, JSESSION_ID_KEY);
                    System.out.println("JSESSIONID: " + jsessionId);
                }
                if (cookies.contains(INSIGHT_SESSION_ID_KEY)) {
                    insightSessionId = extractTokenFromCookies(cookies, INSIGHT_SESSION_ID_KEY);
                    System.out.println("INSIGHTSESSIONID: " + insightSessionId);
                }
            }
        }
    }

    private static void retrieveSessionFromStorage(JavascriptExecutor jsExecutor) {
        Object jsessionIdFromLocalStorage = jsExecutor.executeScript("return window.localStorage.getItem('" + JSESSION_ID_KEY + "');");
        Object insightSessionIdFromLocalStorage = jsExecutor.executeScript("return window.localStorage.getItem('" + INSIGHT_SESSION_ID_KEY + "');");

        if (jsessionIdFromLocalStorage != null) {
            jsessionId = jsessionIdFromLocalStorage.toString();
            System.out.println("JSESSIONID from localStorage: " + jsessionId);
        }
        if (insightSessionIdFromLocalStorage != null) {
            insightSessionId = insightSessionIdFromLocalStorage.toString();
            System.out.println("INSIGHTSESSIONID from localStorage: " + insightSessionId);
        }
    }

    private static String extractTokenFromCookies(String cookies, String tokenName) {
        for (String cookie : cookies.split("; ")) {
            if (cookie.startsWith(tokenName)) {
                return cookie.split("=")[1];
            }
        }
        return "";
    }

    public static String getJSessionId() {
        return jsessionId;
    }

    public static String getInsightSessionId() {
        return insightSessionId;
    }
}
