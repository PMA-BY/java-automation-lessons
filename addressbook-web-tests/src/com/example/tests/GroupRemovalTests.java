package com.example.tests;

import org.testng.annotations.Test;

public class GroupRemovalTests extends TestBase {

	@Test
	public void deleteSomeGroup() {
		app.getNavigationHelper().openMainPage();
		app.getNavigationHelper().gotoGroupsPage();

		int index = 1;
		app.getGroupHelper().deleteGroup(index); // because is no delete
												// confirmation

		app.getGroupHelper().returnToGroupsPage();
	}

}
