package com.example.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.example.fw.ApplicationManager;

public class TestBase {

	protected ApplicationManager app;

	@BeforeSuite
	public void setUp() throws Exception {
		app = new ApplicationManager();

	}

	@AfterSuite
	public void tearDown() throws Exception {
		app.stop();

	}

	@DataProvider
	public Iterator<Object[]> randomValidGroupGenerator() {
		List<Object[]> list = new ArrayList<Object[]>();
		for (int iterator = 0; iterator < 5; iterator++) {

			GroupData group = new GroupData()
					.withName(generateRandomString())
					.withHeader(generateRandomString())
					.withFooter(generateRandomString());

			list.add(new Object[] { group });
		}
		return list.iterator();
	}

	@DataProvider
	public Iterator<Object[]> randomValidContactGenerator() {
		List<Object[]> list = new ArrayList<Object[]>();
		for (int iterator = 0; iterator < 5; iterator++) {
			ContactData contact = new ContactData();

			contact.firstName = generateRandomString();
			contact.lastName = generateRandomString();
			contact.addressPrimary = generateRandomString();
			contact.telephoneHomePri = generateRandomString();
			contact.telephoneMobile = generateRandomString();
			contact.telephoneWork = generateRandomString();
			contact.email1 = generateRandomString();
			contact.email2 = generateRandomString();
			contact.birthdayDay = generateRandomString(28);
			contact.birthdayMonth = generateRandomMonthString();
			contact.birthdayYear = String.valueOf(1977 + Integer.parseInt((generateRandomString(40))));
			// contact.group = "";
			contact.addressSecondary = generateRandomString();
			contact.telephoneHomeSec = generateRandomString();

			list.add(new Object[] { contact });
		}
		return list.iterator();
	}

	public String generateRandomString() {
		Random rnd = new Random();
		String string;
		if (rnd.nextInt(5) == 0) {
			string = "";
		} else {
			string = "test_" + rnd.nextInt();
		}
		return string;
	}

	public String generateRandomString(int max_value) {
		Random rnd = new Random();
		int value = rnd.nextInt(max_value);
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
		int month_num = rnd.nextInt(12);

		if (month_num == 0) {
			monthString = "January";
		} else {
			switch (month_num) {
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
