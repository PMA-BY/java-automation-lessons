package com.example.tests;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

	@Test
	public void testNonEmptyGroupCreation() throws Exception {
		openMainPage();
		gotoGroupsPage();
		initGroupCreation();
		GroupData group = new GroupData();
		group.name = "group name 1";

		group.footer = "group footer 1";
		
		group.header = "group header 1";
		
		fillGroupForm(group);
		submitGroupCreation();
		returnToGroupsPage();
	}

	@Test
	public void testEmptyGroupCreation() throws Exception {
		openMainPage();
		gotoGroupsPage();
		initGroupCreation();
		GroupData group = new GroupData("", "", "");
		fillGroupForm(group);
		submitGroupCreation();
		returnToGroupsPage();
	}
}
