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
	private GroupHelper groupHelper;
	private ContactHelper contactHelper;
	private Properties properties;
	private HibernateHelper hibernateHelper;

	private ApplicationModel model;

	public ApplicationManager(Properties properties) {
		this.properties = properties;
		model = new ApplicationModel();
		model.setGroups(getHibernateHelper().listGroups());
	}

	public ApplicationModel getModel() {
		return model;
	}

	public void stop() {
		driver.quit();
	}

	public NavigationHelper navigateTo() {
		if (navigationHelper == null) {
			navigationHelper = new NavigationHelper(this);
		}
		return navigationHelper;
	}

	public GroupHelper getGroupHelper() {
		if (groupHelper == null) {
			groupHelper = new GroupHelper(this);
		}
		return groupHelper;
	}

	public ContactHelper getContactHelper() {
		if (contactHelper == null) {
			contactHelper = new ContactHelper(this);
		}
		return contactHelper;
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

}
