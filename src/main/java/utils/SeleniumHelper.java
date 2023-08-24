package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;

public class SeleniumHelper {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {


            HashSet<String> ab2 = new HashSet<>();
            ab2.add("uploadButton.png");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            HashMap<String, Object> ltOptions = new HashMap<String, Object>();
            ltOptions.put("browserName", "Chrome");
            ltOptions.put("browserVersion", "113.0");
            ltOptions.put("platformName", "Windows 10");
            ltOptions.put("project", "Sikuli Test");
            ltOptions.put("selenium_version", "4.0.0");
            ltOptions.put("w3c", true);
            ltOptions.put("lambda:userFiles", ab2);
            capabilities.setCapability("LT:Options", ltOptions);



            try {
                driver = new RemoteWebDriver(new URL("https://LT_USERNAME:LT_ACCESS_KEY@hub.lambdatest.com/wd/hub"), capabilities);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }
}
