package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static com.example.tests.ContactDataGenerator.loadContactsFromFile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.fw.Contact;
import com.example.utils.SortedListOf;

public class TestContactCreation extends TestBase {
	
	private static final String[] args = {"1","contacts.csv"};

	@DataProvider
	public Iterator<Object[]> ContactsFromFile() throws IOException {
		return wrapContactsForDataProvider(loadContactsFromFile(new File("contacts.csv"))).iterator();
	}

	@Test(dataProvider = "ContactsFromFile")
	public void createContactWithValidDataAssertExportedFiles(Contact contact) throws IOException {
		ContactDataGenerator.main(args);
		File oldContacts = app.getContactHelper().oldContacts;
		File newContacts = app.getContactHelper().newContacts;
		
		//old states
		app.getContactHelper().exportContacts(oldContacts,true);
		SortedListOf<Contact> oldList = app.getContactHelper().loadExportedContactsFromFile(oldContacts);
		
		//actions
		app.getContactHelper().createContact(contact);
		
		//new states
		app.getContactHelper().exportContacts(newContacts,false);
		SortedListOf<Contact> newList = app.getContactHelper().loadExportedContactsFromFile(newContacts);
		
		//compare
		assertThat(newList, equalTo(oldList.withAdded(contact)));
	}
	
	@Test(dataProvider = "ContactsFromFile")
	public void createContactWithValidDataWithAssertContacts(Contact contact) throws IOException { //тест выполняется довольно долго, т.к. два раза перебирает все имеющиеся контакты
		ContactDataGenerator.main(args);
		SortedListOf<Contact> oldList = new SortedListOf<Contact>();
		//old states
		oldList = app.getContactHelper().getContacts();
		
		//actions
		app.getContactHelper().createContact(contact);
		
		//new states
		SortedListOf<Contact> newList = app.getContactHelper().getContacts();
		
		//compare
		assertThat(newList, equalTo(oldList.withAdded(contact)));
	}

}
