package CRtrial;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class scrollLang {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		 
		capabilities.setCapability("deviceName","MI Note 6 Pro");
		 capabilities.setCapability("platformName","Android");
		 capabilities.setCapability("automationName","Appium");
		 capabilities.setCapability("app","C:\\apk\\cr test 3-6.apk");
		 capabilities.setCapability("platformVersion", "9");
		 capabilities.setCapability("appPackage", "org.curiouslearning.container");
		 capabilities.setCapability("appActivity", "org.curiouslearning.container.MainActivity");
		 
		 URL url = URI.create("http://127.0.0.1:4723/wd/hub").toURL();
		 
		 AndroidDriver driver = new AndroidDriver(url, capabilities);
		 System.out.println("Application Started");

		 // click on settings button
		 
		 //WebElement setting = driver.findElement(By.id("org.curiouslearning.container:id/settings"));
			//setting.click(); 
			//System.out.println("Clicked on settings");
		 
		 //click on drop down 
		
			WebElement option = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Show drop-down menu\"]"));
			option.click();
			System.out.println("Drop down opened");
			
			//drop down options
			    
			WebElement box = driver.findElement(By.id("org.curiouslearning.container:id/settings_box"));
			
			//selecting language
		
			  WebElement language = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"  
			                                 +  ".scrollIntoView(new UiSelector()" + ".textMatches(\"" + "Indian English" + "\").instance(0))"));
			  language.click();
			  System.out.println("language selected");
	}

}