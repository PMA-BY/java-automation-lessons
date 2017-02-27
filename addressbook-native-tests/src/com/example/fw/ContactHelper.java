package com.example.fw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.example.utils.SortedListOf;

public class ContactHelper extends HelpersBase {
	
	private String exportPath = manager.getProperty("exportContacts.path");
	public File oldContacts = new File(manager.getProperty("oldContacts"));
	public File newContacts = new File(manager.getProperty("newContacts"));

	public ContactHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}
	
	public void createContact(Contact contact) {
		initContactCreation();
		fillContactForm(contact);
		confirmContactCreation();
	}
	
	public void deleteContact(int index) {
		initContactDeletion(index);
		confirmContactDeletion();
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------

	private void initContactCreation() {
		manager.getAutoItHelper()
			.winWaitAndActivate("AddressBook Portable", "", 5000)
			.click("Add").winWaitAndActivate("Add Contact", "", 5000);
	}
	
	private void fillContactForm(Contact contact) {
		manager.getAutoItHelper()
			.send("TDBEdit12", contact.name)
			.send("TDBEdit11", contact.surname)
			.send("TDBEdit8", contact.streetAddress)
			.send("TDBEdit7", contact.city)
			.send("TDBEdit6", contact.postalCode)
			.send("TDBEdit5", contact.country)
			.send("TDBEdit4", contact.phone)
			.send("TDBEdit3", contact.fax)
			.send("TDBEdit2", contact.cell)
			.send("TDBEdit10", contact.email)
			.send("TDBEdit1", contact.webPage)
			.send("TDBEdit9", contact.internetContact);
	}
	
	private void confirmContactCreation() {
		manager.getAutoItHelper()
			.click("Save")
			.winWaitAndActivate("AddressBook Portable", "", 5000);
	}
	
	public void initContactDeletion(int index) {
		selectContactByIndex(index);
		Contact contact = getSelectedContact();
		selectContactByIndex(index);
		manager.getAutoItHelper()
			.send("{SPACE}")
			.click("Delete");
		System.out.println("DELETED CONTACT: [name=" + contact.name + ", surname=" + contact.surname + ", email=" + contact.email + ", cell=" + contact.cell + "]");
	}
	
	public void confirmContactDeletion() {
		manager.getAutoItHelper()
			.winWaitAndActivate("Confirm", "", 5000)
			.click("TButton2");
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------

	public void exportContacts(File file, boolean isFirstExport) {
		if (file.exists()) {
			file.delete();
			System.out.println("OLD FILE <" + file + "> HAS BEEN DELETED");
		}
		String fileName = file.toString();
		manager.getAutoItHelper()
			.winWaitAndActivate("AddressBook Portable", "", 5000)
			.click("Export");
		if (isFirstExport) 
			manager.getAutoItHelper().winWaitAndActivate("Сохранить как", "", 5000);
		if (!isFirstExport)
			manager.getAutoItHelper().winWaitAndActivate("Save CSV File", "", 5000);
		manager.getAutoItHelper()
			.send("ComboBox1", exportPath)
			.send("Edit1", fileName)
			.click("Button2");
		manager.getAutoItHelper()
			.winWaitAndActivate("Information", "", 5000)
			.click("TButton1")
			.winWaitAndActivate("AddressBook Portable", "", 5000);
	}
	
	public SortedListOf<Contact> loadExportedContactsFromFile(File file) throws IOException {
		if (!file.exists()) {
			System.out.println("FILE FOR LOADING CONTACTS <" + file + "> NOT FOUND");
		}
		SortedListOf<Contact> list = new SortedListOf<Contact>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = bufferedReader.readLine();
		while(line != null) {
				line = line + ",!";
			String[] part = line.split(",");
			Contact contact = new Contact()
				.setName(part[0])
				.setSurname(part[1])
				.setEmail(part[2])
				.setInternetContact(part[3])
				.setStreetAddress(part[4])
				.setCity(part[5])
				.setPostalCode(part[6])
				.setCountry(part[7])
				.setPhone(part[8])
				.setFax(part[9])
				.setCell(part[10])
				.setWebPage(part[11]);
			list.add(contact);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return list;
	}
	
	private void selectContactByIndex(int index) {
		manager.getAutoItHelper()
			.winWaitAndActivate("AddressBook Portable", "", 5000)
			.click("TListView1");
		for(int i = 0; i < index; i++) {
			manager.getAutoItHelper().send("{DOWN}");
			System.out.println("send DOWN_" + (i+1));
		}
	}
	
	private Contact getSelectedContact() {
		manager.getAutoItHelper().click("Edit");
		if (manager.getAutoItHelper().isWindow("Information","")) {
			manager.getAutoItHelper()
				.winWaitAndActivate("Information", "", 5000)
				.click("OK");
			System.out.println("getSelectedContact: THERE IS NO CONTACTS IN ADDRESSBOOK");
			return null;
		}
		manager.getAutoItHelper().winWaitAndActivate("Update Contact", "", 5000);
		Contact contact = new Contact()
			.setName(manager.getAutoItHelper().getText("TDBEdit12"))
			.setSurname(manager.getAutoItHelper().getText("TDBEdit11"))
			.setStreetAddress(manager.getAutoItHelper().getText("TDBEdit8"))
			.setCity(manager.getAutoItHelper().getText("TDBEdit7"))
			.setPostalCode(manager.getAutoItHelper().getText("TDBEdit6"))
			.setCountry(manager.getAutoItHelper().getText("TDBEdit5"))
			.setPhone(manager.getAutoItHelper().getText("TDBEdit4"))
			.setFax(manager.getAutoItHelper().getText("TDBEdit3"))
			.setCell(manager.getAutoItHelper().getText("TDBEdit2"))
			.setEmail(manager.getAutoItHelper().getText("TDBEdit10"))
			.setWebPage(manager.getAutoItHelper().getText("TDBEdit1"))
			.setInternetContact(manager.getAutoItHelper().getText("TDBEdit9"));
		manager.getAutoItHelper()
			.click("Cancel")
			.winWaitAndActivate("AddressBook Portable", "", 5000);
		System.out.println("SELECTED CONTACT: [name=" + contact.name + ", surname=" + contact.surname + ", email=" + contact.email + ", cell=" + contact.cell + "]");
		return contact;
	}

	public SortedListOf<Contact> getContacts() {
		SortedListOf<Contact> contacts = new SortedListOf<Contact>();
		Contact contact1 = new Contact().setName("1");
		Contact contact2 = new Contact().setName("2");
		int currentIndex = 0;
		while (!contact1.equals(contact2)) {
			currentIndex = currentIndex + 1;
			selectContactByIndex(currentIndex);
			contact1 = getSelectedContact();
			if (contact1 == null) 
				return null;
			selectContactByIndex(currentIndex + 1);
			contact2 = getSelectedContact();
			contacts.add(contact1);
	}
		return contacts;
	}

}
