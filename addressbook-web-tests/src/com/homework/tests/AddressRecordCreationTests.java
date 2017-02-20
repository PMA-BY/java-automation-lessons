package com.homework.tests;

import org.testng.annotations.Test;

public class AddressRecordCreationTests extends TestBase {
	
	@Test
	public void testAddressRecordCreation() throws Exception {
		openMainPage();
		initAddressRecordCreation();
		
		AddressBookData valueObject = new AddressBookData();
		valueObject.firstName 			= "FIRST  name 1";
		valueObject.lastName  			= "LAST   name 1";
		valueObject.addressPrimary 		= "PRI address 1";
		valueObject.telephoneHomePri 	= "12345678-1";
		valueObject.telephoneMobile 	= "22345678-1";
		valueObject.telephoneWork 		= "32345678-1";
		valueObject.email1 				= "email1@test.com";
		valueObject.email2 				= "email2@test.com";
		valueObject.birthdayDay 		= "1";
		valueObject.birthdayMonth 		= "February";
		valueObject.birthdayYear 		= "1978";
		// valueObject.group 				= "";
		valueObject.addressSecondary 		= "SEC address 1";
		valueObject.telephoneHomeSec 		= "42345678-1";
		
		fillAddressBookForm(valueObject);
		submitAddressBookForm();
		openMainPage(); 
	}
	
	@Test
	public void testEmptyAddressRecordCreation() throws Exception {
		openMainPage();
		initAddressRecordCreation();	
		
		AddressBookData valueObject = new AddressBookData();
		//fillAddressBookForm(valueObject);
		
		submitAddressBookForm();
		openMainPage();
	}

}
