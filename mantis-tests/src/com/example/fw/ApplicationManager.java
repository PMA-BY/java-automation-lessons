package com.example.fw;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ApplicationManager {

	private WebDriver driver;
	public String baseUrl;

	private NavigationHelper navigationHelper;
	private Properties properties;
	private HibernateHelper hibernateHelper;
	private AccountHelper accountHelper;


	public ApplicationManager(Properties properties) {
		this.properties = properties;
	}


	public void stop() {
		driver.quit();
	}

	public AccountHelper getAccountHelper() {
		if (accountHelper == null) {
			accountHelper = new AccountHelper(this);
		}
		return accountHelper;
	}

	
	public NavigationHelper navigateTo() {
		if (navigationHelper == null) {
			navigationHelper = new NavigationHelper(this);
		}
		return navigationHelper;
	}

	public HibernateHelper getHibernateHelper() {
		if (hibernateHelper == null) {
			hibernateHelper = new HibernateHelper(this);
		}
		return hibernateHelper;
	}

	public WebDriver getDriver() {
		String browser = properties.getProperty("browser");

		if (driver == null) {
			if ("chrome".equals(browser)) {
				driver = new ChromeDriver();
			} else if ("firefox".equals(browser)) {
				driver = new FirefoxDriver();
			} else if ("ie".equals(browser)) {
				System.setProperty("webdriver.ie.driver",
						new File("C:/git/Selenium/MicrosoftWebDriver.exe").getAbsolutePath());
				// System.setProperty("webdriver.ie.driver",
				// "D:/iexploredriver.exe");
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				driver = new InternetExplorerDriver(caps);
			} else {
				throw new Error("Unsupported Browser: " + browser);
			}

			baseUrl = properties.getProperty("baseUrl"); // baseUrl =
															// "http://localhost/addressbook/";

			// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.get(baseUrl);
		}
		return driver;
	}
	
	public String getProperty (String key) {
		return properties.getProperty(key);
	}

}
