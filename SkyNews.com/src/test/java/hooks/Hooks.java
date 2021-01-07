package hooks;



import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	public WebDriver driver =null;;
	public Logger logger=Logger.getLogger(Hooks.class);
	
	
	@Before
	public WebDriver StartBrowser() {
		
		//Logging
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
		
		// Launching Browser
		if (driver == null)	
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;

	}

	@After
	public void TearDown() {
		//Closing browser
		logger.info("--Closing browser--");
		driver.close();
		driver.quit();
		driver = null;

	}


}
