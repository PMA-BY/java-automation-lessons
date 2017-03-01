package com.example.tests;

import org.testng.annotations.Test;

import com.example.fw.AccountHelper;
import com.example.fw.AdminHelper;
import com.example.fw.User;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Assert;

public class SignupTest extends TestBase {

	private AdminHelper admin;

	@Test
	public void newUserShouldSignup() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		User user = new User().setLogin("testuser1").setPassword("123456").setEmail("testuser1@localhost");

		AccountHelper accHelper = app.getAccountHelper();

		admin.deleteUserIfExists(user);
		accHelper.signup(user);
		accHelper.login(user);

		Assert.assertTrue(app.getAccountHelper().isLogged(user));
	}

}
