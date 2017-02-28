package com.example.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.GroupData;
import com.example.utils.SortedListOf;

public class GroupHelper extends WebDriverHelperBase {

	public GroupHelper(ApplicationManager manager) {
		super(manager);
	}

	public GroupHelper createGroup(GroupData group) {
		manager.navigateTo().groupsPage();
		initGroupCreation();
		fillGroupForm(group);
		submitGroupForm();
		returnToGroupsPage();
		// update model
		manager.getModel().addGroup(group);
		// rebuildCache();
		return this;
	}

	public GroupHelper modifyGroup(int index, GroupData group) {
		manager.navigateTo().groupsPage();
		initGroupModification(index);
		fillGroupForm(group);
		submitGroupModification();
		returnToGroupsPage();
		// update model
		manager.getModel().removeGroup(index).addGroup(group);
		// rebuildCache();
		return this;
	}

	public GroupHelper deleteGroup(int index) {
		manager.navigateTo().groupsPage();
		selectGroupByIndex(index);
		submitGroupDeletion();
		returnToGroupsPage();
		// update model
		manager.getModel().removeGroup(index);
		// rebuildCache();
		return this;
	}

	// private SortedListOf<GroupData> cachedGroups;
	// public SortedListOf<GroupData> getGroups() {
	// if (cachedGroups == null) {
	// rebuildCache();
	// }
	//
	// return manager.getModel().getGroups(); // cachedGroups;
	//
	// }

	// private void rebuildCache() {
	// cachedGroups = new SortedListOf<GroupData>();
	//
	// manager.navigateTo().groupsPage();
	//
	// List <WebElement> checkboxes =
	// driver.findElements(By.name("selected[]"));
	//
	// for (WebElement checkbox : checkboxes) {
	// String title = checkbox.getAttribute("title");
	// String name = title.substring("Select (".length(), title.length() -
	// ")".length());
	// cachedGroups.add(new GroupData().withName(name));
	// }
	// }

	// --------------------------------------------------------------------------------------

	public SortedListOf<GroupData> getUiGroups() {
		SortedListOf<GroupData> groups = new SortedListOf<GroupData>();

		manager.navigateTo().groupsPage();
 
		List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));

		for (WebElement checkbox : checkboxes) {
			String title = checkbox.getAttribute("title");
			String name = title.substring("Select (".length(), title.length() - ")".length());
			groups.add(new GroupData().withName(name));
		}
		return groups;
	}
	
	public GroupHelper initGroupCreation() {
		manager.navigateTo().groupsPage();
		click(By.name("new"));
		return this;
	}

	public GroupHelper fillGroupForm(GroupData group) {
		type(By.name("group_name"), group.getName());
		type(By.name("group_header"), group.getHeader());
		type(By.name("group_footer"), group.getFooter());
		return this;
	}

	public GroupHelper submitGroupForm() {
		click(By.name("submit"));
		// cachedGroups = null;
		return this;
	}

	public GroupHelper returnToGroupsPage() {
		click(By.linkText("group page"));
		return this;
	}

	public GroupHelper initGroupModification(int index) {
		selectGroupByIndex(index);
		click(By.name("edit"));
		return this;
	}

	public GroupHelper submitGroupModification() {
		click(By.name("update"));
		// cachedGroups = null;
		return this;
	}

	private GroupHelper submitGroupDeletion() {
		click(By.name("delete"));
		// cachedGroups = null;
		return this;
	}

	private GroupHelper selectGroupByIndex(int index) {
		click(By.xpath("(//input[@name='selected[]'])[" + (index + 1) + "]"));
		return this;
	}

}
