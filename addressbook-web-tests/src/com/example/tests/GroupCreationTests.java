package com.example.tests;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

	@Test
	public void testNonEmptyGroupCreation() throws Exception {

		app.getNavigationHelper().openMainPage();
		app.getNavigationHelper().gotoGroupsPage();
		app.getGroupHelper().initGroupCreation();
		
		GroupData group = new GroupData();
		group.name = "group name 1";
		group.footer = "group footer 1";
		group.header = "group header 1";
		
		app.getGroupHelper().fillGroupForm(group);
		app.getGroupHelper().submitGroupForm();
		app.getGroupHelper().returnToGroupsPage();
	}

	@Test
	public void testEmptyGroupCreation() throws Exception {
		app.getNavigationHelper().openMainPage();
		app.getNavigationHelper().gotoGroupsPage();
		app.getGroupHelper().initGroupCreation();
		GroupData group = new GroupData("", "", "");
		app.getGroupHelper().fillGroupForm(group);
		app.getGroupHelper().submitGroupForm();
		app.getGroupHelper().returnToGroupsPage();
	}
}
