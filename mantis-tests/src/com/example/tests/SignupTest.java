package com.example.tests;

import org.testng.annotations.Test;

import com.example.fw.User;

import org.junit.Assert;

public class SignupTest extends TestBase {

	@Test
	public void newUserShouldSignup() {
		User user = new User().setLogin("testuser1").setPassword("123456")
				.setEmail("testuser1@localhost");
		
		
		app.getAccountHelper().signup(user);
		Assert.assertTrue(app.getAccountHelper().isLogged(user));
	}

}
