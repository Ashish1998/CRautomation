package CRtrial;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
public class APKINSTALL2 {
    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("automationName","uiautomator");
        capabilities.setCapability("app","C:\\apk\\CR qa.apk");
        capabilities.setCapability("platformVersion", "9");
        @SuppressWarnings("deprecation")
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, capabilities);
        System.out.println("Application Started");
    }
}