package com.example.tests;

import org.testng.annotations.Test;

import com.example.fw.Contact;

public class TestsContactCreation extends TestBase {
	 
	@Test
	public void shouldCreateContactWithValidData() {
		Contact contact = new Contact().setFirstName("tester").setLastName("last_tester");
		app.getContactHelper().createContact(contact);
	}

}