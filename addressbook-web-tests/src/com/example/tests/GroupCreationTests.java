package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// import static com.example.tests.GroupDataGenerator.generateRandomGroups;
import static com.example.tests.GroupDataGenerator.loadGroupsFromCsvFile;
import static com.example.tests.GroupDataGenerator.loadGroupsFromXmlFile;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class GroupCreationTests extends TestBase {

	@DataProvider
	public Iterator<Object[]> groupsFromFile() throws IOException {
		return wrapGroupsForDataProvider(loadGroupsFromXmlFile(new File ("groups.xml"))).iterator();
	}

		@Test(dataProvider = "groupsFromFile") // (dataProvider =
														// "randomValidGroupGenerator")
	public void testGroupCreationWithValidData(GroupData group) throws Exception {

		// save previous state
		List<GroupData> oldList = app.getGroupHelper().getGroups();

		// actions
		app.getGroupHelper().createGroup(group);

		// save new state
		List<GroupData> newList = app.getGroupHelper().getGroups();

		// compare states (only by elements count) is obsolete
		// assertEquals(newList.size(), oldList.size() + 1);

		// compare states
		oldList.add(group);
		Collections.sort(oldList); // toLowerCase
		assertEquals(newList, oldList);

	}

}
