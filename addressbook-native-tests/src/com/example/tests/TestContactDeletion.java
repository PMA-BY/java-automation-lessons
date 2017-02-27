package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.testng.annotations.Test;

import com.example.fw.Contact;
import com.example.utils.SortedListOf;

public class TestContactDeletion extends TestBase {
	
	@Test
	public void deleteContactAssertExportedFiles() throws IOException {
		File oldContacts = app.getContactHelper().oldContacts;
		File newContacts = app.getContactHelper().newContacts;
		Random rnd = new Random();
		
		//old states
		app.getContactHelper().exportContacts(oldContacts,true);
		SortedListOf<Contact> oldList = app.getContactHelper().loadExportedContactsFromFile(oldContacts);
		
		//actions
		//int index = rnd.nextInt(oldList.size());
		//app.getContactHelper().deleteContact(index+1);
		
		//new states
		app.getContactHelper().exportContacts(newContacts,false);
		SortedListOf<Contact> newList = app.getContactHelper().loadExportedContactsFromFile(newContacts);
		
		//compare
		//assertThat(newList, equalTo(oldList.without(index)));
	}
	
	@Test
	public void deleteContactAssertContacts() throws IOException { //���� ����������� �������� �����, �.�. ��� ���� ���������� ��� ��������� ��������
		Random rnd = new Random();
		SortedListOf<Contact> newList = new SortedListOf<Contact>();
		
		//old states
		SortedListOf<Contact> oldList = app.getContactHelper().getContacts();
		
		//actions
		//int index = rnd.nextInt(oldList.size());
		//app.getContactHelper().deleteContact(index+1);
		
		//new states
		//if (oldList.size() <= 1)
		//	newList = null;
		//else newList = app.getContactHelper().getContacts();
		
		//compare
		//assertThat(newList, equalTo(oldList.without(index)));
	}

}
