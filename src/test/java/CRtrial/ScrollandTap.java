package CRtrial;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class ScrollandTap {

    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", "MI Note 6 Pro");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("app", "C:\\apk\\cr.apk");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("appPackage", "org.curiouslearning.container");
        capabilities.setCapability("appActivity", "org.curiouslearning.container.MainActivity");

        URL url = URI.create("http://127.0.0.1:4723/wd/hub").toURL();

        AndroidDriver driver = new AndroidDriver(url, capabilities);
        System.out.println("Application Started");

        // Click on drop down
        WebElement option = driver.findElement(By.id("org.curiouslearning.container:id/text_input_end_icon"));
        option.click();
        System.out.println("Drop down opened");

        // Drop down options
        WebElement box = driver.findElement(By.id("org.curiouslearning.container:id/settings_box"));

        // Scrolling
        Dimension size = driver.manage().window().getSize();

        int startX = size.getWidth() / 2;
        int startY = (int) (size.getHeight() * 0.9);

        int endX = startX;
        int endY = size.getHeight() / 2;

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        for (int i = 0; i < 8; i++) {
            Sequence scrollSequence = new Sequence(finger1, 1)
                    .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger1, Duration.ofMillis(200)))
                    .addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(scrollSequence));
            
        }
        System.out.println("Scrolled to the prefered langauge");
        

        // Tapping
        Point location = box.getLocation();
        Dimension size1 = box.getSize();

        Point centerOfElement = getCenterofElement(location, size1, 200);

        Sequence tapSequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(10)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tapSequence));

        System.out.println("Tapped on the option");

        
        // Click on app icon
        WebElement appicon = driver.findElement(By.xpath(("(//android.widget.ImageView[@content-desc=\"Curious Reader\"])[1]")));
        appicon.click();
        System.out.println("Opening the app by clicking on the icon");
        
        
        //waiting for the app to download
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        //Clicking on the play button splash screen
        WebElement play = driver.findElement(By.id("org.curiouslearning.container:id/web_app"));
        play.click();
        System.out.println("Clicked on Play button");
        
       
        // Tap on the level button
        int screenWidth = size.getWidth();
        int screenHeight = size.getHeight();

        int topleftX = (int) (screenWidth * 0.15); // Approximate X coordinate (15% from the left)
        int topleftY = (int) (screenHeight * 0.2); // Approximate Y coordinate (20% from the top)


        
        Sequence taplevelbuttonSequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), topleftX, topleftY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(10)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(taplevelbuttonSequence));

        System.out.println("Clicked on level button");
        
    
        // Close the driver
        //driver.quit();
    }

    private static Point getCenterofElement(Point location, Dimension size1, int offset) {
        int x = location.getX() + size1.getWidth() / 2;
        int y = location.getY() + size1.getHeight() / 2 + offset;
        return new Point(x, y);
    }
}
