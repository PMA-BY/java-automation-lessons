package com.example.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

	@Test
	public void modifySomeContact() {
		app.getNavigationHelper().openMainPage(); // go to Home / Contacts Page

		int index = 1;

		app.getContactHelper().initContactModification(index);

		ContactData contact = new ContactData();
		contact.lastName = "NEW last name";

		app.getContactHelper().fillAddressBookForm(contact);
		app.getContactHelper().submitContactModification();

		app.getNavigationHelper().openMainPage(); // return to Home / Contacts
													// Page
	}

}
