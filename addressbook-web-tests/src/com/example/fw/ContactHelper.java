package com.example.fw;

import org.openqa.selenium.By;

import com.example.tests.ContactData;

public class ContactHelper extends HelperBase {

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	public void initContactCreation() {
		click(By.linkText("add new"));
	}

	public void submitContactForm() {
		click(By.name("submit"));
	}

	public void fillAddressBookForm(ContactData contact) {
		type(By.name("firstname"), contact.firstName);
		type(By.name("lastname"), contact.lastName);
		type(By.name("address"), contact.addressPrimary);
		type(By.name("home"), contact.telephoneHomePri);
		type(By.name("mobile"), contact.telephoneMobile);
		type(By.name("work"), contact.telephoneWork);
		type(By.name("email"), contact.email1);
		type(By.name("email2"), contact.email2);

		// WAS new
		// Select(driver.findElement(By.name("new_group"))).selectByVisibleText("group
		// 1");
		// selectByText(By.name("new_group"), "group 1");

		selectByText(By.name("bday"), contact.birthdayDay);
		selectByText(By.name("bmonth"), contact.birthdayMonth);

		type(By.name("byear"), contact.birthdayYear);
		type(By.name("address2"), contact.addressSecondary);
		type(By.name("phone2"), contact.telephoneHomeSec);
	}

	public void deleteContact(int index) {
		initContactModification(index);
		click(By.xpath("(//input[@name='update'])[2]"));
	}

	public void initContactModification(int index) {
		click(By.xpath("(//img[@alt='Edit'])[" + index + "]"));
	}

	public void submitContactModification() {
		click(By.xpath("(//input[@name='update'])[1]"));
	}

}
