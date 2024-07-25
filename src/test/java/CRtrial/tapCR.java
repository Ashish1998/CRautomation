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

import io.appium.java_client.android.AndroidDriver;

public class tapCR {

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
			
			Point location = box.getLocation();
			Dimension size = box.getSize();
			
			Point centerOfElement = getCenterofElement(location, size, 200);
			
            PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
			         .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
			         .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
			         .addAction(new Pause(finger1, Duration.ofMillis(10)))
			         .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			
			driver.perform(Collections.singletonList(sequence));
			 
			System.out.println("Tapped on the option");
			
			//click on settings button
			
			WebElement setting = driver.findElement(By.id("org.curiouslearning.container:id/settings"));
			setting.click(); 
			System.out.println("Settings clicked");
	}

	private static Point getCenterofElement(Point location, Dimension size, int offset) {
	
		int y;
		int x;
		return new Point(x= location.getX() + size.getWidth()/2, 
				         y= location.getY()+ size.getHeight()/2 + offset );
	}

}