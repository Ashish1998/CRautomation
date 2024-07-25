package CRtrial;

import java.net.URI;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class selectlang {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        capabilities.setCapability("deviceName","Xiaomi note 6 pro");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("app","C:\\apk\\cr qa 8-3.apk");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("appPackage", "org.curiouslearning.container");
        capabilities.setCapability("appActivity", "org.curiouslearning.container.MainActivity");
        
        try {
            URL url = URI.create("http://127.0.0.1:4723/wd/hub").toURL();
            
            AndroidDriver driver = new AndroidDriver(url, capabilities);
            System.out.println("Application Started");

            // Click on settings button
            WebElement setting = driver.findElement(By.id("org.curiouslearning.container:id/settings"));
            setting.click(); 
            
            // Click on dropdown to open options
            WebElement dropdown = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Show drop-down menu\"]"));
            dropdown.click();

            // Find all options within the dropdown
            List<WebElement> options = driver.findElements(By.xpath("//android.widget.Spinner[@resource-id=org.curiouslearning.container:id/autoComplete"));

            // Iterate through each option to find the one with text "language" and select it
            for (WebElement option : options) {
                if (option.getText().equals("English")) {
                    option.click();
                    break; // Break the loop once the desired option is found and clicked
                }
            }
            
            System.out.println("Language selected successfully.");
            
            // Close the app
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
