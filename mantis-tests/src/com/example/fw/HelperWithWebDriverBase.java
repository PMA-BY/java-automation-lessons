package com.example.fw;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class HelperWithWebDriverBase extends HelperBase {
	
	protected WebDriver driver;
	public boolean acceptNextAlert = true;
	
	protected void openUrl(String string) {
		driver.get(manager.getProperty("baseUrl") + string);
	}
	
	protected void openAbsoluteUrl(String string) {
		driver.get(string);
	}

	public HelperWithWebDriverBase(ApplicationManager manager) {
		super(manager);
		this.driver = manager.getDriver();
	}
	
	protected void click(By locator) {
		driver.findElement(locator).click();
	}

	protected void typeText(By locator, String text) {
		//if (text != null) { //с данным условием сравнение данных при редактировании контакта работает некорректно
			driver.findElement(locator).clear();
		    driver.findElement(locator).sendKeys(text);	
		//}
	}
	
	public WebElement findElement(By locator) {
		try {
			return driver.findElement(locator);
		} catch (Exception e) {
			return null;
		}
	}
	
	protected List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}
	
	protected void selectByText(By locator, String text) {
		if (text != null) 
			new Select(driver.findElement(locator)).selectByVisibleText(text);
	}
	
	protected void randomDropDownValue(By locator) {
		Select select = new Select(driver.findElement(locator));
		List<WebElement> groups = select.getOptions();
		Random rnd = new Random();
		String group = groups.get(rnd.nextInt(groups.size())).getText();
		selectByText(locator, group);
	}

/*............................................................................................................................*/	
	public boolean isElementPresent(By by) {
	    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	    try {
	      return driver.findElements(by).size() > 0;
	    } finally {
	      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    }
	}
	
	public String closeAlertAndGetItsText() {
		try{
			Alert alert = driver.switchTo().alert();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alert.getText();
		} finally {
			acceptNextAlert = true;
		}
	}
	
}
