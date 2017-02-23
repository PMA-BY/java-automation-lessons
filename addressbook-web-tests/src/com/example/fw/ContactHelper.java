package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;
import com.example.tests.GroupData;

public class ContactHelper extends HelperBase {
	
	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;
	
	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	public void initContactCreation() {
		click(By.linkText("add new"));
	}

	public void submitContactForm() {
		click(By.name("submit"));
	}

	public void fillAddressBookForm(ContactData contact, boolean formType) {
		type(By.name("firstname"), contact.firstName);
		type(By.name("lastname"), contact.lastName);
		type(By.name("address"), contact.addressPrimary);
		type(By.name("home"), contact.telephoneHomePri);
		type(By.name("mobile"), contact.telephoneMobile);
		type(By.name("work"), contact.telephoneWork);
		type(By.name("email"), contact.email1);
		type(By.name("email2"), contact.email2);

		selectByText(By.name("bday"), contact.birthdayDay);
		selectByText(By.name("bmonth"), contact.birthdayMonth);

		type(By.name("byear"), contact.birthdayYear);

		if (formType == CREATION) {
			// selectByText(By.name("new_group"), "group 1");
		} else {
			if (driver.findElements(By.name("new_group")).size() != 0) {
				throw new Error("Group selector exists in contact modification form");
			}
		}

		type(By.name("address2"), contact.addressSecondary);
		type(By.name("phone2"), contact.telephoneHomeSec);
	}

	public void deleteContact(int index) {
		initContactModification(index);
		click(By.xpath("(//input[@name='update'])[2]"));
	}

	public void initContactModification(int index) {
		click(By.xpath("(//img[@alt='Edit'])[" + (index + 1) + "]"));
	}

	public void submitContactModification() {
		click(By.xpath("(//input[@name='update'])[1]"));
	}

	// See more at:
	// http://stackoverflow.com/questions/10630120/selenium-webdriver-fetching-table-data
	// http://stackoverflow.com/questions/6198947/how-to-get-text-from-each-cell-of-an-html-table
	//
	// public List<ContactData> getContacts() {
	// List<ContactData> contacts = new ArrayList<ContactData>();
	//
	// List<WebElement> table_lines = driver.findElements(
	// By.xpath("(//img[@alt='Edit'])/ancestor::td[@class='center']/ancestor::tr[@name='entry']/td[2]"));
	//
	// for (WebElement table_line : table_lines) {
	// ContactData contact = new ContactData();
	//
	// String title = table_line.getText();
	//
	// // contact.firstName = title.substring("Select (".length(),
	// title.length() - ")".length());
	// contacts.add(contact);
	// }
	//
	// return contacts;
	// }

	/*
	 * (//img[@alt='Edit'])[7]/ancestor::td[@class='center']/ancestor::tr[@name=
	 * 'entry']/td[2]
	 * 
	 * 
	 * All First Names:
	 * (//img[@alt='Edit'])/ancestor::td[@class='center']/ancestor::tr[@name='
	 * entry']/td[2]/text()
	 * 
	 * All Last Names:
	 * (//img[@alt='Edit'])/ancestor::td[@class='center']/ancestor::tr[@name='
	 * entry']/td[3]/text()
	 * 
	 * All Emails:
	 * (//img[@alt='Edit'])/ancestor::td[@class='center']/ancestor::tr[@name='
	 * entry']/td[4]/a/text()
	 * 
	 */

}
