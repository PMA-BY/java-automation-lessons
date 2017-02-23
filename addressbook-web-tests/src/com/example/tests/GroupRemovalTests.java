package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Random;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupRemovalTests extends TestBase {

	@Test
	public void deleteSomeGroup() {
		// save previous state
		SortedListOf<GroupData> oldList = app.getGroupHelper().getGroups();

		Random rnd = new Random();
		int index = rnd.nextInt(oldList.size() - 1);

		app.getGroupHelper().deleteGroup(index); // One step because is no
													// delete confirmation

		// save new state
		SortedListOf<GroupData> newList = app.getGroupHelper().getGroups();

		// compare states
		assertThat(newList, equalTo(oldList.without(index)));
	}

}
