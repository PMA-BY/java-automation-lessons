package com.example.tests;

import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase {

	@Test
	public void deleteSomeContact() {
		app.getNavigationHelper().openMainPage();				// go to Home / Contacts Page
		
		int index = 1;
		
		app.getContactHelper().deleteContact(index);
		app.getNavigationHelper().openMainPage();				// return to Home / Contacts Page
	}

}