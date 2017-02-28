package com.example.fw;

import org.openqa.selenium.By;

public class AccountHelper extends HelperWithWebDriverBase {

	public AccountHelper(ApplicationManager manager) {
		super(manager);
	}

	public void signup(User user) {
		openUrl("/"); // "/signup_page.php"
		click(By.xpath((" ")));

		typeText(By.id("username"), user.login);
		typeText(By.id("email-field"), user.email);

		click(By.cssSelector("input.button"));

	}

	public boolean isLogged(User user) {

		return false;
	}

}
