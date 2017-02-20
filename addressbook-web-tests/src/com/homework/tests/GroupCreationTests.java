package com.homework.tests;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {
	@Test
	public void testGroupCreation() throws Exception {
		openMainPage();
		gotoGroupsPage();
		initGroupCreation();
		GroupData valueObject = new GroupData();
		valueObject.name = "name1";
		valueObject.header = "header1";
		valueObject.footer = "footer1";
		fillGroupsForm(valueObject);
		submitGroupForm();
		returnToGroupsPage();
	}
	
	@Test
	public void testEmptyGroupCreation() throws Exception {
		openMainPage();
		gotoGroupsPage();
		initGroupCreation();
		GroupData valueObject = new GroupData();
		fillGroupsForm(valueObject);
		submitGroupForm();
		returnToGroupsPage();
	}
}
