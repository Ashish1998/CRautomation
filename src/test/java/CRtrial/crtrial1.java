package CRtrial;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class crtrial1 {

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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on drop down
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.id("org.curiouslearning.container:id/text_input_end_icon")));
        option.click();
        System.out.println("Drop down opened");

        // Drop down options
        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("org.curiouslearning.container:id/settings_box")));

        // Get screen size
        Dimension screenSize = driver.manage().window().getSize();

        // Perform scrolling using common method
        performScroll(driver, screenSize, 0.5, 0.9, 0.5, 0.5, 8);
        System.out.println("Scrolled to the required language");

        // Tapping on the settings box
        Point boxCenter = getCenterOfElement(box.getLocation(), box.getSize(), 200);
        performTap(driver, boxCenter);
        System.out.println("Tapped on the language");

        // Click on app icon
        WebElement appIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//android.widget.ImageView[@content-desc=\"Curious Reader\"])[1]")));
        appIcon.click();
        System.out.println("Opening the app by clicking on the icon");

        // Wait for the play button to be visible and clickable
        WebElement play = wait.until(ExpectedConditions.elementToBeClickable(By.id("org.curiouslearning.container:id/web_app")));
        play.click();
        System.out.println("Clicked on Play button");

        // Tap on the level button using common method for coordinate calculation
        Point levelButtonPosition = calculateScreenCoordinates(screenSize, 0.15, 0.2);
        performTap(driver, levelButtonPosition);
        System.out.println("Clicked on level button");

        // Close the driver
        // driver.quit();
    }

    // Method to perform scrolling
    private static void performScroll(AndroidDriver driver, Dimension screenSize, double startXPct, double startYPct, double endXPct, double endYPct, int repetitions) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        int startX = (int) (screenSize.getWidth() * startXPct);
        int startY = (int) (screenSize.getHeight() * startYPct);
        int endX = (int) (screenSize.getWidth() * endXPct);
        int endY = (int) (screenSize.getHeight() * endYPct);

        for (int i = 0; i < repetitions; i++) {
            Sequence scrollSequence = new Sequence(finger, 1)
                    .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger, Duration.ofMillis(200)))
                    .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(scrollSequence));
        }
    }

    // Method to perform tap
    private static void performTap(AndroidDriver driver, Point location) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tapSequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), location))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(10)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tapSequence));
    }

    // Common method to calculate screen coordinates
    private static Point calculateScreenCoordinates(Dimension screenSize, double xPercentage, double yPercentage) {
        int x = (int) (screenSize.getWidth() * xPercentage);
        int y = (int) (screenSize.getHeight() * yPercentage);
        return new Point(x, y);
    }

    // Method to get the center of an element
    private static Point getCenterOfElement(Point location, Dimension size, int offset) {
        int x = location.getX() + size.getWidth() / 2;
        int y = location.getY() + size.getHeight() / 2 + offset;
        return new Point(x, y);
    }
}
