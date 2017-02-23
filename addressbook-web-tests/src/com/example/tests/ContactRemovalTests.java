package com.example.tests;

import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase {

	@Test
	public void deleteSomeContact() {
		app.navigateTo().mainPage();				// go to Home / Contacts Page
		
		int index = 0;
		
		app.getContactHelper().deleteContact(index);
		app.navigateTo().mainPage();				// return to Home / Contacts Page
	}

}