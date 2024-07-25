package CRtrial;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class APKInstall {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub

		//Gather desired capabilities 
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		 
		capabilities.setCapability("deviceName","Pixel 3");
		 capabilities.setCapability("platformName","Android");
		 capabilities.setCapability("automationName","Appium");
		 capabilities.setCapability("app","C:\\apk\\cr test R.apk");
		 capabilities.setCapability("platformVersion", "10");
		 
		 URL url = URI.create("http://127.0.0.1:4723/wd/hub").toURL();
		 
		 AndroidDriver driver = new AndroidDriver(url, capabilities);
		 System.out.println("Application Started");
		 
	}

}
