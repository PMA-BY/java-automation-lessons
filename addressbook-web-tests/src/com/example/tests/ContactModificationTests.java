package com.example.tests;

import static com.example.fw.ContactHelper.MODIFICATION;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

	@Test
	public void modifySomeContact() {
		app.navigateTo().mainPage(); // go to Home / Contacts Page

		int index = 1;

		app.getContactHelper().initContactModification(index);

		ContactData contact = new ContactData();
		contact.lastName = "NEW last name";

		app.getContactHelper().fillAddressBookForm(contact, MODIFICATION);
		
		app.getContactHelper().submitContactModification();

		app.navigateTo().mainPage(); // return to Home / Contacts
													// Page
	}

}
