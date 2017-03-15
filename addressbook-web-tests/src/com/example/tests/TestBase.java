package com.example.tests;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import static com.example.tests.GroupDataGenerator.generateRandomGroups;

import com.example.fw.ApplicationManager;

public class TestBase {

	protected ApplicationManager app;
	private int checkCounter;
	private int checkFrequency;

	@BeforeTest
	public void setUp() throws Exception {

		String configFile = System.getProperty("configFile", "application.properties");

		Properties properties = new Properties();
		properties.load(new FileReader(new File(configFile)));
		app = new ApplicationManager(properties);
		
		checkCounter = 0;
		checkFrequency = Integer.parseInt(properties.getProperty("check.frequency", "0"));
	}
	
	protected boolean wantToCheck() {
		checkCounter++;
		if (checkCounter > checkFrequency) {
			checkCounter = 0;
			return true;
		} else {
			return false;
		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		app.stop();

	}

	@DataProvider
	public Iterator<Object[]> randomValidGroupGenerator() {
		return wrapGroupsForDataProvider(generateRandomGroups(5)).iterator();
	}

	protected static List<Object[]> wrapGroupsForDataProvider(List<GroupData> groups) {
		List<Object[]> list = new ArrayList<Object[]>();
		for (GroupData group : groups) {
			list.add(new Object[] { group });
		}
		return list;
	}

	@DataProvider
	public Iterator<Object[]> randomValidContactGenerator() {
		List<Object[]> list = new ArrayList<Object[]>();
		for (int iterator = 0; iterator < 5; iterator++) {
			ContactData contact = new ContactData();

			contact.birthdayDay = generateRandomString(28);
			contact.birthdayMonth = generateRandomMonthString();
			contact.birthdayYear = String.valueOf(1977 + Integer.parseInt(generateRandomString(40)));
			// contact.group = "";

			list.add(new Object[] { contact });
		}
		return list.iterator();
	}

	// public String generateRandomString() {
	// Random rnd = new Random();
	// String string;
	// if (rnd.nextInt(5) == 0) {
	// string = "";
	// } else {
	// string = "test_" + rnd.nextInt();
	// }
	// return string;
	// }

	public String generateRandomString(int maxValue) {
		Random rnd = new Random();
		int value = rnd.nextInt(maxValue);
		String string;
		if (value == 0) {
			string = Integer.toString(value + 1);
		} else {
			string = Integer.toString(value);
		}
		return string;
	}

	public String generateRandomMonthString() {
		Random rnd = new Random();
		String monthString;
		int monthNum = rnd.nextInt(12);

		if (monthNum == 0) {
			monthString = "January";
		} else {
			switch (monthNum) {
			case 1:
				monthString = "January";
				break;
			case 2:
				monthString = "February";
				break;
			case 3:
				monthString = "March";
				break;
			case 4:
				monthString = "April";
				break;
			case 5:
				monthString = "May";
				break;
			case 6:
				monthString = "June";
				break;
			case 7:
				monthString = "July";
				break;
			case 8:
				monthString = "August";
				break;
			case 9:
				monthString = "September";
				break;
			case 10:
				monthString = "October";
				break;
			case 11:
				monthString = "November";
				break;
			case 12:
				monthString = "December";
				break;
			default:
				monthString = "Invalid month";
				break;
			}
		}
		return monthString;
	}

}
