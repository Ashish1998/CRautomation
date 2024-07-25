package CRtrial;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class trailscroll {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		 
		capabilities.setCapability("deviceName","MI Note 6 Pro");
		 capabilities.setCapability("platformName","Android");
		 capabilities.setCapability("automationName","Appium");
		 capabilities.setCapability("app","C:\\apk\\cr.apk");
		 capabilities.setCapability("platformVersion", "9");
		 capabilities.setCapability("appPackage", "org.curiouslearning.container");
		 capabilities.setCapability("appActivity", "org.curiouslearning.container.MainActivity");
		 
		 URL url = URI.create("http://127.0.0.1:4723/wd/hub").toURL();
		 
		 AndroidDriver driver = new AndroidDriver(url, capabilities);
		 System.out.println("Application Started");

		 
		 //click on drop down 
		
			WebElement option = driver.findElement(By.id("org.curiouslearning.container:id/text_input_end_icon"));
			option.click();
			System.out.println("Drop down opened");
			
			//drop down options
			    
			WebElement box = driver.findElement(By.id("org.curiouslearning.container:id/settings_box"));
			
			Dimension size = driver.manage().window().getSize();

	        int startX = size.getWidth() / 2;
	        int startY = (int) (size.getHeight() * 0.9);
	        int endX = startX;
	        int endY = size.getHeight() / 2;

	        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

	        List<Integer> yOffsets = new ArrayList<>();
	        for (int i = 1; i <= 6; i++) {
	            yOffsets.add(100 + (i - 1) * 150); // Dynamically calculate y-coordinates
	        }

	        for (int i = 0; i < 7; i++) {
	            Sequence sequence = new Sequence(finger1, 1)
	                    .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
	                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
	                    .addAction(new Pause(finger1, Duration.ofMillis(200)))
	                    .addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY)) // Swipe action
	                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	            driver.perform(Collections.singletonList(sequence));

	            // Click on the dropdown option based on the y-coordinate
	            int optionY = yOffsets.get(i % yOffsets.size()); // Use modulus to loop over yOffsets
	            Sequence clickSequence = new Sequence(finger1, 1)
	                    .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, optionY))
	                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
	                    .addAction(new Pause(finger1, Duration.ofMillis(200)))
	                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	            driver.perform(Collections.singletonList(clickSequence));

	            // Optional: Add a pause between actions if needed
	            try {
	                Thread.sleep(500); // Adjust the duration as needed
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
			
		System.out.println("Scrolled..");

	}

}







