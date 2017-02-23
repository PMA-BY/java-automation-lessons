package com.example.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.example.fw.ContactHelper;
import static com.example.fw.ContactHelper.CREATION;



public class ContactCreationTests extends TestBase {
	
	@Test
	public void testContactCreation() throws Exception {
		app.navigateTo().mainPage();
		app.getContactHelper().initContactCreation();
		
		ContactData contact = new ContactData();
		contact.firstName 			= "FIRST  name 1";
		contact.lastName  			= "LAST   name 1";
		contact.addressPrimary 		= "PRI address 1";
		contact.telephoneHomePri 	= "12345678-1";
		contact.telephoneMobile 	= "22345678-1";
		contact.telephoneWork 		= "32345678-1";
		contact.email1 				= "email1@test.com";
		contact.email2 				= "email2@test.com";
		contact.birthdayDay 		= "1";
		contact.birthdayMonth 		= "February";
		contact.birthdayYear 		= "1978";
		//contact.group 				= "";
		contact.addressSecondary 		= "SEC address 1";
		contact.telephoneHomeSec 		= "42345678-1";
		
		app.getContactHelper().fillAddressBookForm(contact, CREATION);
		app.getContactHelper().submitContactForm();
		app.navigateTo().mainPage(); 
	}
	
	@Test
	public void testEmptyAddressRecordCreation() throws Exception {
		app.navigateTo().mainPage();
		app.getContactHelper().initContactCreation();	
		
		ContactData contact = new ContactData();
		app.getContactHelper().fillAddressBookForm(contact, CREATION);
		
		app.getContactHelper().submitContactForm();
		app.navigateTo().mainPage();
	}

	
}
